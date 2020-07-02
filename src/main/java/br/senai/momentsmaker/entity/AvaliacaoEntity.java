package br.senai.momentsmaker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "avaliacao")
@Data
public class AvaliacaoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String comentario;

	@Column(name = "nota")
	private Double nota;

	@Column(name = "origemAvaliacao")
	private Boolean origemAvaliacao;
	
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private FornecedorEntity fornecedor;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private ClienteEntity cliente;

	@Column(name = "deCliente")
	private Boolean deCliente;
}
