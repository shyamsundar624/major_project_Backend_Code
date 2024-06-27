package com.shyam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shyam.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
