package com.ioservice.web.rest.dto.postDtos.users;

import com.ioservice.web.rest.dto.postDtos.addresses.PostAddressBody;

import java.util.Date;

public class PostUserBody {
    private String name;
    private String phone;
    private String dob;
    private PostAddressBody address;

    public PostUserBody(String name, String phone, String dob, PostAddressBody address) {
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public PostAddressBody getAddress() {
        return address;
    }

    public void setAddress(PostAddressBody address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PostUserBody{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", dob='" + dob + '\'' +
                ", address=" + address +
                '}';
    }
}
