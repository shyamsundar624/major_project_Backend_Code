package com.shyam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shyam.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
