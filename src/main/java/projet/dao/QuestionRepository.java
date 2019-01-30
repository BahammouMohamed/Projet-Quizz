package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}
