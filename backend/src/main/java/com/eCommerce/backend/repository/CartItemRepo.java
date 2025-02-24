package com.eCommerce.backend.repository;

import com.eCommerce.backend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser_UserId(Long userId);
    Optional<CartItem> findByUser_UserIdAndProduct_ProductIdAndSize(Long userId, Long productId, String size);

    @Transactional
    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.user.userId = :userId")
    void deleteByUser_UserId(@Param("userId") Long userId);
}
