package org.java.controller;

import java.util.List;

import org.java.db.pojo.Category;
import org.java.db.pojo.Picture;
import org.java.db.serv.CategoryService;
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
public class CategoryController {

	@Autowired
	CategoryService catServ;

	// ROTTA PER VEDERE LA LISTA DELLA CATEGORIE
	@GetMapping("/categories/list")
	public String viewCategories(Model model, @RequestParam(required = false) String query) {
		List<Category> categories = query != null ? catServ.findByName(query) : catServ.findAll();
		model.addAttribute("categories", categories);
		return "categories-list";
	}

	// ROTTA PER LA CREAZIONE DELLE CATEGORIE
	@GetMapping("/category/create")
	public String viewFormCat(Model model) {

		Category cat = new Category();
		model.addAttribute("category", cat);
		return "create-update-category";
	}

	// ROTTA IN POST RICEVE CATEGORIE, CONTROLLA SE CI SONO ERRORI NELLA CREAZIONE E
	// RESTITUISCE GLI ERRORI ALTRIMENTI SALVA LA CATEGORIA
	@PostMapping("/category/create")
	public String createCat(Model model, @Valid @ModelAttribute Category category, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("category", category);
			return "create-update-category";
		}

		catServ.save(category);

		return "redirect:/categories/list";

	}

	// ROTTA CHE ELIMINA LA CATEGORIA
	@PostMapping("/category/delete/{id}")
	public String deleteCategory(@PathVariable int id, RedirectAttributes redirectAtr) {
		Category cat = catServ.findById(id);

		// TROVO TUTTE LE PIC CHE HANNO QUESTA CATEGORIA
		List<Picture> pictures = cat.getPictures();

		// SE CI SONO PIC CON QUESTA CATEGORIA CICLO E LE LE RIMUOVO DALLE PC
		if (pictures.size() > 0) {
			for (Picture pic : pictures) {
				pic.getCategories().remove(cat);
			}
		}

		// ELIMINO LA CATEGORIA
		catServ.delte(cat);

		// RENDIRIZZO LA CATEGORIA ELIMINATA PER DARE UN FEEDBACK
		redirectAtr.addFlashAttribute("catDeleted", cat);
		return "redirect:/categories/list";
	}
}
