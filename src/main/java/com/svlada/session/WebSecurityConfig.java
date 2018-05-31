package com.svlada.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * WebSecurityConfig
 *
 * @author vladimir.stankovic@vicert.com
 */
@Configuration
@EnableWebSecurity
@EnableJdbcHttpSession
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final AuthenticationProvider provider;

    public WebSecurityConfig(final RestAuthenticationEntryPoint restAuthenticationEntryPoint,
        final AuthenticationProvider provider) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.provider = provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(restAuthenticationEntryPoint)
            .and()
                .formLogin()
                .successHandler(new SessionAuthenticationSuccessHandler())
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
            .and()
                .logout()
                    .defaultLogoutSuccessHandlerFor(new HttpStatusReturningLogoutSuccessHandler(),
                        new AntPathRequestMatcher("/logout"))
            .and()
                .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/h2/**").permitAll()
            .and()
                .authorizeRequests().antMatchers("/api/**").hasAnyRole("ADMIN")
            .and()
                .requestCache()
                .requestCache(new NullRequestCache());
    }

    // https://github.com/spring-projects/spring-session/commit/6f05c84aa7c1f7c4efcf2c0d3c20709a79b0785f
    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return new HeaderHttpSessionIdResolver("X-Auth-Token");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider);
    }
}
