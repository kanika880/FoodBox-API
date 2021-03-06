package com.foodbox.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.foodbox.model.Order;
import com.foodbox.model.User;

public interface OrderRepository extends PagingAndSortingRepository<Order,Integer>{
	Page<Order> findByUserContaining(User user, Pageable pageable);
}