package com.ioservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", unique = true)
    private Long item_id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "size")
    private String size;

    @Column(name = "maker")
    private String maker;

    @Column(name = "model")
    private String model;

    @Column(name = "link")
    private String link;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToMany(mappedBy = "items", fetch = FetchType.EAGER)
    Set<Wishlist> wishlists;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "item_buyers",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
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
