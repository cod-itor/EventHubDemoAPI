package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.entities.Response.PresenceSnapshotResponse;
import com.example.springsecurityjwt.service.PresenceState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/presence")
public class PresenceController {
    private final PresenceState presenceState;

    @GetMapping("/snapshot")
    public ResponseEntity<PresenceSnapshotResponse> getSnapshot() {
        PresenceSnapshotResponse response = new PresenceSnapshotResponse(
                presenceState.getLiveViewCount(),
                presenceState.getOnlineUsersSnapshot()
        );
        return ResponseEntity.ok(response);
    }
}
