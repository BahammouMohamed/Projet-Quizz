package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.entities.Multimedia;

public interface MultimediaRepository extends JpaRepository<Multimedia, Long>{

}