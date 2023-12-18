package org.java.restcontroller;

import org.java.auth.db.pojo.User;
import org.java.auth.db.serv.UserService;
import org.java.db.pojo.Message;
import org.java.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin
@RequestMapping("api/v1.0/messages")
public class MessageRestController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<Message> reciveMessage(@RequestBody Message message, @RequestParam int userId) {

		User user = userService.findById(userId);
		Message msg = new Message(message.getName(), message.getEmail(), message.getMessage(), user);

		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
