package com.hurrypizza.mine.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hurrypizza.mine.config.security.authn.BodyCredentialAuthenticationFilter;
import com.hurrypizza.mine.config.security.authn.CustomAuthenticationEntryPoint;
import com.hurrypizza.mine.config.security.authn.UserNamePasswordAuthenticationProvider;
import com.hurrypizza.mine.config.security.common.handler.LoginFailureHandler;
import com.hurrypizza.mine.config.security.common.handler.LoginSuccessHandler;
import com.hurrypizza.mine.config.security.token.HttpAuthTokenSupport;
import com.hurrypizza.mine.config.security.token.TokenProvider;
import com.hurrypizza.mine.config.security.token.jwt.JwtTokenPersistFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginSuccessHandler successHandler;
    private final LoginFailureHandler failureHandler;
    private final UserNamePasswordAuthenticationProvider provider;
    private final TokenProvider tokenProvider;
    private final HttpAuthTokenSupport tokenSupport;
    private final ObjectMapper mapper;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationManager authenticationManager)
            throws Exception {
        // @formatter:off
        return http.csrf().disable()
                   .formLogin().disable()
                   .httpBasic().disable()
                   .logout().disable()
                   .headers().frameOptions().sameOrigin().and()
                   .authorizeRequests()
                       .antMatchers("/v1/users/auth/**").permitAll()
                       .antMatchers("/v1/paths/within").permitAll()
                       .antMatchers("/v1/ranks/**").permitAll()
                       .antMatchers("/exception/**").permitAll()
                       .anyRequest().authenticated().and()
                   .exceptionHandling()
                        .authenticationEntryPoint(customAuthenticationEntryPoint).and()
                   .addFilterBefore(bodyCredentialAuthenticationFilter(authenticationManager, mapper),
                                    UsernamePasswordAuthenticationFilter.class)
                   .addFilterBefore(tokenPersistFilter(),
                                    UsernamePasswordAuthenticationFilter.class)
                   .build();
        // @formatter:on
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/h2-console/**",
                "/favicon.ico",
                "/error",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v3/api-docs");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new ProviderManager(authenticationProvider);
    }

    private BodyCredentialAuthenticationFilter bodyCredentialAuthenticationFilter(AuthenticationManager manager, ObjectMapper mapper) {
        var filter = new BodyCredentialAuthenticationFilter("/v1/users/auth/login", manager, mapper);
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        return filter;
    }

    private JwtTokenPersistFilter tokenPersistFilter() {
        return new JwtTokenPersistFilter(tokenSupport, tokenProvider);
    }

}
