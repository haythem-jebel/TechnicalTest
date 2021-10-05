package com.haythem.atos.service;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.haythem.atos.entity.User;

public interface UserService {
	public List<User> findAll();
	public User findOne(String userId);
	public User create(User user);
	public void delete(String id);
}
