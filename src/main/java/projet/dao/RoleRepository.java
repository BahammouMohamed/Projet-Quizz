package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
	public Role findByRoleName(String roleName);

}