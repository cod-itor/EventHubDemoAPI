package com.example.springsecurityjwt.service.Impl;

import com.example.springsecurityjwt.entities.AppUser;
import com.example.springsecurityjwt.entities.Response.AppUserResponse;
import com.example.springsecurityjwt.entities.request.AppUserRequest;
import com.example.springsecurityjwt.repository.AppUserRepository;
import com.example.springsecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@NullMarked
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserRepository.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public AppUserResponse register(AppUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        AppUser appUser = appUserRepository.register(request);
        for (String role : request.getRoles()){
            if (role.equals("ROLE_USER")){
                appUserRepository.insertUserIdAndRoleId(1L, appUser.getUserId());
            }
            if (role.equals("ROLE_ADMIN")){
                appUserRepository.insertUserIdAndRoleId(2L, appUser.getUserId());
            }
        }
        return modelMapper.map(appUserRepository.getUserById(appUser.getUserId()), AppUserResponse.class);
    }

    @Override
    public List<AppUser> getAllUser() {
        return null;
    }
}
