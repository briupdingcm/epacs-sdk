package com.epacs.sdk.conf;

import java.net.URI;

public interface Configuration {
    // 生产系统的起点
    public URI getAppPoint();
    // 任务状态访问点
    public URI getTasksPoint();
    // 图像处理结果访问点
    public URI getImagesPoint();
    // 轮询时间差
    public Long getWaitTime();
    public String getUserName();
    public String getPassword();
}
