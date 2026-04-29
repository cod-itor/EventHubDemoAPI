package com.example.springsecurityjwt.entities.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresenceSnapshotResponse {
    private int liveViewCount;
    private List<OnlineUserDto> onlineUsers;
}
