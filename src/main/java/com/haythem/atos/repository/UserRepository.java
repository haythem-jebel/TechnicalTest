package com.haythem.atos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haythem.atos.entity.User;

/**
 * 
 * @author HJEBEL
 *
 *         This is a repository using jpaRepository (JPA specific). DAO layer.
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

	public Optional<User> findOneById(String userId);

	public Optional<User> findOneByUsername(String username);

	public User save(User user);

}
