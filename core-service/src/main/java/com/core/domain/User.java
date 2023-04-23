package com.core.domain;


import java.util.Date;
import java.util.Set;

public class User {

    private Long id;


    private String name;


    private String password;


    private String email;


    private Date dob;



    private String phone;


    private Address address;



    private Set<Notification> notifications;



    private Set<Wishlist> wishlists;



    private Set<Item> items;


    Set<Group> shared_groups;

    Set<Item> baught_items;


    private Set<Group> created_groups;


    public User() {
    }

    public User(Long id, String name, String password, String email, Date dob, String phone, Address address, Set<Notification> notifications, Set<Wishlist> wishlists, Set<Item> items, Set<Group> shared_groups, Set<Item> baught_items, Set<Group> created_groups) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.notifications = notifications;
        this.wishlists = wishlists;
        this.items = items;
        this.shared_groups = shared_groups;
        this.baught_items = baught_items;
        this.created_groups = created_groups;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(Set<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Group> getShared_groups() {
        return shared_groups;
    }

    public void setShared_groups(Set<Group> shared_groups) {
        this.shared_groups = shared_groups;
    }

    public Set<Item> getBaught_items() {
        return baught_items;
    }

    public void setBaught_items(Set<Item> baught_items) {
        this.baught_items = baught_items;
    }

    public Set<Group> getCreated_groups() {
        return created_groups;
    }

    public void setCreated_groups(Set<Group> created_groups) {
        this.created_groups = created_groups;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", notifications=" + notifications +
                ", wishlists=" + wishlists +
                ", items=" + items +
                ", shared_groups=" + shared_groups +
                ", baught_items=" + baught_items +
                ", created_groups=" + created_groups +
                '}';
    }
}
