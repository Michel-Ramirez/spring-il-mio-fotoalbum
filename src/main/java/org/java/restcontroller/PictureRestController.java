package org.java.restcontroller;

import java.util.List;

import org.java.db.pojo.Picture;
import org.java.db.serv.CategoryService;
import org.java.db.serv.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/pictures")
public class PictureRestController {

	@Autowired
	private PictureService pictureService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Picture>> getAllPicture(@RequestParam(required = false) String query) {

		try {
			List<Picture> pictures = query != null ? pictureService.findByTitle(query)
					: pictureService.findVidibleTrue();

			return new ResponseEntity<>(pictures, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
