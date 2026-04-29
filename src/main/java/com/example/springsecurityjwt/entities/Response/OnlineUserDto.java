package com.example.springsecurityjwt.entities.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUserDto {
    private Long userId;
    private String username;
    private String profilePictureUrl;
}
