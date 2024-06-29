package com.api.pizzaria.controller;

import com.api.pizzaria.dto.OrderDTO;
import com.api.pizzaria.entity.Order;
import com.api.pizzaria.entity.Pizza;
import com.api.pizzaria.service.OrderService;
import com.api.pizzaria.service.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order API", description = "API para gerenciamento de pedidos")
public class OrderController {

    private final OrderService orderService;
    private final PizzaService pizzaService;

    @Autowired
    public OrderController(OrderService orderService, PizzaService pizzaService) {
        this.orderService = orderService;
        this.pizzaService = pizzaService;
    }

    @GetMapping
    @Operation(summary = "Obter todos os pedidos", description = "Retorna uma lista de todos os pedidos realizados")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter pedido por ID", description = "Retorna um pedido específico por ID")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Fazer um novo pedido", description = "Faz um novo pedido com uma lista de IDs de pizzas")
    public ResponseEntity<Void> placeOrder(@Valid @RequestBody OrderDTO orderDTO) {
        List<Pizza> pizzas = orderDTO.getPizzaIds().stream()
                .map(pizzaService::getPizzaById)
                .collect(Collectors.toList());

        Order order = new Order();
        order.setPizzas(pizzas);
        order.setTotalPrice(pizzas.stream().mapToDouble(Pizza::getPrice).sum());
        orderService.placeOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
