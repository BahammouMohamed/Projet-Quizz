package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
