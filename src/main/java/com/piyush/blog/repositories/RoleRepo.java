package com.piyush.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyush.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	Role findByName(String name);
}
