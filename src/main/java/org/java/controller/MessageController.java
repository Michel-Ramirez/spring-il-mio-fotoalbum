package org.java.controller;

import org.java.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

	@Autowired
	MessageService messageServ;

	@GetMapping("/messages/list")
	public String viewMessages(Model model) {

		return "message-list";
	}
}
