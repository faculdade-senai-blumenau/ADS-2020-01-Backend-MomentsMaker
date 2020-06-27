package br.senai.momentsmaker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import br.senai.momentsmaker.enumerator.TipoProfissional;
import lombok.Data;

@Entity
@Table(name = "fornecedor")
@Data
public class FornecedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "cnpj_cpf", unique = true)
    private String cnpj_cpf;

    @Column(name = "nome_fantasia", nullable = false)
    private String nome_fantasia;
    
    @Column(name = "tipo_profissional", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProfissional tipoProfissional;
    
    @Column(name = "ehSuplente")
    private Boolean ehSuplente;
    
    @Column(name = "pessoaFisica")
    private Boolean pessoaFisica;
    
    @Column(name = "mediaAvaliacao")
    private Double mediaAvaliacao;
    
    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private EnderecoEntity endereco;
    
    @OneToOne
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    private LoginEntity login;
    
    // Responsável por criar o relacionamento ManyToMany entre tabela Fornecedor e Imagem gerando a "imagens_fornecedor"
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="imagens_fornecedor", 
               joinColumns=  @JoinColumn( name = "fornecedor_id"), 
               inverseJoinColumns= @JoinColumn(name = "imagem_id") )
    private List<ImagemEntity> imagens = new ArrayList<ImagemEntity>();

    @ManyToMany
    private List<CategoriaEntity> categorias;
    
}
