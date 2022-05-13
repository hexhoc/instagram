package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found by username: " + username));

        return build(user);
    }

    public User loadUserById(Long id) throws UsernameNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("User not found by username: " + id));

        return build(user);
    }

    public static User build(User user) {
       List<GrantedAuthority> authorities =  user.getRole().stream()
               .map(userRole -> new SimpleGrantedAuthority(userRole.name()))
               .collect(Collectors.toList());

       return new User(user.getId(),
               user.getUsername(),
               user.getEmail(),
               user.getPassword(),
               authorities);
    }

}
