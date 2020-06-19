package br.senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senai.momentsmaker.entity.ContatoEntity;


@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {

}

