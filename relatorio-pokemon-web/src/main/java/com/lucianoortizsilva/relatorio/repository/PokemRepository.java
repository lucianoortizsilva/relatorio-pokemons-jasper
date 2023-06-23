package com.lucianoortizsilva.relatorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucianoortizsilva.relatorio.entity.Pokemon;

public interface PokemRepository extends JpaRepository<Pokemon, Integer> {}