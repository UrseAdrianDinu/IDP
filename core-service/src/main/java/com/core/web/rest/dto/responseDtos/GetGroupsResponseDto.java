package com.core.web.rest.dto.responseDtos;

import com.core.web.rest.dto.modelDtos.GroupDto;

import java.util.List;

public class GetGroupsResponseDto {
    private long totalCount;
    private List<GroupDto> groups;

    public GetGroupsResponseDto() {

    }

    public GetGroupsResponseDto(long totalCount, List<GroupDto> groups) {
        this.totalCount = totalCount;
        this.groups = groups;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDto> groups) {
        this.groups = groups;
    }
}
