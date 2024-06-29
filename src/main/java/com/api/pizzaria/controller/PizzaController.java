package com.api.pizzaria.controller;

import com.api.pizzaria.dto.PizzaDTO;
import com.api.pizzaria.entity.Pizza;
import com.api.pizzaria.service.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@Tag(name = "Pizza API", description = "API para gerenciamento de pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    @Operation(summary = "Obter todas as pizzas", description = "Retorna uma lista de todas as pizzas disponíveis")
    public ResponseEntity<List<Pizza>> getAllPizzas() {
        return new ResponseEntity<>(pizzaService.getAllPizzas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter pizza por ID", description = "Retorna uma pizza específica por ID")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable Long id) {
        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza != null) {
            return new ResponseEntity<>(pizza, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Adicionar nova pizza", description = "Adiciona uma nova pizza ao sistema")
    public ResponseEntity<Void> addPizza(@Valid @RequestBody PizzaDTO pizzaDTO) {
        Pizza pizza = new Pizza();
        pizza.setName(pizzaDTO.getName());
        pizza.setDescription(pizzaDTO.getDescription());
        pizza.setPrice(pizzaDTO.getPrice());
        pizzaService.addPizza(pizza);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

