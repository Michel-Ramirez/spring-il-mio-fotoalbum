package org.java.db.serv;

import java.util.List;

import org.java.auth.db.pojo.User;
import org.java.db.pojo.Category;
import org.java.db.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	public Category findById(int id) {
		return categoryRepo.findById(id).get();
	}

	public void save(Category category) {
		categoryRepo.save(category);
	}

	public List<Category> getAllCategoryByUser(User user) {
		return categoryRepo.findByUser(user);
	}

	public List<Category> findByUserAndTitleOrCategory(User user, String query) {
		return categoryRepo.findByUserAndNameContainingIgnoreCase(user, query);
	}

	public void delte(Category category) {
		categoryRepo.delete(category);
	}

	public List<Category> findByName(String value) {
		return categoryRepo.findByNameContainingIgnoreCase(value);
	}
}
