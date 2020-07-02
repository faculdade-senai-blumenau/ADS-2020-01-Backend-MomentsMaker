package br.senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senai.momentsmaker.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Modifying
    @Query(value = "UPDATE cliente c SET c.media_avaliacao = :notaMediaAvaliacaoCliente WHERE id = :clienteId", nativeQuery = true)
    void persistirNotaMediaAvaliacao(@Param("notaMediaAvaliacaoCliente") Double notaMediaAvaliacaoCliente, @Param("clienteId") Long clienteId);

}
