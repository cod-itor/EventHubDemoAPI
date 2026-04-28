package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.entities.AppUser;
import com.example.springsecurityjwt.entities.Response.AppUserResponse;
import com.example.springsecurityjwt.entities.request.AppUserRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
public interface UserService extends UserDetailsService {

    AppUserResponse register(AppUserRequest request);

    List<AppUser> getAllUser();

}
