package br.senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senai.momentsmaker.entity.MunicipioEntity;


@Repository
public interface MunicipioRepository extends JpaRepository<MunicipioEntity, Long> {

}
