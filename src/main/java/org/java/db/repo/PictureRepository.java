package org.java.db.repo;

import java.util.List;

import org.java.auth.db.pojo.User;
import org.java.db.pojo.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

	List<Picture> findByTitleContainingIgnoreCase(String value);

	List<Picture> findByUserAndTitleContaining(User user, String value);

	List<Picture> findByUser(User user);

	// QUERY PER TROVARE LE PIC PER NOME O PER CATEGORIA
//	@Query("SELECT DISTINCT p FROM Picture p LEFT JOIN p.categories c WHERE p.title LIKE %:searchQuery% OR c.name LIKE %:searchQuery%")
//	List<Picture> findPicturesByPictureTilteOrCategoryName(@Param("searchQuery") String searchQuery);
}
