package com.tra22.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.tra22.security.constant.user.Permission.ADMIN_CREATE;
import static com.tra22.security.constant.user.Permission.ADMIN_DELETE;
import static com.tra22.security.constant.user.Permission.ADMIN_READ;
import static com.tra22.security.constant.user.Permission.ADMIN_UPDATE;
import static com.tra22.security.constant.user.Permission.MANAGER_CREATE;
import static com.tra22.security.constant.user.Permission.MANAGER_DELETE;
import static com.tra22.security.constant.user.Permission.MANAGER_READ;
import static com.tra22.security.constant.user.Permission.MANAGER_UPDATE;
import static com.tra22.security.constant.user.Role.ADMIN;
import static com.tra22.security.constant.user.Role.MANAGER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(req -> req
          //public access
          .requestMatchers(
                  "/api/v1/auth/**",
                  "/v2/api-docs",
                  "/v3/api-docs",
                  "/v3/api-docs/**",
                  "/swagger-resources",
                  "/swagger-resources/**",
                  "/configuration/ui",
                  "/configuration/security",
                  "/swagger-ui/**",
                  "/webjars/**",
                  "/swagger-ui.html"
          ).permitAll()
          //all management access
          .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
          //all management access with Method GET
          .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
          //all management access with Method POST
          .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
          //all management access with Method PUT
          .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
          //all management access with Method DELETE
          .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
          //otherwise need to authenticated first before access
          .anyRequest()
            .authenticated()
        )
        //disable session with STATELESS
        .sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        //set provide for authentication
        .authenticationProvider(authenticationProvider)
        //set filter for logic check token and authenticate
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        //logout to new endpoint and set logout handler
        .logout(logout -> logout
          .logoutUrl("/api/v1/auth/logout")
          .addLogoutHandler(logoutHandler)
          .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        );
    return http.build();
  }
}
