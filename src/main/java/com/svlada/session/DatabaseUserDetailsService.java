package com.svlada.session;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * DatabaseUserDetailsService
 *
 * @author vladimir.stankovic@vicert.com
 */
@Component
public class DatabaseUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return new User("test", "$2a$11$mzCGQ05Z4LZuJoLIhontwOX0q5.NBa70HXT3in77eZOpdBZwRJWrG", authorities);
    }
}
