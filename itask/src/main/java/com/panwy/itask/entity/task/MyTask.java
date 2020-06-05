package com.panwy.itask.entity.task;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class MyTask {
    /**
     * 执行任务列表
     */
    private List<SimpleTask> executingTasks;

    /**
     * 未启动任务列表
     */
    private List<SimpleTask> initTasks;

    /**
     * 暂停任务列表
     */
    private List<SimpleTask> suspendTasks;

    /**
     * 已完成任务列表
     */
    private List<SimpleTask> finishTasks;

    /**
     * 已终止任务列表
     */
    private List<SimpleTask> stopTasks;

    public static MyTask init(Map<String,List<SimpleTask>> map){
        MyTask myTask = new MyTask();
        myTask.setExecutingTasks(map.get("进行中"));
        myTask.setInitTasks(map.get("未启动"));
        myTask.setSuspendTasks(map.get("已暂停"));
        myTask.setStopTasks(map.get("已终止"));
        myTask.setFinishTasks(map.get("已完成"));
        return myTask;
    }
}