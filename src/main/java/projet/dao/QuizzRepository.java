package projet.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projet.entities.Quizz;

public interface QuizzRepository extends JpaRepository<Quizz, Long>{
	@Query("SELECT q FROM Quizz q where q.user = :id") 
    Collection<Quizz> findQuizzsByUserId(@Param("id") Long id);
}

