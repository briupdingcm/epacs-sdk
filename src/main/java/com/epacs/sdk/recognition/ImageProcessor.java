package com.epacs.sdk.recognition;

import com.alibaba.fastjson.JSONObject;
import com.epacs.sdk.common.*;
import com.epacs.sdk.conf.Configuration;
import com.epacs.sdk.model.Request;
import com.epacs.sdk.model.ImageResponse;
import com.epacs.sdk.model.TaskResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @create: 2020.04.13 18:24
 * @author: Kevin
 * @description: . 图像处理器。创建图像处理任务，查询任务状态，获得处理结果
 */
public class ImageProcessor {
    private static Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    // 用户身份信息
    private String token;
    private Configuration conf;
    // 线程池对象
    private ExecutorService threadsPool;

    /**
     * 创建图像处理器
     * @param token 客户端的认证信息
     * @param conf 配置信息
     */
    public ImageProcessor(String token, Configuration conf) {
        this.conf = conf;
        this.token = token;
        this.threadsPool = Executors.newCachedThreadPool();
    }

    /**
     * 创建图像识别任务
     * @param image base64编码后以字符串形式表示的图像
     * @return 返回新建任务的状态
     * @throws IOException 无法和服务器端连接
     * @throws InternalException 服务器内部出错
     * @throws RequestException 发送的请求出现错误
     */
    public TaskResponse createTask(String image) throws IOException, InternalException, RequestException {
        // 获取任务提交点
        URI taskUrl = conf.getTasksPoint();
        // 请求对象
        Request request = new Request(image);
        // 向任务提交点发送请求
        String resp = request.doPost(taskUrl, this.token);
        // 返回新建任务的信息
        return  TaskResponse.parse(resp);
    }

    /**
     * 创建图像识别任务
     * @param image base64编码后以字符串形式表的图像
     * @param region image对应的发动机部位
     * @return 返回新建任务的状态
     * @throws IOException 无法和服务器端连接
     * @throws InternalException 服务器内部出错
     * @throws RequestException 发送的请求出现错误
     */
    public TaskResponse createTask(String image, Region region) throws IOException, InternalException, RequestException {
        // 获取任务提交点
        URI taskUrl = conf.getTasksPoint();

        Request request = new Request(image, region);

        String resp = request.doPost(taskUrl, this.token);
        // 发送POST请求，提交任务参数图像

        return  TaskResponse.parse(resp);
    }

    /**
     * 查询图像处理任务的相关信息
     * @param taskId 任务编号，创建任务时由服务端返回
     * @return 任务的当前信息
     * @throws IOException 访问服务器出错
     * @throws InternalException 服务器内部出错
     * @throws RequestException 发送的请求参数不合法
     */
    public TaskResponse getTaskInfo(Integer taskId) throws IOException, InternalException, RequestException {
        URI taskUrl = conf.getTasksPoint();
        // 任务状态查询点
        URI taskStatusUrl = URI.create(taskUrl + "/" + taskId);
        // 查询任务状态
        Request request = new Request();
        String resp = request.doGet(taskStatusUrl, this.token);
        // 返回当前任务信息
        return  TaskResponse.parse(resp);
    }

    /**
     *  获取图像处理结果
     * @param imageId 图像的id，创建图像处理任务时服务端分配的唯一标识
     * @return 图像信息（json格式）
     * @throws IOException 访问服务器出错
     * @throws InternalException 服务器内部出错
     * @throws RequestException 发送的请求参数不合法
     */
    public ImageResponse getImageInfo(String imageId) throws IOException, RequestException, InternalException {
        logger.debug("getImageInfo " + imageId);
        // 图像资源访问点
        URI imageUrl = conf.getImagesPoint();
        // 图像信息获取点
        URI imageStatusUrl = URI.create(imageUrl + "/" + imageId + "?query=result");
        // 请求
        Request request = new Request();
        // 提交请求，获得响应
        String resp = request.doGet(imageStatusUrl, this.token);
        // 返回图像处理结果
        return  ImageResponse.parse(resp);
    }

    /**
     * 获得图像识别任务结束后的结果
     * @param taskId 任务编号
     * @return 图像污染程度的置信度
     * @throws IOException 访问服务器出错
     * @throws InternalException 服务器内部出错
     * @throws RequestException 发送的请求参数不合法
     *
     */
    public ImageResponse getResult(Integer taskId) throws IOException, InternalException, RequestException {
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
            return imageResponse;
        }else {
            throw new TaskException("illegal status: " + taskStatus);
        }
    }

    /**
     * 同步提交图像，获得该图像识别结果
     * @param image base64编码以字符串形式表示的图像
     * @return 该图像污染程度置信度
     * @throws ImageFormatException
     * @throws IOException
     * @throws InternalException
     * @throws RequestException
     */
    public ImageResponse submit(final String image) throws IOException, InternalException, RequestException {
        // 发送请求创建任务
        TaskResponse taskResponse = createTask(image);
        // 获得任务编号
        Integer taskId = taskResponse.getTaskId();
        // 图像处理结果
        return getResult(taskId);

    }

    /**
     * 同步提交图像，获得该图像识别结果
     * @param image base64编码以字符串形式表示的图像
     * @parsm region 发动机部位
     * @return 该图像污染程度置信度
     * @throws ImageFormatException
     * @throws IOException
     * @throws InternalException
     * @throws RequestException
     */
    public ImageResponse submit(final String image, Region region) throws IOException, InternalException, RequestException {
        // 发送请求创建任务
        TaskResponse taskResponse = createTask(image, region);
        // 获得任务编号
        Integer taskId = taskResponse.getTaskId();
        return getResult(taskId);

    }

    /**
     * 提交图像， 异步方式获得识别结果
     * @param image base64编码的字符串图像
     * @param callback 服务端返回识别结果后的回调函数
     * @throws IOException
     * @throws TaskException
     * @throws InternalException
     * @throws RequestException
     */
    public void submit(final String image, final ResultCallback callback) throws IOException, TaskException, InternalException, RequestException {
        // 发送请求创建任务
        TaskResponse taskResponse = createTask(image);
        // 获得任务编号
        int taskId = taskResponse.getTaskId();
        long  time = conf.getWaitTime();

        // region ------------------------局部内部类------------------------
        // 局部内部类，线程类，封装用于查询识别结果
        class RecognitionThread implements Runnable{
            public final long waitTime;
            public final int taskId;

            public RecognitionThread(int taskId, long waitTime) {
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
                        // 图像ID
                        String imageId = taskResponse.getImageId();
                        // 图像的识别结果
                        ImageResponse imageResponse = getImageInfo(imageId);
                        // 回调任务的错误码和图像识别结果
                        callback.callback(imageResponse);

                    }catch(InterruptedException | IOException e){
                        logger.error("Thread exception：" + e.getMessage());
                    } catch (InternalException e) {
                        logger.error("server error: " + e.getMessage());
                    } catch (RequestException e) {
                        logger.error("request error: " + e.getMessage());
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
