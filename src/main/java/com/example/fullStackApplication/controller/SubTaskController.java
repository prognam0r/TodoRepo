package com.example.fullStackApplication.controller;

import com.example.fullStackApplication.model.Subtask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/subtasks")
public class SubTaskController {

    @Autowired
    private com.example.fullStackApplication.Service.SubTaskService subtaskService;

    @PostMapping
    public Subtask createSubtask(@RequestBody Subtask subtask) {
        return subtaskService.saveSubtask(subtask);
    }

    @GetMapping
    public List<Subtask> getAllSubtasks() {
        return subtaskService.getAllSubtasks();
    }

    @GetMapping("/{id}")
    public Subtask getSubtaskById(@PathVariable Long id) {
        return subtaskService.getSubtaskById(id);
    }

    @PutMapping("/{id}")
    public Subtask updateSubtask(@PathVariable Long id, @RequestBody Subtask subtask) {
        subtask.setId(id);
        return subtaskService.saveSubtask(subtask);
    }

    @DeleteMapping("/{id}")
    public void deleteSubtask(@PathVariable Long id) {
        subtaskService.deleteSubtask(id);
    }
}


