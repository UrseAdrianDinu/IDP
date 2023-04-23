package com.ioservice.web.rest.dto.responseDtos;

import com.ioservice.web.rest.dto.modelDtos.ItemDto;

import java.util.List;

public class GetItemsResponseDto {
    private long totalCount;
    private List<ItemDto> items;

    public GetItemsResponseDto(long totalCount, List<ItemDto> items) {
        this.totalCount = totalCount;
        this.items = items;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
