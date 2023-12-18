package org.java.controller;

import java.util.List;

import org.java.auth.db.pojo.User;
import org.java.auth.db.serv.UserService;
import org.java.db.pojo.Message;
import org.java.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageServ;

	@Autowired
	private UserService userService;

	@GetMapping("/messages/list")
	public String getMessages(Model model, @AuthenticationPrincipal UserDetails userDetails) {

		String username = userDetails.getUsername();
		User user = userService.findByUsername(username);

		List<Message> messages = messageServ.getAllMessagesByUser(user);
		model.addAttribute("messages", messages);

		model.addAttribute("username", username);
		return "message-list";

	}

	@PostMapping("/message/read/{id}")
	public String makeAsRaed(@PathVariable int id) {
		Message msg = messageServ.findById(id);

		msg.setMessage_read(true);

		messageServ.save(msg);

		return "redirect:/messages/list";
	}

	@PostMapping("/message/delete/{id}")
	public String deleteMsg(@PathVariable int id, RedirectAttributes redirectAttributes) {
		Message msg = messageServ.findById(id);
		messageServ.delete(msg);
		redirectAttributes.addFlashAttribute("msgDeleted", msg);
		return "redirect:/messages/list";

	}
}
