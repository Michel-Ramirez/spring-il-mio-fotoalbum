package org.java.db.repo;

import java.util.List;

import org.java.db.pojo.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

	List<Picture> findByTitleContainingIgnoreCase(String value);

}
