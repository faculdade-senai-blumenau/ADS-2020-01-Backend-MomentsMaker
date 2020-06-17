package senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import senai.momentsmaker.entity.AvaliacaoEntity;


@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Long>{

}

