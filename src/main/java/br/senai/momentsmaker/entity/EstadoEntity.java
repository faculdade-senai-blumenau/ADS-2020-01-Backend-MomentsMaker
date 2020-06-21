package br.senai.momentsmaker.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "estado")
@Data
public class EstadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sigla", nullable = false)
    private String sigla;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pais_id", nullable = false)
    private PaisEntity pais;

}
