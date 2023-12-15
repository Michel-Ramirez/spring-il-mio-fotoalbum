package org.java.controller;

import java.util.List;

import org.java.db.pojo.Picture;
import org.java.db.serv.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;

	@GetMapping("/")
	public String getFoto(Model model, @RequestParam(required = false) String query) {

		List<Picture> pictures = query != null ? pictureService.findByTitle(query) : pictureService.findAll();

		model.addAttribute("pictures", pictures);
		return "index-pictures-list";

	}

}
