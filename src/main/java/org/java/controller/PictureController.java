package org.java.controller;

import java.util.List;

import org.java.db.pojo.Category;
import org.java.db.pojo.Picture;
import org.java.db.serv.CategoryService;
import org.java.db.serv.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;

	@Autowired
	private CategoryService catServ;

	@GetMapping("/")
	public String getFoto(Model model, @RequestParam(required = false) String query) {

		List<Picture> pictures = query != null ? pictureService.findByTitleOrCategory(query)
				: pictureService.getAllPicturesWithCategories();

		System.out.println(pictures);
		model.addAttribute("pictures", pictures);
		return "index-pictures-list";

	}

	@GetMapping("/picture/{id}")
	public String detailPicture(Model model, @PathVariable int id) {

		Picture picture = pictureService.findById(id);
		List<Category> cat = picture.getCategories();

		model.addAttribute("picture", picture);
		model.addAttribute("categories", cat);
		return "detail-picture";

	}

}
