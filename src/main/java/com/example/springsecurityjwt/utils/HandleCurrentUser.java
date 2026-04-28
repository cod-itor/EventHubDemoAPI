package com.example.springsecurityjwt.utils;

import com.example.springsecurityjwt.entities.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class HandleCurrentUser {

    public Long getUserIdOfCurrentUser() {
        try {
            AppUser appUser = (AppUser) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            System.out.println(">>> Auth: " + SecurityContextHolder.getContext().getAuthentication());
            System.out.println(">>> Principal: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            Long userId = appUser.getUserId();
            System.out.println(">>> UserId: " + userId);
            return userId;
        } catch (Exception e) {
            System.out.println(">>> HandlerCurrentUser ERROR: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public String getEmailOfCurrentUser() {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = appUser.getUsername();
        System.out.println(email);
        return email;
    }

    public AppUser getCurrentUser() {
        return (AppUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }
}