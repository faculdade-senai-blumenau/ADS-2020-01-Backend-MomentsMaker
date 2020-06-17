package senai.momentsmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import senai.momentsmaker.entity.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long>{

}
