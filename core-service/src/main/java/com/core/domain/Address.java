package com.core.domain;


public class Address {


    private Long address_id;

    private String city;

    private String street;

    private String zip;

    private String country;

    private User user;


    public Address(Long address_id, String city, String street, String zip, String country, User user) {
        this.address_id = address_id;
        this.city = city;
        this.street = street;
        this.zip = zip;
        this.country = country;
        this.user = user;
    }

    public Address() {
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
