package com.lucianoortizsilva.relatorio.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lucianoortizsilva.relatorio.repository.PokemRepository;
import com.lucianoortizsilva.relatorio.service.JasperService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

@Controller
public class CartaController {

	@Autowired
	private PokemRepository repository;
	
	@Autowired
	private JasperService service;

	@GetMapping("/carta")
	public String index() {
		return "carta";
	}

	@GetMapping("/pokemons")
	public ModelAndView buscarPokemonsPorNome(@RequestParam("nome") String nome) {
		final String pageHtml = "carta";
		return new ModelAndView(pageHtml, "pokemons", this.repository.findPokemonsByNome(nome));
	}

	@GetMapping("/pokemons/card/pdf")
	public void gerarCard(@RequestParam("id") Integer id, HttpServletResponse response) throws JRException, IOException {
		service.addParams("ID", id);
		byte[] bytes = service.exportarPDF("pokemon-card");
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		response.setHeader("Content-disposition", "inline;filename=pokemon-" + id + ".pdf");
		response.getOutputStream().write(bytes);
	}
	
}