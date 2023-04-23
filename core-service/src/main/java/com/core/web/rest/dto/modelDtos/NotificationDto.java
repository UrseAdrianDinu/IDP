package com.core.web.rest.dto.modelDtos;


public class NotificationDto {
    private Long id;

    private String category;

    private String details;

    public NotificationDto(Long id, String category, String details) {
        this.id = id;
        this.category = category;
        this.details = details;
    }

    public NotificationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
