package com.example.fullStackApplication.controller;


import com.example.fullStackApplication.Service.TaskService;
import com.example.fullStackApplication.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/tasks")
public class TaskController {
//    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;


    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(
            @RequestParam("name") String name,
            @RequestParam("duration") int duration,
            @RequestParam("progress") int progress,
            @RequestParam("status") String status,
            @RequestParam("priority") String priority,
            @RequestParam("notes") String notes,
            @RequestParam("reminderSet") boolean reminderSet,
            @RequestParam(value = "reminderDate", required = false) String reminderDate,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment) {

        Task task = new Task();
        task.setName(name);
        task.setDuration(duration);
        task.setProgress(progress);
        task.setStatus(status);
        task.setPriority(priority);
        task.setNotes(notes);
        task.setReminderSet(reminderSet);
        if (reminderDate != null && !reminderDate.isEmpty()) {
            task.setReminderDate(LocalDateTime.parse(reminderDate));
        }

        if (attachment != null && !attachment.isEmpty()) {
            // Save the attachment
            String fileName = UUID.randomUUID().toString() + "_" + attachment.getOriginalFilename();
            File file = new File("C:\\Users\\lenov\\Desktop\\fullStackApplication" + fileName);
            try {
                attachment.transferTo(file);
                task.setAttachment(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Task savedTask = taskService.saveTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @GetMapping("getAllTasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("duration") int duration,
            @RequestParam("progress") int progress,
            @RequestParam("status") String status,
            @RequestParam("priority") String priority,
            @RequestParam("notes") String notes,
            @RequestParam("reminderSet") boolean reminderSet,
            @RequestParam(value = "reminderDate", required = false) String reminderDate,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment) {

        Task existingTask = taskService.getTaskById(id);

        existingTask.setName(name);
        existingTask.setDuration(duration);
        existingTask.setProgress(progress);
        existingTask.setStatus(status);
        existingTask.setPriority(priority);
        existingTask.setNotes(notes);
        existingTask.setReminderSet(reminderSet);

        if (reminderDate != null && !reminderDate.isEmpty()) {
            existingTask.setReminderDate(LocalDateTime.parse(reminderDate));
        }

        if (attachment != null && !attachment.isEmpty()) {
            // Save the attachment
            String fileName = UUID.randomUUID().toString() + "_" + attachment.getOriginalFilename();
            File file = new File("C:\\Users\\lenov\\Desktop\\fullStackApplication\\" + fileName); // Ensure you include the backslash
            try {
                attachment.transferTo(file);
                existingTask.setAttachment(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Task updatedTask = taskService.saveTask(existingTask);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }


    @DeleteMapping("deleteTask/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.getTaskById(id);
        taskService.deleteTask(id);
        return "Task with id '"+id+"' has been deleted successfully";
    }
}
