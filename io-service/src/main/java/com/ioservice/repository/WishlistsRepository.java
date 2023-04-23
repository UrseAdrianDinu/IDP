package com.ioservice.repository;

import com.ioservice.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistsRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByNameContaining(String name);
}
