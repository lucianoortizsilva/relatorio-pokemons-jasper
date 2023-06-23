package com.lucianoortizsilva.relatorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lucianoortizsilva.relatorio.entity.Pokemon;

import jakarta.persistence.Tuple;

public interface PokemRepository extends JpaRepository<Pokemon, Integer> {

	@Query("select "
			+ "p.id         as id, "
			+ "p.nome       as nome, " 
			+ "p.hp         as hp, "
			+ "p.velocidade as velocidade, "
			+ "p.ataque     as ataque, "
			+ "p.defesa     as defesa "
			+ "FROM Pokemon p " 
			+ "WHERE p.nome like %:nome% ")
	List<Tuple> findPokemonsByNome(String nome);

}