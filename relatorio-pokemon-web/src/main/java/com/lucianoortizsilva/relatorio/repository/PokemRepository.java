package com.lucianoortizsilva.relatorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucianoortizsilva.relatorio.config.PokemonEntity;

public interface PokemRepository extends JpaRepository<PokemonEntity, Integer> {}