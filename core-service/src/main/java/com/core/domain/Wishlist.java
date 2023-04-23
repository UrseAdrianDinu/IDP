package com.core.domain;

import java.util.Set;

public class Wishlist {

    private Long wishlist_id;


    private String name;


    private String details;


    private User user;


    Set<Group> groups;


    Set<Item> items;

    public Wishlist(Long wishlist_id, String name, String details, User user, Set<Group> groups, Set<Item> items) {
        this.wishlist_id = wishlist_id;
        this.name = name;
        this.details = details;
        this.user = user;
        this.groups = groups;
        this.items = items;
    }

    public Wishlist(String name, String details, User user, Set<Group> groups, Set<Item> items) {
        this.name = name;
        this.details = details;
        this.user = user;
        this.groups = groups;
        this.items = items;
    }
    public Wishlist() {
    }

    public Long getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(Long wishlist_id) {
        this.wishlist_id = wishlist_id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
