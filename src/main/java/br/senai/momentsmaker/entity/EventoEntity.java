package br.senai.momentsmaker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "evento")
@Data
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "criacao_evento", nullable = false)
    private LocalDateTime criacaoEvento;

    @Column(name = "data_inicio", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd:HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd:HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm")
    private LocalDateTime dataFim;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToMany
    private List<FornecedorEntity> fornecedores;

}
