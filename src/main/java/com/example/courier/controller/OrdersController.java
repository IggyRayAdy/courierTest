package com.example.courier.controller;

import com.example.courier.domain.Order;
import com.example.courier.services.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.findAllOrderByIdAsc();
    }

    @GetMapping("{id}")
    public Order getOrder(@PathVariable("id") Order order) {
        return order;
    }

    @PostMapping
    public Order addOrder(
            @RequestBody Order order) {
        order.setActive(true);
        if (order.getArrivalDate() == null) {
            order.setArrivalDate(LocalDate.now().toString());
        }
        return orderService.save(order);
    }

    @PutMapping("{id}")
    public Order updateOrder(
            @PathVariable("id") Order orderDB,
            @RequestBody Order order
    ) {
        BeanUtils.copyProperties(order, orderDB, "id", "arrivalDate");
        return orderService.save(orderDB);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable("id") Order order) {
        orderService.delete(order);
    }
}
