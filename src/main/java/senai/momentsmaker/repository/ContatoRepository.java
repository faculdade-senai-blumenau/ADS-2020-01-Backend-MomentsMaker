package senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import senai.momentsmaker.entity.ContatoEntity;


@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {

}

