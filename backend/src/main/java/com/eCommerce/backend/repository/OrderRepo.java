package com.eCommerce.backend.repository;

import com.eCommerce.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findByUser_UserId(Long userId);
}
