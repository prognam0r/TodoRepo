package com.example.fullStackApplication.repository;

import com.example.fullStackApplication.model.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskRepository extends JpaRepository<Subtask,Long> {
}
