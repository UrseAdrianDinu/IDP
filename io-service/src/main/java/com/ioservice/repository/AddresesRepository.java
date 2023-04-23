package com.ioservice.repository;

import com.ioservice.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddresesRepository extends JpaRepository<Address, Long> {
}
