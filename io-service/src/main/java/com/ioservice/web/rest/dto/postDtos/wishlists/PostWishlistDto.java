package com.ioservice.web.rest.dto.postDtos.wishlists;

public class PostWishlistDto {
    private String name;
    private String details;

    public PostWishlistDto(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "PostWishlistDto{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
