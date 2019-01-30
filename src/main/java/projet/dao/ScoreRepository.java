package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.entities.Score;

public interface ScoreRepository extends JpaRepository<Score, Long>{

}

