package com.core.web.rest.dto.postDtos.items;

import java.util.List;

public class PutItemBuyersBody {
    List<Integer> buyersIds;

    public PutItemBuyersBody(List<Integer> buyersIds) {
        this.buyersIds = buyersIds;
    }

    public PutItemBuyersBody() {
    }

    public List<Integer> getBuyersIds() {
        return buyersIds;
    }

    public void setBuyersIds(List<Integer> buyersIds) {
        this.buyersIds = buyersIds;
    }

    @Override
    public String toString() {
        return "PutItemBuyersBody{" +
                "buyersIds=" + buyersIds +
                '}';
    }
}
