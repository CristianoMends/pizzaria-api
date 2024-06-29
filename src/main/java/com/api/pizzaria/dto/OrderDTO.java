package com.api.pizzaria.dto;


import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class OrderDTO {

    @NotEmpty(message = "Pizzas cannot be empty")
    private List<Long> pizzaIds;

    // Getters and Setters

    public List<Long> getPizzaIds() {
        return pizzaIds;
    }

    public void setPizzaIds(List<Long> pizzaIds) {
        this.pizzaIds = pizzaIds;
    }
}
