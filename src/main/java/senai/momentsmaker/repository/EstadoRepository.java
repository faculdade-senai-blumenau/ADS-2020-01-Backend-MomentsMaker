package senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import senai.momentsmaker.entity.EstadoEntity;


@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity, Long> {

}