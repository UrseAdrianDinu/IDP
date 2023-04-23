package com.ioservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", unique = true)
    private Long group_id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "details")
    private String details;

    @ManyToMany(mappedBy = "shared_groups", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("shared_groups")
    Set<User> members;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnoreProperties("created_groups")
    private User user;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
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
