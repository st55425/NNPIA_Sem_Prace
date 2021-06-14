package com.example.semprace.security;

import java.util.ArrayList;
import java.util.List;

import com.example.semprace.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user != null) {
            List<GrantedAuthority> privileges = new ArrayList<>();
            privileges.add(new SimpleGrantedAuthority(user.getRole().toString()));
            return new User(user.getUsername(), user.getPassword(), privileges);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
