package com.core.web.rest.dto.modelDtos;

import com.core.domain.Item;
import com.core.domain.User;

import java.util.ArrayList;
import java.util.List;

public class ItemDto {

    private Long id;
    private String name;
    private String details;
    private Integer quantity;
    private String size;
    private String maker;
    private String model;
    private String link;

    private Long ownerId;

    private List<UserDto> buyers;

    public ItemDto(Item item) {
        this.id = item.getItem_id();
        this.name = item.getName();
        this.details = item.getDetails();
        this.quantity = item.getQuantity();
        this.size = item.getSize();
        this.maker = item.getMaker();
        this.model = item.getModel();
        this.link = item.getLink();
        this.ownerId = item.getUser().getId();
        this.buyers = new ArrayList<>();
        for(User user : item.getBuyers()) {
            this.buyers.add(new UserDto(user));
        }
    }

    public ItemDto(Long id, String name, String details, Integer quantity, String size, String maker, String model, String link, Long ownerId, List<UserDto> buyers) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.quantity = quantity;
        this.size = size;
        this.maker = maker;
        this.model = model;
        this.link = link;
        this.ownerId = ownerId;
        this.buyers = buyers;
    }

    public ItemDto() {
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<UserDto> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<UserDto> buyers) {
        this.buyers = buyers;
    }
}
