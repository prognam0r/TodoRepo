package com.example.fullStackApplication.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(Long id){
        super("Could not found Task with id:"+id);

    }
}
