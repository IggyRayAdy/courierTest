package com.example.courier.controller;

import com.example.courier.domain.Order;
import com.example.courier.domain.Task;
import com.example.courier.services.OrderService;
import com.example.courier.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("task")
public class CourierController {

    @Autowired
    private final OrderService orderService;
    private final TaskService taskService;

    public CourierController(OrderService orderService, TaskService taskService) {
        this.orderService = orderService;
        this.taskService = taskService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.findAllOrderByIdAsc();
    }

    @GetMapping("/late")
    public void addTask(@RequestParam(name = "id") Long id) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent() && order.get().isActive() && !order.get().isDone()) {
            Task task = new Task();
            task.setOrder(order.get());
            task.setCreationDate(order.get().getArrivalDate());
            task.setCreationTime(LocalTime.now());
            taskService.save(task);
            order.get().setActive(false);
            orderService.save(order.get());
        }
    }
}



