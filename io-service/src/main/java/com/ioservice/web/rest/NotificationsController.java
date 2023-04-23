package com.ioservice.web.rest;

import com.ioservice.domain.Notification;
import com.ioservice.repository.NotificationsRepository;
import com.ioservice.repository.UsersRepository;
import com.ioservice.web.rest.dto.modelDtos.NotificationDto;
import com.ioservice.web.rest.dto.responseDtos.GetNotificationsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NotificationsController {

    @Autowired
    NotificationsRepository notificationsRepository;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/me/notifications")
    public ResponseEntity<GetNotificationsResponseDto> getAllNotifications() {

        try {

            List<Notification> notifications = new ArrayList<Notification>(usersRepository.findById(1L).get().getNotifications());
            //notificationsRepository.findAll().forEach(notifications::add);


            if (notifications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<NotificationDto> notificationDtos= new ArrayList<>();
            for(Notification notif : notifications) {
                notificationDtos.add(new NotificationDto(notif.getNotification_id(), notif.getCategory(), notif.getContent()));
            }
            return new ResponseEntity<>(new GetNotificationsResponseDto(notificationDtos), HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/notifications/{user_id}")
    public ResponseEntity<List<NotificationDto>> getNotifications() {

        try {

            List<Notification> notifications = new ArrayList<Notification>(notificationsRepository.findAll());

            List<NotificationDto> notificationDtos= new ArrayList<>();
            if (notifications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for(Notification notif : notifications) {
                notificationDtos.add(new NotificationDto(notif.getNotification_id(), notif.getCategory(), notif.getContent()));
            }

            return new ResponseEntity<>(notificationDtos, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
