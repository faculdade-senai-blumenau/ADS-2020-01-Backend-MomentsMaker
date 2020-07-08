package br.senai.momentsmaker.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senai.momentsmaker.entity.EventoEntity;


@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {
	
	@Modifying
    @Query(value = "insert into evento_fornecedores (evento_id,fornecedor_id) VALUES (:eventoId,:fornecedorId)", nativeQuery = true)
    @Transactional
    void inserirEventoFornecedor(@Param("eventoId") Long eventoId, @Param("fornecedorId") Long fornecedorId);
}
