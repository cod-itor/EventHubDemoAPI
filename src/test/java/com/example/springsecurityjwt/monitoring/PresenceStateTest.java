package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.entities.Response.AdminPresenceMessage;
import com.example.springsecurityjwt.entities.Response.LiveViewCountMessage;
import com.example.springsecurityjwt.repository.UserActivityLogRepository;
import com.example.springsecurityjwt.service.Impl.PresenceStateImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PresenceStateTest {

    @Mock
    private UserActivityLogRepository userActivityLogRepository;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    private PresenceStateImpl presenceState;

    @BeforeEach
    void setUp() {
    presenceState = new PresenceStateImpl(userActivityLogRepository, messagingTemplate);
    }

    @Test
    void handleConnectUpdatesStateAndBroadcasts() {
        presenceState.handleConnect("session-1", User.withUsername("taylor").password("n/a").authorities("USER").build());

        assertThat(presenceState.getLiveViewCount()).isEqualTo(1);
        assertThat(presenceState.getOnlineUsersSnapshot()).hasSize(1);

        verify(messagingTemplate).convertAndSend(eq("/topic/live-views"), argThat(isViewCount(1)));
        verify(messagingTemplate).convertAndSend(eq("/topic/admin-presence"), argThat(isAdminPresence(1)));
    }

    private ArgumentMatcher<LiveViewCountMessage> isViewCount(int expected) {
        return message -> message != null && message.getTotalCount() == expected;
    }

    private ArgumentMatcher<AdminPresenceMessage> isAdminPresence(int expectedSize) {
        return message -> message != null && message.getOnlineUsers() != null
                && message.getOnlineUsers().size() == expectedSize;
    }
}
