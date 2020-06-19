package br.senai.momentsmaker.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.senai.momentsmaker.enumerator.Status;
import br.senai.momentsmaker.enumerator.TipoProfissional;
import lombok.Data;

@Entity
@Table(name = "fornecedorEvento")
@Data
public class FornecedorEventoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ehSuplente", unique = true)
	private Boolean ehSuplente;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "tipo_profissional", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoProfissional tipoProfissional;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fornecedor_id", nullable = false)
	private FornecedorEntity fornecedor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "evento_id", nullable = false)
	private EventoEntity evento;
}
