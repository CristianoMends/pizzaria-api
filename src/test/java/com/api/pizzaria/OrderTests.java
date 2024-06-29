package com.api.pizzaria;

import com.api.pizzaria.entity.Order;
import com.api.pizzaria.entity.Pizza;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class OrderTests {

    @Mock
    private Pizza mockPizza1;

    @Mock
    private Pizza mockPizza2;

    private Order order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
        order.setPizzas(Arrays.asList(mockPizza1, mockPizza2));
    }

    @Test
    public void testCalculateTotalPrice() {
        when(mockPizza1.getPrice()).thenReturn(10.0);
        when(mockPizza2.getPrice()).thenReturn(15.0);

        order.calculateTotalPrice();

        println("test Calculate Total Price");
        println("Saida esperada: 25.0");
        println("Sua Saida:      "+ order.getTotalPrice());
        println("");

        assertEquals(25.0, order.getTotalPrice());
    }

    @Test
    public void testCalculateTotalPriceWithNoPizzas() {
        order.setPizzas(List.of());

        order.calculateTotalPrice();
        println("test Calculate Total Price With No Pizzas");
        println("Saida esperada: 0.0");
        println("Sua Saida:      "+ order.getTotalPrice());

        assertEquals(0.0, order.getTotalPrice());
    }

    @Test
    public void testCalculateTotalPriceWithNullPizzas() {
        order.setPizzas(null);

        order.calculateTotalPrice();
        println("Calculate Total Price With Null Pizzas");
        println("Saida esperada: 0.0");
        println("Sua Saida:      "+ order.getTotalPrice());

        assertEquals(0.0, order.getTotalPrice());
    }

    @Test
    public void testCalculateTotalPriceWithOnePizza() {
        when(mockPizza1.getPrice()).thenReturn(10.0);
        order.setPizzas(Arrays.asList(mockPizza1));

        order.calculateTotalPrice();

        println("test Calculate Total Price With One Pizza");
        println("Saida esperada: 10.0");
        println("Sua Saida:      "+ order.getTotalPrice());

        assertEquals(10.0, order.getTotalPrice());
    }

    @Test
    public void testCalculateTotalPriceWithZeroPricePizzas() {
        when(mockPizza1.getPrice()).thenReturn(0.0);
        when(mockPizza2.getPrice()).thenReturn(0.0);

        order.calculateTotalPrice();

        println("test Calculate Total Price With Zero Price Pizzas");
        println("Saida esperada: 0.0");
        println("Sua Saida:      "+ order.getTotalPrice());

        assertEquals(0.0, order.getTotalPrice());
    }
    public static void println(Object obj){
        System.out.println(obj);
    }
}

