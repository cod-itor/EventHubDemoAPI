package com.example.springsecurityjwt.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActivityLog {
    private Long id;
    private Long userId;
    private String actionType;
    private Instant timestamp;
}
