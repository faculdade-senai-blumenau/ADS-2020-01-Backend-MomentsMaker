package br.senai.momentsmaker.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contato")
@Data
public class ContatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone_residencial")
    private String telefoneResidencial;

    @Column(name = "telefone_celular")
    private String telefoneCelular;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private FornecedorEntity fornecedor;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

}
