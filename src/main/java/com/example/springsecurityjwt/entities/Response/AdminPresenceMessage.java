package com.example.springsecurityjwt.entities.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPresenceMessage {
    private List<OnlineUserDto> onlineUsers;
}
