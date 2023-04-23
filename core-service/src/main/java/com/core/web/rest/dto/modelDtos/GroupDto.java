package com.core.web.rest.dto.modelDtos;

import com.core.domain.Group;

import java.util.List;

public class GroupDto {

    private Long id;
    private String name;
    private String details;
    private Long ownerId;
    private List<WishlistDto> wishlists;
    private List<UserDto> users;

    public GroupDto(Long id, String name, String details, Long ownerId, List<WishlistDto> wishlists, List<UserDto> users) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.ownerId = ownerId;
        this.wishlists = wishlists;
        this.users = users;
    }

    public GroupDto(Group group, List<WishlistDto> wishlists, List<UserDto> users) {
        this.id = group.getGroup_id();
        this.name = group.getName();
        this.details = group.getDetails();
        this.ownerId = group.getUser().getId();
        this.wishlists = wishlists;
        this.users = users;
    }

    public GroupDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<WishlistDto> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<WishlistDto> wishlists) {
        this.wishlists = wishlists;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
