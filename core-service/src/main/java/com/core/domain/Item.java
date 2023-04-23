package com.core.domain;


import java.util.Set;

public class Item {

    private Long item_id;


    private String name;


    private String details;


    private Integer quantity;


    private String size;


    private String maker;


    private String model;


    private String link;


    private User user;

    Set<Wishlist> wishlists;


    Set<User> buyers;



    public Item() {
    }

    public Item(Long item_id, String name, String details, Integer quantity, String size, String maker, String model, String link, User user, Set<Wishlist> wishlists, Set<User> buyers) {
        this.item_id = item_id;
        this.name = name;
        this.details = details;
        this.quantity = quantity;
        this.size = size;
        this.maker = maker;
        this.model = model;
        this.link = link;
        this.user = user;
        this.wishlists = wishlists;
        this.buyers = buyers;
    }

    public Item(String name, String details, Integer quantity, String size, String maker, String model, String link, User user, Set<Wishlist> wishlists, Set<User> buyers) {
        this.item_id = item_id;
        this.name = name;
        this.details = details;
        this.quantity = quantity;
        this.size = size;
        this.maker = maker;
        this.model = model;
        this.link = link;
        this.user = user;
        this.wishlists = wishlists;
        this.buyers = buyers;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public Set<User> getBuyers() {
        return buyers;
    }

    public void setBuyers(Set<User> buyers) {
        this.buyers = buyers;
    }
}
