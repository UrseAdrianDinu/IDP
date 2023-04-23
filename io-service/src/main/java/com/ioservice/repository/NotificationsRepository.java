package com.ioservice.repository;

import com.ioservice.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notification, Long> {
}
