package com.app.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.Entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	   User findByUsername(String username);
	 


}