package com.ioservice.web.rest.dto.postDtos.groups;

import java.util.List;

public class PutGroupWishlistsBody {
    List<Integer> wishlistIds;

    public PutGroupWishlistsBody(List<Integer> wishlistIds) {
        this.wishlistIds = wishlistIds;
    }

    public PutGroupWishlistsBody() {
    }

    public List<Integer> getWishlistIds() {
        return wishlistIds;
    }

    public void setWishlistIds(List<Integer> wishlistIds) {
        this.wishlistIds = wishlistIds;
    }

    @Override
    public String toString() {
        return "PutGroupWishlistsBody{" +
                "wishlistIds=" + wishlistIds +
                '}';
    }
}
