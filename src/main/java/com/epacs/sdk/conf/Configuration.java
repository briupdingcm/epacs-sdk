package com.epacs.sdk.conf;

import java.net.URI;

public interface Configuration {
    // 生产系统的起点
    public URI getAppPoint();
    public void setAppPoint(String appPoint);
    // 任务状态访问点
    public URI getTasksPoint();
    public void setTasksPoint(String tasksPoint);

    // 图像处理结果访问点
    public void setImagesPoint(String imagesPoint);
    public URI getImagesPoint();

    // 轮询时间差
    public long getWaitTime();


}
