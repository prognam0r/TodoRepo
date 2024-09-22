package com.example.fullStackApplication.Service;

import com.example.fullStackApplication.exception.TaskNotFoundException;
import com.example.fullStackApplication.model.Subtask;
import com.example.fullStackApplication.model.Task;
import com.example.fullStackApplication.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        if (task.getSubtasks() != null) {
            task.getSubtasks().forEach(subtask -> subtask.setTask(task));
        }
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}
