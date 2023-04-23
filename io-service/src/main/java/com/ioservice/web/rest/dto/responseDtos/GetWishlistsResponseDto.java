package com.ioservice.web.rest.dto.responseDtos;

import com.ioservice.web.rest.dto.modelDtos.WishlistDto;

import java.util.List;

public class GetWishlistsResponseDto {
    private long totalCount;
    private List<WishlistDto> wishlists;

    public GetWishlistsResponseDto(long totalCount, List<WishlistDto> wishlists) {
        this.totalCount = totalCount;
        this.wishlists = wishlists;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<WishlistDto> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<WishlistDto> wishlists) {
        this.wishlists = wishlists;
    }
}
