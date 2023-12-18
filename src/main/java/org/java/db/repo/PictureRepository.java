package org.java.db.repo;

import java.util.List;

import org.java.auth.db.pojo.User;
import org.java.db.pojo.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

	List<Picture> findByTitleContainingIgnoreCaseAndVisibleTrue(String value);

	List<Picture> findByUserAndTitleContaining(User user, String value);

	List<Picture> findByVisibleTrue();

	List<Picture> findByUser(User user);

}
