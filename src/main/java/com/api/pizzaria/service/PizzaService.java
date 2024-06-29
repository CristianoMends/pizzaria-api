package com.api.pizzaria.service;



import com.api.pizzaria.entity.Pizza;

import java.util.List;

public interface PizzaService {
    List<Pizza> getAllPizzas();
    Pizza getPizzaById(Long id);
    void addPizza(Pizza pizza);
}
