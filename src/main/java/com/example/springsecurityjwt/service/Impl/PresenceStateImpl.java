package com.example.springsecurityjwt.service.Impl;

import com.example.springsecurityjwt.entities.AppUser;
import com.example.springsecurityjwt.entities.Response.AdminPresenceMessage;
import com.example.springsecurityjwt.entities.Response.LiveViewCountMessage;
import com.example.springsecurityjwt.entities.Response.OnlineUserDto;
import com.example.springsecurityjwt.entities.UserActivityLog;
import com.example.springsecurityjwt.repository.UserActivityLogRepository;
import com.example.springsecurityjwt.service.PresenceState;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class PresenceStateImpl implements PresenceState {
    private final AtomicInteger liveViewCount = new AtomicInteger(0);
    private final ConcurrentHashMap<String, OnlineUserDto> onlineUsers = new ConcurrentHashMap<>();
    private final UserActivityLogRepository userActivityLogRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void handleConnect(String sessionId, UserDetails userDetails) {
        liveViewCount.incrementAndGet();
        OnlineUserDto onlineUser = toOnlineUser(userDetails);
        onlineUsers.put(sessionId, onlineUser);
        if (onlineUser.getUserId() != null) {
            userActivityLogRepository.insertLog(new UserActivityLog(null, onlineUser.getUserId(), "LOGIN", Instant.now()));
        }
        broadcast();
    }

    @Override
    public void handleDisconnect(String sessionId) {
        OnlineUserDto removed = onlineUsers.remove(sessionId);
        if (removed != null && removed.getUserId() != null) {
            userActivityLogRepository.insertLog(new UserActivityLog(null, removed.getUserId(), "LOGOUT", Instant.now()));
        }
        liveViewCount.updateAndGet(value -> Math.max(0, value - 1));
        broadcast();
    }

    @Override
    public int getLiveViewCount() {
        return liveViewCount.get();
    }

    @Override
    public List<OnlineUserDto> getOnlineUsersSnapshot() {
        return new ArrayList<>(onlineUsers.values());
    }

    private void broadcast() {
        messagingTemplate.convertAndSend("/topic/live-views", new LiveViewCountMessage(getLiveViewCount()));
        messagingTemplate.convertAndSend("/topic/admin-presence", new AdminPresenceMessage(getOnlineUsersSnapshot()));
    }

    private OnlineUserDto toOnlineUser(UserDetails userDetails) {
        if (userDetails instanceof AppUser appUser) {
            return new OnlineUserDto(appUser.getUserId(), appUser.getUsername(), null);
        }
        return new OnlineUserDto(null, userDetails.getUsername(), null);
    }
}
