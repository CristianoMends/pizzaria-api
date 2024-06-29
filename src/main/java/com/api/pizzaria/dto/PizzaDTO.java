package com.api.pizzaria.dto;


import com.api.pizzaria.entity.Pizza;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PizzaDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    private String description;

    @NotNull(message = "Price cannot be null")
    private double price;

    public PizzaDTO(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public PizzaDTO(){}

    public Pizza toEntity(){
        return new Pizza(getName(), getDescription(), getPrice());
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
