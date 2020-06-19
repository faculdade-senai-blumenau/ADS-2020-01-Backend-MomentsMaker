package senai.momentsmaker.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "endereco")
@Data
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rua", nullable = false)
    private String rua;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "numero", nullable = false)
    private Long numero;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "complemento")
    private String complemento;

    @OneToOne
    @JoinColumn(name = "municipio_id", referencedColumnName = "id")
    private MunicipioEntity municipio;
    
}
