package com.ioservice.web.rest.dto.responseDtos;

import com.ioservice.web.rest.dto.modelDtos.NotificationDto;

import java.util.List;

public class GetNotificationsResponseDto {
    List<NotificationDto> notifications;

    public GetNotificationsResponseDto(List<NotificationDto> notifications) {
        this.notifications = notifications;
    }

    public List<NotificationDto> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationDto> notifications) {
        this.notifications = notifications;
    }
}
