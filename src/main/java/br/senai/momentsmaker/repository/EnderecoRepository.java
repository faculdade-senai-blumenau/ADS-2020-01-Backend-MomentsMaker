package br.senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senai.momentsmaker.entity.EnderecoEntity;


@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

}
