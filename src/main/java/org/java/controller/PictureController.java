package org.java.controller;

import java.util.List;

import org.java.auth.db.pojo.Role;
import org.java.auth.db.pojo.User;
import org.java.auth.db.serv.UserService;
import org.java.db.pojo.Category;
import org.java.db.pojo.Message;
import org.java.db.pojo.Picture;
import org.java.db.serv.CategoryService;
import org.java.db.serv.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

	@Autowired
	private UserService userService;

	// ROTTA PER LA HOME DOVE MOSTRO TUTE LE PC
	@GetMapping("/pictures")
	public String getPicture(Model model, @RequestParam(required = false) String query,
			@AuthenticationPrincipal UserDetails userDetails) {

		String username = userDetails.getUsername();

		User user = getUserIsLog(userDetails);

		int unreadMessagesCount = unreadMsgCount(user);

		if (userHasRole(user, "SUPERADMIN")) {

			System.out.println("ENTRA");
			List<Picture> pictures = query != null ? pictureService.findByTitle(query) : pictureService.findAll();
			model.addAttribute("pictures", pictures);
		} else {
			// SE C'E' UNA QUERY TROVO LA PIC PER NOME ALTRIMENTI TROVO LE PIC PER CATEGORIE
			List<Picture> pictures = query != null ? pictureService.findByUserAndTitleOrCategory(user, query)
					: pictureService.getAllPicturesByUser(user);

			model.addAttribute("pictures", pictures);
		}

		model.addAttribute("username", username);
		model.addAttribute("messagesSize", unreadMessagesCount);
		return "index-pictures-list";

	}

	// -------| FIND ROLE | ------- //

	private boolean userHasRole(User user, String roleName) {

		for (Role role : user.getRoles()) {
			if (role.getName().equals(roleName)) {
				return true;
			}
		}

		return false;
	}

	// ROTTA PER IL DETTAGLIO DELLA PC
	@GetMapping("/picture/{id}")
	public String detailPicture(Model model, @PathVariable int id, @AuthenticationPrincipal UserDetails userDetails) {

		if (userDetails == null) return "redirect:/login";

		User user = getUserIsLog(userDetails);

		int unreadMessagesCount = unreadMsgCount(user);

		Picture picture = pictureService.findById(id);
		List<Category> cat = picture.getCategories();

		model.addAttribute("picture", picture);
		model.addAttribute("categories", cat);
		model.addAttribute("messagesSize", unreadMessagesCount);
		return "detail-picture";

	}

	// ROTTA PER LA CREAZIONE DELLA PC
	@GetMapping("/picture/create")
	public String viewPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {

		if (userDetails == null) return "redirect:/login";
		Picture picture = new Picture();

		model.addAttribute("picture", picture);
		model.addAttribute("categories", getCategories(model));
		return "create-update-pic";
	}

	// ROTTA IN POST PER IL SALVATAGGIO DELLA PIC CON VALIDAZIONE, SE C'E' UN ERRORE
	// RESTITUIRA' L'OGGETTO CON GLI ERRORI
	@PostMapping("/picture/create")
	public String createPicture(Model model, @Valid @ModelAttribute Picture picture, BindingResult bindingResult,
			@AuthenticationPrincipal UserDetails userDetails) {

		String username = userDetails.getUsername();
		User user = userService.findByUsername(username);
		picture.setUser(user);

		// RICHIAMO IL METODO SAVE PASSANDOGLI DEGLI ATTRIBUTI
		return save(model, picture, bindingResult);

	}

	@GetMapping("/picture/edit/{id}")
	public String editPicture(Model model, @PathVariable int id, @AuthenticationPrincipal UserDetails userDetails) {

		if (userDetails == null) return "redirect:/login";
		User user = getUserIsLog(userDetails);

		Picture picture = pictureService.findById(id);

		if(picture.getUser().getId() != user.getId()){

			return "not-auth";
		}

		model.addAttribute("categories", getCategories(model));
		model.addAttribute("picture", picture);

		return "create-update-pic";
	}

	@PostMapping("/picture/edit/{id}")
	public String updatePicture(Model model, @Valid @ModelAttribute Picture picture, BindingResult bindingResult) {

		return save(model, picture, bindingResult);

	}

	@PostMapping("/picture/delete/{id}")
	public String delete(Model model, @PathVariable int id, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails ) {

		User user = getUserIsLog(userDetails);

		Picture pic = pictureService.findById(id);

		if(pic.getUser().getId() != user.getId()){

			return "not-auth";
		}

		pictureService.delete(pic);
		redirectAttributes.addFlashAttribute("picDeleted", pic);
		return "redirect:/pictures";
	}

	// SAVE PICTURE

	// METODO PER IL SALVATAGGIO RICEVE SE CI SONO DEGLI ERRORI RITORNO GLI ERRORI
	// IN PAGINA E RENDIRIZZO ALLA PAGINA
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

	public User getUserIsLog(UserDetails userDetails){
		String username = userDetails.getUsername();
		User user = userService.findByUsername(username);

		return user;
	}


	public List<Category> getCategories(Model model) {
		List<Category> categories = catServ.findAll();
		return categories;
	}

	private int unreadMsgCount(User user) {
		List<Message> messages = user.getMessages();
		int unreadMessagesCount = 0;

		for (Message message : messages) {
			if (!message.isMessage_read()) {
				unreadMessagesCount++;
			}
		}
		return unreadMessagesCount;
	}

}
