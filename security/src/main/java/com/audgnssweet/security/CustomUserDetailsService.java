package com.audgnssweet.security;

import com.audgnssweet.dto.User;
import com.audgnssweet.dto.UserRole;
import com.audgnssweet.service.UserDbService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDbService userDbService;

    @Autowired
    public CustomUserDetailsService(UserDbService userDbService) {
        this.userDbService = userDbService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User foundUser = userDbService.getUser(username);
        if(foundUser == null) {
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다");
        }

        com.audgnssweet.security.UserDetails userDetails = new com.audgnssweet.security.UserDetails();
        userDetails.setUsername(foundUser.getUsername());
        userDetails.setPassword(foundUser.getPassword());

        List<UserRole> userRoles = userDbService.getUserRoles(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (!userRoles.isEmpty()) {
            userRoles.forEach(userRole -> {
                authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
            });
        }

        userDetails.setAuthorities(authorities);
        userDetails.setEnabled(true);
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setCredentialsNonExpired(true);

        return userDetails;
    }

}
