package org.java.db.serv;

import java.util.List;

import org.java.db.pojo.Picture;
import org.java.db.repo.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

	@Autowired
	private PictureRepository pictureRepo;

	public List<Picture> findAll() {
		return pictureRepo.findAll();
	}

	public Picture findById(int id) {
		return pictureRepo.findById(id).get();
	}

	public void save(Picture picture) {
		pictureRepo.save(picture);
	}

	public void delete(Picture picture) {
		pictureRepo.delete(picture);
	}

	public List<Picture> findByTitle(String value) {

		return pictureRepo.findByTitleContainingIgnoreCase(value);
	}
}
