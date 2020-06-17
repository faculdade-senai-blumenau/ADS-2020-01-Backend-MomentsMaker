package senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import senai.momentsmaker.entity.EnderecoEntity;


@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

}
