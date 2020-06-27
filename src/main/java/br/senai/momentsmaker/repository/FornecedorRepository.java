package br.senai.momentsmaker.repository;

import br.senai.momentsmaker.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senai.momentsmaker.entity.FornecedorEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Long> {

    List<FornecedorEntity> findByCategorias(CategoriaEntity categoriaEntity);

    @Query(value =
            "SELECT fr.* from fornecedor fr " +
            "LEFT JOIN evento_fornecedores evt_fr ON fr.id = evt_fr.fornecedores_id " +
            "LEFT JOIN evento ON evt_fr.evento_entity_id = evento.id " +
            "AND evento.data_inicio >= :dataInicio AND evento.data_fim <= :dataFim WHERE evento.id is null",
            nativeQuery = true)
    List<FornecedorEntity> findByDisponibilidade(LocalDateTime dataInicio, LocalDateTime dataFim);

}
