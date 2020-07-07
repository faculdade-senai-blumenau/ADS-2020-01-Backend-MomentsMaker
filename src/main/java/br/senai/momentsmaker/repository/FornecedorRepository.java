package br.senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.senai.momentsmaker.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senai.momentsmaker.entity.FornecedorEntity;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Long>{

	List<FornecedorEntity> findByCategorias(CategoriaEntity categoriaEntity);
	
    @Modifying
    @Query(value = "UPDATE fornecedor f SET f.media_avaliacao = :notaMediaAvaliacaoFornecedor WHERE id = :fornecedorId", nativeQuery = true)
    void persistirNotaMediaAvaliacao(@Param("notaMediaAvaliacaoFornecedor") Double notaMediaAvaliacaoFornecedor, @Param("fornecedorId") Long fornecedorId);

    @Query(value =
            "SELECT fr.* from fornecedor fr " +
            "LEFT JOIN evento_fornecedores evt_fr ON fr.id = evt_fr.fornecedores_id " +
            "LEFT JOIN evento ON evt_fr.evento_entity_id = evento.id " +
            "AND evento.data_inicio >= :dataInicio AND evento.data_fim <= :dataFim WHERE evento.id is null",
            nativeQuery = true)
    List<FornecedorEntity> findByDisponibilidade(LocalDate dataInicio, LocalDate dataFim);
}
