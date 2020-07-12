package br.senai.momentsmaker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;

@Entity
@Table(name = "evento")
@Data
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data_inicio", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @Column(name = "hora_inicio")
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaInicio;

    @Column(name = "data_fim", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFim;

    @Column(name = "hora_fim")
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaFim;

    @Column(name = "data_hora_inicio")
    @DateTimeFormat(pattern = "yyyy-MM-dd:HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm")
    private LocalDateTime dataHoraInicio;

    @Column(name = "data_hora_fim")
    @DateTimeFormat(pattern = "yyyy-MM-dd:HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm")
    private LocalDateTime dataHoraFim;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    private Long idFornecedor;
    
    @ManyToMany
    @JoinTable(name="evento_fornecedores",
               joinColumns=  @JoinColumn( name = "evento_id"),
               inverseJoinColumns= @JoinColumn(name = "fornecedor_id") )
    private List<FornecedorEntity> fornecedores;

}
