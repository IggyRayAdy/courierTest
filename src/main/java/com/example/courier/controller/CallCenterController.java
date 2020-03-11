package com.example.courier.controller;

import com.example.courier.domain.Task;
import com.example.courier.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("center")
public class CallCenterController {

    @Autowired
    private final TaskService taskService;

    public CallCenterController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.findAll();
    }

    @GetMapping("{id}")
    public Task getTask(@PathVariable("id") Task task) {
        return task;
    }

    @GetMapping("/filter")
    public List<Task> findTaskBetweenDates(
            @RequestParam(name = "date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1,
            @RequestParam(name = "date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2
    ) {
        return taskService.findByCreationDateBetween(date1, date2);
    }
}
