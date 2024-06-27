package com.shyam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shyam.entity.Order;

public interface OderRepository extends JpaRepository<Order, Integer> {

	public List<Order> findByUserId(Integer userId);

	public List<Order> findByOrderDateAndOrderStatus(String orderDate, String orderStatus);
}
