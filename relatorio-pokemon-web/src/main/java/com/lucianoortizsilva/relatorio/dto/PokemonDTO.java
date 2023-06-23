package com.lucianoortizsilva.relatorio.dto;

public record PokemonDTO(Integer id
					, String nome
					, String tipo1
					, String tipo2
					, Integer total
					, String hp
					, Integer ataque
					, Integer defesa
					, Integer qtdAtaque
					, Integer qtdDefesa
					, Integer velocidade
					, Integer geracao
					, Boolean lendario
		) {}