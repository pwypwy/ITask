package com.panwy.itask.controller;

import java.security.Principal;
import java.util.List;
import com.panwy.itask.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


public class BaseController<T extends BaseEntity> {

    @Autowired
    private JpaRepository<T,Long> repository;

    @GetMapping("/show")
    public String show(){

        return "show";
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(@RequestBody T t,Principal principal){
        t.initCreInfo(principal.getName());
        repository.save(t);
        return "保存成功";
    }

    @PostMapping("/del")
    @ResponseBody
    public String deleate(@RequestBody T t){
        repository.delete(t);
        //repository.deleteById(t.getId());
        return "删除成功";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<T> query(@ModelAttribute T t,@RequestParam int page,@RequestParam int limit){
        PageRequest pageReques=PageRequest.of(page-1,limit);
        Example<T> example = Example.of(t);
        List<T> list = repository.findAll(example, pageReques).getContent();
        return list;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<T> queryAll(){
        return repository.findAll();
    }
    

    
}