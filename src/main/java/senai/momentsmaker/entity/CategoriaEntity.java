package senai.momentsmaker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import senai.momentsmaker.enumerator.TipoProfissional;

@Entity
@Table(name = "categoria")
@Data
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tipo_profissional", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProfissional tipoProfissional;

    @Column(name = "descricao", nullable = false)
    private String descricao;

}
