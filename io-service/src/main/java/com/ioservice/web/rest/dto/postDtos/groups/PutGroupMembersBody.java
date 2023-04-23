package com.ioservice.web.rest.dto.postDtos.groups;

import java.util.List;

public class PutGroupMembersBody {
    List<Integer> userIds;

    public PutGroupMembersBody() {
    }

    public PutGroupMembersBody(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "PutGroupMembersBody{" +
                "userIds=" + userIds +
                '}';
    }
}
