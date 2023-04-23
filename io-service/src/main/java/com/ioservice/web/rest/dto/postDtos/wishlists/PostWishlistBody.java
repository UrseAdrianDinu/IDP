package com.ioservice.web.rest.dto.postDtos.wishlists;

import java.util.List;

public class PostWishlistBody {
    private PostWishlistDto wishlist;
    private List<Integer> itemIds;

    public PostWishlistBody(PostWishlistDto wishlist, List<Integer> itemIds) {
        this.wishlist = wishlist;
        this.itemIds = itemIds;
    }

    public PostWishlistDto getWishlist() {
        return wishlist;
    }

    public void setWishlist(PostWishlistDto wishlist) {
        this.wishlist = wishlist;
    }

    public List<Integer> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Integer> itemIds) {
        this.itemIds = itemIds;
    }

    @Override
    public String toString() {
        return "PostWishlistBody{" +
                "wishlist=" + wishlist +
                ", itemIds=" + itemIds +
                '}';
    }
}
