package org.java.controller;

import java.util.List;

import org.java.db.pojo.Message;
import org.java.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

	@Autowired
	MessageService messageServ;

	@GetMapping("/messages/list")
	public String getPicture(Model model, @RequestParam(required = false) String query) {

		// SE C'E' UNA QUERY TROVO LA PIC PER NOME ALTRIMENTI TROVO LE PIC PER CATEGORIE
//		List<Picture> pictures = query != null ? messageServ.findByTitleOrCategory(query)
//				: messageServ.getAllPicturesWithCategories();

		List<Message> messages = messageServ.findAll();

		model.addAttribute("messages", messages);

		return "message-list";

	}
}
