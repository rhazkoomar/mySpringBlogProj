package cz.jiripinkas.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.jiripinkas.jba.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findRoleByName(String name);

}
