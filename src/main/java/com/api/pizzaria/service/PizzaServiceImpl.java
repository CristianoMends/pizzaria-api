package com.api.pizzaria.service;

import com.api.pizzaria.entity.Pizza;
import com.api.pizzaria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza getPizzaById(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    @Override
    public void addPizza(Pizza pizza) {
        pizzaRepository.save(pizza);
    }
}

