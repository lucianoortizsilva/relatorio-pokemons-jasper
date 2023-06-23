package com.lucianoortizsilva.relatorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lucianoortizsilva.relatorio.repository.PokemRepository;

@Controller
public class RelatorioController {

	@Autowired
	private PokemRepository repository;
	
	@GetMapping("/relatorio")
	public String relatorio() {
		return "relatorio";
	}
	
	@GetMapping("/buscar/pokemons")
	public ModelAndView buscarFuncionariosPorNome(@RequestParam("nome") String nome) {
		return new ModelAndView("relatorio", "pokemons", this.repository.findPokemonsByNome(nome));
	}

}