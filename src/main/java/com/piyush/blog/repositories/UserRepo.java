package com.piyush.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyush.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
