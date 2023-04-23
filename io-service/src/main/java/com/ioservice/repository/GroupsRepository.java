package com.ioservice.repository;

import com.ioservice.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Group, Long> {
}
