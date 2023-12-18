package org.java.db.repo;

import java.util.List;

import org.java.auth.db.pojo.User;
import org.java.db.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findByUser(User user);
}
