package com.panwy.itask.repository;

import com.panwy.itask.entity.task.SimpleTask;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleTaskRepository extends JpaRepository<SimpleTask, Long> {
    
}