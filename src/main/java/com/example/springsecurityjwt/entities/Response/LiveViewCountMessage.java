package com.example.springsecurityjwt.entities.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiveViewCountMessage {
    private int totalCount;
}
