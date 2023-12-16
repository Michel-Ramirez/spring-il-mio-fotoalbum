package org.java.db.serv;

import java.util.List;

import org.java.db.pojo.Message;
import org.java.db.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepo;

	public List<Message> findAll() {
		return messageRepo.findAll();
	}

	public Message findById(int id) {
		return messageRepo.findById(id).get();
	}

	public void save(Message message) {
		messageRepo.save(message);
	}

	public void delete(Message message) {
		messageRepo.delete(message);
	}
}
