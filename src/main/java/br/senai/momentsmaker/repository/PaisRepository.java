package br.senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senai.momentsmaker.entity.PaisEntity;


@Repository
public interface PaisRepository extends JpaRepository<PaisEntity, Long>{

}
