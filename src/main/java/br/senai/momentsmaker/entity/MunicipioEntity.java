package br.senai.momentsmaker.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "municipio")
@Data
public class MunicipioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoEntity estado;

}
