package org.java.controller;

import java.util.List;

import org.java.db.pojo.Category;
import org.java.db.pojo.Picture;
import org.java.db.serv.CategoryService;
import org.java.db.serv.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

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

	@GetMapping("/picture/create")
	public String viewPage(Model model) {

		Picture picture = new Picture();

		model.addAttribute("picture", picture);
		model.addAttribute("categories", getCategories(model));
		return "create-update-pic";
	}

	@PostMapping("/picture/create")
	public String createPicture(Model model, @Valid @ModelAttribute Picture picture, BindingResult bindingResult) {

		return save(model, picture, bindingResult);

	}

	@GetMapping("/picture/edit/{id}")
	public String editPicture(Model model, @PathVariable int id) {

		Picture picture = pictureService.findById(id);

		model.addAttribute("categories", getCategories(model));
		model.addAttribute("picture", picture);

		return "create-update-pic";
	}

	@PostMapping("/picture/edit/{id}")
	public String updatePicture(Model model, @Valid @ModelAttribute Picture picture, BindingResult bindingResult) {

		return save(model, picture, bindingResult);

	}

	@PostMapping("/picture/delete/{id}")
	public String delete(Model model, @PathVariable int id, RedirectAttributes redirectAttributes) {

		Picture pic = pictureService.findById(id);

		pictureService.delete(pic);

		return "redirect:/";
	}

	public String save(Model model, @Valid @ModelAttribute Picture picture, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("picture", picture);
			model.addAttribute("categories", getCategories(model));
			return "create-update-pic";
		}

		pictureService.save(picture);

		int id = picture.getId();

		return "redirect:/picture/" + id;
	}

	public List<Category> getCategories(Model model) {
		List<Category> categories = catServ.findAll();
		return categories;
	}

}
