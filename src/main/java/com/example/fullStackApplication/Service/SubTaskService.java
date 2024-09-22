package com.example.fullStackApplication.Service;

import com.example.fullStackApplication.model.Subtask;
import com.example.fullStackApplication.repository.SubTaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskService {

    @Autowired
    private SubTaskRepository subtaskRepository;

    public Subtask saveSubtask(Subtask subtask) {
        return subtaskRepository.save(subtask);
    }

    public List<Subtask> getAllSubtasks() {
        return subtaskRepository.findAll();
    }

    public Subtask getSubtaskById(Long id) {
        return subtaskRepository.findById(id).orElse(null);
    }

    public void deleteSubtask(Long id) {
        subtaskRepository.deleteById(id);
    }

    // Additional methods for update, search, etc.
}
