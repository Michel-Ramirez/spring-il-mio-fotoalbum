package org.java.auth.db.repo;

import org.java.auth.db.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String value);

}
