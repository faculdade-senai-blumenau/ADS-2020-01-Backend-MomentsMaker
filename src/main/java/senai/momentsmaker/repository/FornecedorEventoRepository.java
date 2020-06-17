package senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import senai.momentsmaker.entity.FornecedorEventoEntity;


@Repository
public interface FornecedorEventoRepository extends JpaRepository<FornecedorEventoEntity, Long> {

}
