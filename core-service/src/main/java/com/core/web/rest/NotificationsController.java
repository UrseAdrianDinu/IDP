package com.core.web.rest;

import com.core.domain.Notification;
import com.core.web.rest.dto.modelDtos.NotificationDto;
import com.core.web.rest.dto.responseDtos.GetItemsResponseDto;
import com.core.web.rest.dto.responseDtos.GetNotificationsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@RestController
public class NotificationsController {

    @GetMapping("/notifications/{user_id}")
    public ResponseEntity<List<NotificationDto>> getNotifications(@PathVariable("user_id") long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/notifications/" + id;
        ResponseEntity<List<NotificationDto>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<NotificationDto>>(){});
        List<NotificationDto> notifications = response.getBody();
        return new ResponseEntity<List<NotificationDto>>(notifications, HttpStatus.OK);
    }
}
