package com.example.courier.services;

import com.example.courier.domain.Task;
import com.example.courier.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findByCreationDateBetween(LocalDate date1, LocalDate date2) {
        return taskRepository.findByCreationDateBetween(date1, date2);
    }

}
