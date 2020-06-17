package senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import senai.momentsmaker.entity.ImagemEntity;


@Repository
public interface ImagemRepository extends JpaRepository<ImagemEntity, Long>{

}