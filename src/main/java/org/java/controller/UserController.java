package org.java.controller;

import org.java.auth.config.AuthConfig;
import org.java.auth.db.pojo.Role;
import org.java.auth.db.pojo.User;
import org.java.auth.db.serv.RoleService;
import org.java.auth.db.serv.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@GetMapping("/")
	public String getHome(Model model, @AuthenticationPrincipal UserDetails userDetails) {

		if (userDetails != null) {

			return "redirect:/pictures";
		}

		User user = new User();

		model.addAttribute("newUser", user);
		return "home";
	}

	@PostMapping("/")
	public String saveNewUser(Model model, @Valid @ModelAttribute("newUser") User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			System.out.println("UTENTE DEL CAZZO" + user.getName() + " " + user.getSurname() + " " + user.getUsername()
					+ " " + user.getEmail());
			System.out.println("PASSWORD : " + user.getPassword());
			System.out.println("ERRORE BINDIND DEL CRISTO" + bindingResult);

			model.addAttribute("newUser", user);
			return "home";
		}

		try {

			System.out.println("SALVA");
			String psw = AuthConfig.passwordEncoder().encode(user.getPassword());

			Role adminRole = roleService.findByName("ADMIN");

			User newUser = new User(user.getName(), user.getSurname(), user.getUsername(), user.getEmail(), psw,
					adminRole);

			userService.save(newUser);

			return "redirect:/login";

		} catch (Exception e) {

			System.out.println("ERRORE NEL CATH DEL PORCO DIO");
			System.out.println("UTENTE DEL CAZZO" + user.getName() + " " + user.getSurname() + " " + user.getUsername()
					+ " " + user.getEmail() + " " + user.getPassword());

			bindingResult.addError(new FieldError("newUser", "username", user.getUsername(), false, null, null,
					"This username already exists"));
			bindingResult.addError(new FieldError("newUser", "email", user.getEmail(), false, null, null,
					"This email already exists"));
			model.addAttribute("newUser", user);
			return "home";

		}

	}
}
