package com.panwy.itask.controller.task;

import com.panwy.itask.controller.BaseController;
import com.panwy.itask.entity.task.SimpleTask;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task/simple")
public class SimpleTaskController extends BaseController<SimpleTask> {
    
    public String show(){

        return "task/simpleTask";
    }
}