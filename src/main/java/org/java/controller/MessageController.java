package org.java.controller;

import java.util.List;

import org.java.db.pojo.Message;
import org.java.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MessageController {

	@Autowired
	MessageService messageServ;

	@GetMapping("/messages/list")
	public String getMessages(Model model, @RequestParam(required = false) String query) {

		// SE C'E' UNA QUERY TROVO LA PIC PER NOME ALTRIMENTI TROVO LE PIC PER CATEGORIE
//		List<Picture> pictures = query != null ? messageServ.findByTitleOrCategory(query)
//				: messageServ.getAllPicturesWithCategories();

		List<Message> messages = messageServ.findAll();

		model.addAttribute("messages", messages);

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
