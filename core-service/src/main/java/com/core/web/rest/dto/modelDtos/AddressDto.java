package com.core.web.rest.dto.modelDtos;

import com.core.domain.Address;

public class AddressDto {

    private Long id;
    private String city;
    private String street;
    private String zip;
    private String country;

    public AddressDto(Long id, String city, String street, String zip, String country) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.zip = zip;
        this.country = country;
    }

    public AddressDto(Address address) {
        this.id = address.getAddress_id();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.zip = address.getZip();
        this.country = address.getCountry();
    }

    public AddressDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
