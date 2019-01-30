package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.entities.Quizz;

public interface QuizzRepository extends JpaRepository<Quizz, Long>{

}

