package com.core.web.rest.dto.responseDtos;

import com.core.web.rest.dto.modelDtos.NotificationDto;

import java.util.List;

public class GetNotificationsResponseDto {
    List<NotificationDto> notifications;

    public GetNotificationsResponseDto() {
    }

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
