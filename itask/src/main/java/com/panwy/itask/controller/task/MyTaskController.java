package com.panwy.itask.controller.task;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.panwy.itask.entity.task.MyTask;
import com.panwy.itask.entity.task.SimpleTask;
import com.panwy.itask.repository.SimpleTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/task/my")
public class MyTaskController {
    @Autowired
    private SimpleTaskRepository repository;

    @GetMapping("/show")
    public String show() {
        return "task/myTask";
    }

    @GetMapping("/all")
    @ResponseBody
    public MyTask query() {
        // // 查询未启动的任务
        // SimpleTask condition = new SimpleTask();
        // condition.setTaskState("未启动");
        // Example<SimpleTask> example = Example.of(condition);
        // 按优先级排序
        Sort.Order sort = Sort.Order.desc("priority");
        Sort by = Sort.by(sort);
        List<SimpleTask>list = repository.findAll(by);

        Map<String,List<SimpleTask>> map = list.stream().collect(Collectors.groupingBy(SimpleTask::getTaskState));

        MyTask myTask = MyTask.init(map);

        return myTask;
    }

    //创建任务
    @PostMapping("/create")
    @ResponseBody
    public String create(@RequestBody SimpleTask t,Principal principal) {
        
        // 创建任务
        t.setId(null);
        t.initCreInfo(principal.getName());
        t.setLastOpTime(new Date());
        t.setHistoryTime(0);
        t.setTaskState("未启动") ;
        t.fill();
        repository.save(t);
        return "启动成功";
    }

    //创建任务
    @PostMapping("/edit")
    @ResponseBody
    public String edit(@RequestBody SimpleTask t) {
        
        // 创建任务
        repository.save(t);
        return "启动成功";
    }


    // 启动
    @PostMapping("/start")
    @ResponseBody
    public String start(@RequestBody SimpleTask t) {

        //判定任务状态
        if(!t.getTaskState().equals("未启动")){
            return "只能对未启动的任务进行操作";
        }

        // 查询进行中的任务并暂停
        SimpleTask condition = new SimpleTask();
        condition.setTaskState("进行中");
        Example<SimpleTask> example = Example.of(condition);
        List<SimpleTask> suspends = repository.findAll(example);

        //

        for (SimpleTask simpleTask : suspends) {
            suspend(simpleTask);
        }
        // suspend.ifPresent(v ->{           
        //     suspend(v);
        // });

        // 启动任务
        t.setLastOpTime(new Date());
        t.setHistoryTime(0);
        t.setTaskState("进行中") ;
        repository.save(t);
        return "启动成功";
    }

    //暂停
    @PostMapping("/suspend")
    @ResponseBody
    public String suspend(@RequestBody SimpleTask t) {

        //判定任务状态
        if(!t.getTaskState().equals("进行中")){
            return "只能对进行中的任务进行操作";
        }
        // 暂停任务并计时
        Date now = new Date();        
        if(t.getLastOpTime() != null){
            Long interval = (now.getTime() - t.getLastOpTime().getTime())/(1000*60);
            t.setHistoryTime(t.getHistoryTime()+interval.intValue());
        }else{
            t.setHistoryTime(0);
        }
        t.setTaskState("已暂停") ;
        repository.save(t);
        return "暂停成功";
    }

    //恢复     
    @PostMapping("/restart")
    @ResponseBody
    public String restart(@RequestBody SimpleTask t) {

        //判定任务状态
        if(!t.getTaskState().equals("已暂停")){
            return "只能对已暂停的任务进行操作";
        }

        // 查询进行中的任务并暂停
        SimpleTask condition = new SimpleTask();
        condition.setTaskState("进行中");
        Example<SimpleTask> example = Example.of(condition);
        List<SimpleTask> suspends = repository.findAll(example);

        //

        for (SimpleTask simpleTask : suspends) {
            suspend(simpleTask);
        }

        // 启动任务
        t.setLastOpTime(new Date());
        //t.setHistoryTime(0);
        t.setTaskState("进行中") ;
        repository.save(t);
        return "恢复成功";
    }

    //完成
    @PostMapping("/finish")
    @ResponseBody
    public String finish(@RequestBody SimpleTask t) {

        //判定任务状态
        if(t.getTaskState().equals("已完成") && t.getTaskState().equals("已终止")){
            return "不能对已完成或已终止的任务进行操作";
        }
        // 暂停任务并计时
        Date now = new Date();
        if(t.getLastOpTime() != null){
            Long interval = (now.getTime() - t.getLastOpTime().getTime())/(1000*60);
            t.setHistoryTime(t.getHistoryTime()+interval.intValue());
        }else{
            t.setHistoryTime(0);
        }
        
        t.setLastOpTime(new Date());
        t.setTaskState("已完成") ;
        repository.save(t);
        return "任务完成";
    }
    //中止
    @PostMapping("/stop")
    @ResponseBody
    public String stop(@RequestBody SimpleTask t) {

        //判定任务状态
        if(t.getTaskState().equals("已完成") && t.getTaskState().equals("已终止")){
            return "不能对已完成或已终止的任务进行操作";
        }
        // 暂停任务并计时
        Date now = new Date();
        if(t.getLastOpTime() != null){
            Long interval = (now.getTime() - t.getLastOpTime().getTime())/(1000*60);
            t.setHistoryTime(t.getHistoryTime()+interval.intValue());
        }else{
            t.setHistoryTime(0);
        }
        
        t.setLastOpTime(new Date());
        t.setTaskState("已终止") ;
        repository.save(t);
        return "任务终止";
    }
}