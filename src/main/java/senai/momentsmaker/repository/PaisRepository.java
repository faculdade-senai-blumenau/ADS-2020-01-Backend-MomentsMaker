package senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import senai.momentsmaker.entity.PaisEntity;


@Repository
public interface PaisRepository extends JpaRepository<PaisEntity, Long>{

}
