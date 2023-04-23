package com.ioservice.repository;

import com.ioservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findByNameContaining(String name);
}

