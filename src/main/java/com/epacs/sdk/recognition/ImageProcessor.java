package com.epacs.sdk.recognition;

import com.alibaba.fastjson.JSONObject;
import com.epacs.sdk.common.*;
import com.epacs.sdk.conf.Configuration;
import com.epacs.sdk.model.ImageResponse;
import com.epacs.sdk.model.TaskResponse;
import com.epacs.sdk.utils.HttpUtils;
import com.epacs.sdk.utils.JsonUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 提交任务和查询识别结果的API的封装
 *
 * @author: Kevin
 */
public class ImageProcessor {

    // 用户身份信息
    private String token;
    private JSONObject param;
    private Configuration conf;

    // 线程池对象
    private ExecutorService threadsPool;

    public ImageProcessor(String token, Configuration conf) {
        this.conf = conf;
        this.token = token;
        this.param = new JSONObject();
        this.threadsPool = Executors.newCachedThreadPool();
    }

    /**
     * 检测一个字符串是否是合理的图像编码
     * @param imageStr
     *          编码后以字符串形式表示的图像
     * @throws ImageFormatException
     */
    private void checkImage(String imageStr)throws ImageFormatException{
        //请提供实现过程
    }



    public TaskResponse createTask(String image) throws IOException, InternalException, RequestException {
        // 获取任务提交点
        URI taskUrl = conf.getTasksPoint();
        // 发送POST请求，提交任务参数图像
        param.put("image",image);
        String resp = HttpUtils.post(taskUrl, token, param);
        //checkResponse(resp);
        return  TaskResponse.parse(resp);
    }


    public TaskResponse getTaskInfo(Integer taskId) throws IOException, InternalException, RequestException {
        URI taskUrl = conf.getTasksPoint();
        // 任务状态查询点
        URI taskStatusUrl = URI.create(taskUrl + "/" + taskId);
        // 查询任务状态
        String resp = HttpUtils.get(taskStatusUrl, this.token);
        return  TaskResponse.parse(resp);
    }

    /**
     *
     * @param imageId 图像的id
     * @return 图像信息（json格式）
     * @throws URISyntaxException
     * @throws IOException
     */
    public ImageResponse getImageInfo(String imageId) throws IOException, RequestException, InternalException {
        URI imageUrl = conf.getImagesPoint();

        URI imageStatusUrl = URI.create(imageUrl + "/" + imageId);
        String resp = HttpUtils.get(imageStatusUrl, this.token);
        return  ImageResponse.parse(resp);
    }

    /**
     * 获得图像识别任务结束后的结果
     * @param taskId 任务编号
     * @return 图像污染程度的置信度
     * @throws IOException
     *
     */
    public Map<String, Double> getResult(Integer taskId) throws IOException, InternalException, RequestException {
        // 获得任务信息
        TaskResponse taskResponse = getTaskInfo(taskId);
        // 取出任务状态
        TaskStatus taskStatus = taskResponse.getStatus();
        // 如果任务处于就绪或运行中， 继续轮询
        while(TaskStatus.READY.equals(taskStatus)
                || TaskStatus.RUNNING.equals(taskStatus)){
            taskResponse = getTaskInfo(taskId);
            taskStatus = taskResponse.getStatus();
        }
        // 任务失败，终止任务，抛出异常
        if(TaskStatus.FAILURE.equals(taskStatus)){
            throw new TaskException(taskResponse.getErrorCode(), taskResponse.getErrorMsg());
        }else if(TaskStatus.SUCCESS.equals(taskStatus)) { //任务成功结束
            String imageId = taskResponse.getImageId();
            ImageResponse imageResponse = getImageInfo(imageId);
            return imageResponse.getResults();
        }else {
            throw new TaskException("illegal status: " + taskStatus);
        }
    }

    public Map<String, Double> submit(final String image) throws ImageFormatException, IOException, InternalException, RequestException {
        checkImage(image);
        // 发送请求创建任务
        TaskResponse taskResponse = createTask(image);
        // 获得任务编号
        Integer taskId = taskResponse.getTaskId();
        return getResult(taskId);

    }




    /**
     * 发送请求创建任务，方法内部进行异步查询任务执行结果
     *
     * @param callback
     *     回调接口对象
     */
    public void submit(final String image, final ResultCallback callback) throws ImageFormatException, IOException, TaskException, InternalException, RequestException {
        checkImage(image);
        // 发送请求创建任务
        TaskResponse taskResponse = createTask(image);

        // 获得任务编号
        Integer taskId = taskResponse.getTaskId();

        Long  time = conf.getWaitTime();

        // region ------------------------局部内部类------------------------
        // 局部内部类，线程类，封装用于查询识别结果
        class RecognitionThread implements Runnable{
            private  Long waitTime;
            private  Integer taskId;

            public RecognitionThread(Integer taskId, Long waitTime) {
                this.taskId = taskId;
                this.waitTime = waitTime;
            }

            @Override
            public void run(){
                boolean running = true;
                while(running){
                    try{
                        // 获得任务信息
                        TaskResponse taskResponse = getTaskInfo(taskId);
                        // 取出任务状态
                        TaskStatus taskStatus = taskResponse.getStatus();
                        // 如果任务处于就绪或运行中， 继续轮询
                        while(TaskStatus.READY.equals(taskStatus)
                                || TaskStatus.RUNNING.equals(taskStatus)){
                            Thread.sleep(waitTime);
                            taskResponse = getTaskInfo(taskId);
                            taskStatus = taskResponse.getStatus();
                        }

                        Integer errorCode = taskResponse.getErrorCode();
                        String errorMsg = taskResponse.getErrorMsg();
                        String imageId = taskResponse.getImageId();
                        ImageResponse imageResponse = getImageInfo(imageId);

                        callback.callback(new Integer(0), errorCode, errorMsg, imageResponse.getResults());

                    }catch(InterruptedException | IOException e){
                        e.printStackTrace();
                    } catch (InternalException e) {
                        e.printStackTrace();
                    } catch (RequestException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // endregion

        // 判断任务是否已交成功，如果任务提交成功，则启动新的线程查询任务的执行结果
        if(taskResponse.getErrorCode() == ErrorCode.SUCCESS.getErrorCode()){
            // 启动子线程，发送HTTP的GET请求发送请求获取图像的识别结果，使用循环进行轮训查新
            this.threadsPool.execute(new RecognitionThread(taskId, time));
        }
    }


}
