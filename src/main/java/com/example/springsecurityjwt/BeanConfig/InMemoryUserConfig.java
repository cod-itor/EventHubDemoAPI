package com.example.springsecurityjwt.BeanConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.beans.Encoder;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class InMemoryUserConfig {
    private final PasswordEncoder Encoder;
    //basically you making a user that when the application is running it will have this two user directly
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails user = User.withUsername("user").password(Encoder.encode("123")).roles("USER").build();
        UserDetails admin = User.withUsername("admin").password(Encoder.encode("123")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user , admin);

    }

}
