package com.svlada.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration for default PasswordEncoderConfig that will be used for encoding passwords.
 *
 * @author vladimir.stankovic@vicert.com
 */
@Configuration
public class PasswordEncoderConfig {
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
