package com.lucianoortizsilva.relatorio.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.lucianoortizsilva.relatorio.repository.PokemRepository;

import jakarta.annotation.PostConstruct;

@Configuration
public class PokemomDadosConfig {

	private static final String DIRETORIO_IMG = "classpath:db/data/img/";
	private static final String SUFIXO_IMG = ".png";

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private PokemRepository repository;

	@PostConstruct
	public void init() throws IOException {
		final List<PokemonDTO> pokemons = getInserts(resourceLoader);
		for (final PokemonDTO pokemon : pokemons) {
			var entity = new PokemonEntity();
			BeanUtils.copyProperties(pokemon, entity);
			entity.setFoto(getImage(entity.getNome()));
			repository.save(entity);
		}
	}

	private byte[] getImage(final String nome) throws IOException {
		try {
			final String fullpath = DIRETORIO_IMG + nome + SUFIXO_IMG;
			final Resource resource = resourceLoader.getResource(fullpath);
			return resource.getInputStream().readAllBytes();
		} catch (final Exception e) {
			final String fullpath = DIRETORIO_IMG + "notfound" + SUFIXO_IMG;
			final Resource resource = resourceLoader.getResource(fullpath);
			return resource.getInputStream().readAllBytes();
		}
	}

	private static List<PokemonDTO> getInserts(final ResourceLoader resourceLoader) throws IOException {
		final List<PokemonDTO> pokemons = new ArrayList<>();
		final Resource resource = resourceLoader.getResource("classpath:db/changelog/scripts/insert-table.sql");
		try (InputStream inputStream = resource.getInputStream();BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				var conteudo = line.split(",");
				var id = Integer.valueOf(conteudo[0]);
				var nome = String.valueOf(conteudo[1].toLowerCase());
				var tipo1 = String.valueOf(conteudo[2]);
				var tipo2 = String.valueOf(conteudo[3]);
				var total = Integer.valueOf(conteudo[4]);
				var hp = String.valueOf(conteudo[5]);
				var ataque = Integer.valueOf(conteudo[6]);
				var defesa = Integer.valueOf(conteudo[7]);
				var qtdAtaque = Integer.valueOf(conteudo[8]);
				var qtdDefesa = Integer.valueOf(conteudo[9]);
				var velocidade = Integer.valueOf(conteudo[10]);
				var geracao = Integer.valueOf(conteudo[11]);
				var lendario = Boolean.valueOf(conteudo[12]);
				pokemons.add(new PokemonDTO(id, nome, tipo1, tipo2, total, hp, ataque, defesa, qtdAtaque, qtdDefesa,velocidade, geracao, lendario));
			}
		}
		return pokemons;
	}

}