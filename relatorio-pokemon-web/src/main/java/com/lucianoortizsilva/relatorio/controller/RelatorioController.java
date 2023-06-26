package com.lucianoortizsilva.relatorio.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.lucianoortizsilva.relatorio.repository.PokemRepository;
import com.lucianoortizsilva.relatorio.service.JasperService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

@Controller
public class RelatorioController {
	
	@Autowired
	private JasperService service;
	
	@Autowired
	private PokemRepository repository;
	
	@GetMapping("/relatorio")
	public String relatorio() {
		return "relatorio";
	}
	
	@ModelAttribute("tipos")
	public List<String> buscarTiposDePokemons() {
		return this.repository.findTiposDePokemons();
	}
	
	@GetMapping("/pokemons/pdf/view")
	public void view(@RequestParam(name = "tipo", required = false) String tipo, HttpServletResponse response) throws JRException, IOException {
		service.addParams("TIPO_POKEMON", tipo.isEmpty() ? null : tipo);
		byte[] bytes = service.exportarPDF("pokemons");
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		response.setHeader("Content-disposition", "inline;filename=pokemons.pdf");
		response.getOutputStream().write(bytes);
	}
	
	@GetMapping("/pokemons/pdf/download")
	public void download(@RequestParam(name = "tipo", required = false) String tipo, HttpServletResponse response) throws JRException, IOException {
		service.addParams("TIPO_POKEMON", tipo.isEmpty() ? null : tipo);
		byte[] bytes = service.exportarPDF("pokemons");
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		response.setHeader("Content-disposition", "attachment;filename=pokemons.pdf");
		response.getOutputStream().write(bytes);
	}

}