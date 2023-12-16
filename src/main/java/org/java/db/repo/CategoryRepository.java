package org.java.db.repo;

import java.util.List;

import org.java.auth.db.pojo.User;
import org.java.db.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	// QUERY CHE CERCA LA CATEGORIE PER NOME, IGNORANDO MAIUSCOLE O MINUSCOLE E
	// TRIMMANDO LA STRINGA
	List<Category> findByNameContainingIgnoreCase(String value);

	List<Category> findByUser(User user);

	List<Category> findByUserAndNameContainingIgnoreCase(User user, String value);
}
