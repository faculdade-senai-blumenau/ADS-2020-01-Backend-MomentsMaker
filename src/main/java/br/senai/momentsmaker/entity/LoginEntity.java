package br.senai.momentsmaker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "login")
@Data
public class LoginEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private boolean ehFornecedor;
}
