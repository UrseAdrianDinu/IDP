package com.ioservice.web.rest.dto.modelDtos;

import com.ioservice.domain.Wishlist;
import com.ioservice.web.rest.dto.modelDtos.ItemDto;

import java.util.List;

public class WishlistDto {
    private Long id;
    private String name;
    private String details;
    private Long ownerId;
    private List<ItemDto> items;

    public WishlistDto(Long id, String name, String details, Long ownerId, List<ItemDto> items) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.ownerId = ownerId;
        this.items = items;
    }

    public WishlistDto(Wishlist wishlist, List<ItemDto> itemsDtos) {
        this.id = wishlist.getWishlist_id();
        this.name = wishlist.getName();
        this.details = wishlist.getDetails();
        this.ownerId = wishlist.getUser().getId();
        this.items = itemsDtos;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
