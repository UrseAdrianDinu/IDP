package com.core.web.rest.dto.responseDtos;

import com.core.web.rest.dto.modelDtos.UserDto;

import java.util.List;

public class GetUsersResponseDto {
    private long totalCount;
    private List<UserDto> users;

    public GetUsersResponseDto(long totalCount, List<UserDto> users) {
        this.totalCount = totalCount;
        this.users = users;
    }

    public GetUsersResponseDto() {
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
