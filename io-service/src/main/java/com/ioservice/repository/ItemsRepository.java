package com.ioservice.repository;

import com.ioservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Item, Long> {
    List<Item> findByNameContaining(String name);

}
