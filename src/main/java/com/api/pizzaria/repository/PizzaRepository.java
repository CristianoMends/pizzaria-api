package com.api.pizzaria.repository;

import com.api.pizzaria.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
