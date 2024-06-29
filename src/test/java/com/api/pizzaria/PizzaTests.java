package com.api.pizzaria;

import com.api.pizzaria.controller.PizzaController;
import com.api.pizzaria.dto.PizzaDTO;
import com.api.pizzaria.entity.Pizza;
import com.api.pizzaria.service.PizzaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PizzaTests {

	@Mock
	private PizzaService pizzaService;

	@InjectMocks
	private PizzaController pizzaController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllPizzas() {
		Pizza pizza1 = new Pizza(1L, "Margherita", "Tomato, mozzarella, and basil", 12.99);
		Pizza pizza2 = new Pizza(2L, "Pepperoni", "Pepperoni, tomato sauce, and mozzarella", 14.99);
		List<Pizza> pizzas = Arrays.asList(pizza1, pizza2);

		when(pizzaService.getAllPizzas()).thenReturn(pizzas);

		ResponseEntity<List<Pizza>> response = pizzaController.getAllPizzas();

		System.out.println("Esperado: " + pizzas);
		System.out.println("Retornado: " + response.getBody());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(pizzas, response.getBody());
	}

	@Test
	void testAddPizza() {
		PizzaDTO pizzaDTO = new PizzaDTO("Quattro Formaggi", "Mozzarella, gorgonzola, parmesan, and fontina", 15.99);

		ResponseEntity<Void> response = pizzaController.addPizza(pizzaDTO);

		System.out.println("Esperado: 201 CREATED");
		System.out.println("Retornado: " + response.getStatusCode());

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(pizzaService, times(1)).addPizza(argThat(pizza ->
				pizza.getName().equals(pizzaDTO.getName()) &&
						pizza.getDescription().equals(pizzaDTO.getDescription()) &&
						pizza.getPrice() == pizzaDTO.getPrice()
		));
	}
}
