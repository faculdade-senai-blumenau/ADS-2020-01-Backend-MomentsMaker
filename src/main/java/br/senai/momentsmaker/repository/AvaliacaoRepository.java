package br.senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senai.momentsmaker.entity.AvaliacaoEntity;


@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Long>{

    @Query(value = "SELECT avg(av.nota) FROM avaliacao av WHERE av.fornecedor_id = ?1", nativeQuery = true)
    Double notaMediaAvaliacaoFornecedor(Long fornecedorId);

    @Query(value = "SELECT avg(av.nota) FROM avaliacao av WHERE av.cliente_id = ?1", nativeQuery = true)
    Double notaMediaAvaliacaoCliente(Long clienteId);
}

