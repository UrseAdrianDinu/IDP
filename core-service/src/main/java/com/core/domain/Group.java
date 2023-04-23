package com.core.domain;

import java.util.Set;

public class Group {

    private Long group_id;


    private String name;


    private String details;


    Set<User> members;


    private User user;

    Set<Wishlist> wishlists;

    public Group(Long group_id, String name, String details, Set<User> members, User user, Set<Wishlist> wishlists) {
        this.group_id = group_id;
        this.name = name;
        this.details = details;
        this.members = members;
        this.user = user;
        this.wishlists = wishlists;
    }

    public Group(String name, String details, Set<User> members, User user, Set<Wishlist> wishlists) {
        this.name = name;
        this.details = details;
        this.members = members;
        this.user = user;
        this.wishlists = wishlists;
    }

    public Group() {
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
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

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(Set<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }
}
