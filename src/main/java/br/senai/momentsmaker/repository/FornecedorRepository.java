package br.senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senai.momentsmaker.entity.FornecedorEntity;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Long>{

    @Modifying
    @Query(value = "UPDATE fornecedor f SET f.media_avaliacao = :notaMediaAvaliacaoFornecedor WHERE id = :fornecedorId", nativeQuery = true)
    void persistirNotaMediaAvaliacao(@Param("notaMediaAvaliacaoFornecedor") Double notaMediaAvaliacaoFornecedor, @Param("fornecedorId") Long fornecedorId);

}
