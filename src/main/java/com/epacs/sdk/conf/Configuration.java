package com.epacs.sdk.conf;

import java.net.URI;

public interface Configuration {
    // 生产系统的起点
    public URI getAppPoint();
    public void setAppPoint(URI appPoint);
    public void setAppPoint(String appPoint);
    // 任务状态访问点
    public URI getTasksPoint();
    public void setTasksPoint(URI tasksPoint);

    // 图像处理结果访问点
    public URI getImagesPoint();
    public void setImagesPoint(URI imagesPoint);
    // 轮询时间差
    public long getWaitTime();

    public void setTasksPoint(String tasksPointStr);

    public void setImagesPoint(String s);
}
