package com.lucianoortizsilva.relatorio.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "POKEMON")
@EqualsAndHashCode
public class Pokemon implements Serializable {

	private static final long serialVersionUID = -5318686093175951277L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String tipo1;
	private String tipo2;
	private Integer total;
	private String hp;
	private Integer ataque;
	private Integer defesa;
	private Integer qtdAtaque;
	private Integer qtdDefesa;
	private Integer velocidade;
	private Integer geracao;
	private Boolean lendario;
	@Lob
	@Column(name = "foto", columnDefinition = "BLOB NOT NULL")
	private byte[] foto;

}	