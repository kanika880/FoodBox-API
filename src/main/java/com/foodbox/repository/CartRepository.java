package com.foodbox.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.foodbox.model.Cart;
import com.foodbox.model.User;

public interface CartRepository extends PagingAndSortingRepository<Cart,Integer>{
	Page<Cart> findByUserContaining(User user, Pageable pageable);
}
