package org.java.auth.db.serv;

import java.util.List;

import org.java.auth.db.pojo.User;
import org.java.auth.db.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(int id) {
		return userRepository.findById(id).get();
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

}
