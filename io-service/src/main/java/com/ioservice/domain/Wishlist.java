package com.ioservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "wishlists")
public class Wishlist {
    /*
   CREATE TABLE wishlists (
       WISHLIST_ID INT PRIMARY KEY NOT NULL,
       NAME VARCHAR ( 50 ) NOT NULL,
       DETAILS VARCHAR ( 50 ),
       USER_ID INT NOT NULL,
       FOREIGN KEY (USER_ID)
           REFERENCES users (user_id)
    );
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id", unique = true)
    private Long wishlist_id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "details")
    private String details;

    @ManyToOne
    @JsonIgnoreProperties("wishlists")
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "wishlist_groups",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    Set<Group> groups;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "wishlist_items",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
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
