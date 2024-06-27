package com.shyam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shyam.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
