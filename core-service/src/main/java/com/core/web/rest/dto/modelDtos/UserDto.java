package com.core.web.rest.dto.modelDtos;

import com.core.domain.User;

import java.util.Date;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Date dob;
    private String phone;
    private AddressDto address;

    public UserDto(Long id, String name, String email, Date dob, String phone, AddressDto address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
    }

    public UserDto(User user, AddressDto address) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.dob = user.getDob();
        this.phone = user.getPhone();
        this.address = address;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.dob = user.getDob();
        this.phone = user.getPhone();
        if(user.getAddress() != null) {
            this.address = new AddressDto(user.getAddress());
        } else {
            this.address = null;
        }
    }

    public UserDto() {
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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }
}
