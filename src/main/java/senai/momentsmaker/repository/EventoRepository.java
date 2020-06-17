package senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import senai.momentsmaker.entity.EventoEntity;


@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {

}
