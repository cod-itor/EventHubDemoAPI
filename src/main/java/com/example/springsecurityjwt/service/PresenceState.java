package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.entities.Response.OnlineUserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface PresenceState {
    void handleConnect(String sessionId, UserDetails userDetails);

    void handleDisconnect(String sessionId);

    int getLiveViewCount();

    List<OnlineUserDto> getOnlineUsersSnapshot();
}
