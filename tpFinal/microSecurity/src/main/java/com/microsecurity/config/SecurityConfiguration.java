package com.microsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.microsecurity.security.jwt.JWTFilter;
import com.microsecurity.security.jwt.JwtConfigurer;
import com.microsecurity.security.jwt.TokenProvider;
import com.microsecurity.service.AuthorityConstant;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.microsecurity.security.jwt.JWTFilter;
import com.microsecurity.security.jwt.TokenProvider;
import com.microsecurity.service.AuthorityConstant;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // AGREGAMOS NUESTRA CONFIG DE JWT.
        http.apply(jwtConfigurer());

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/register").permitAll()
                    .requestMatchers("/api/authenticate").permitAll()
                    .requestMatchers("/api/prueba").hasAuthority(AuthorityConstant.ADMIN)
            )
            .anonymous(AbstractHttpConfigurer::disable)
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .httpBasic(Customizer.withDefaults());
    }

    @Bean
    public JwtConfigurer jwtConfigurer() {
        return new JwtConfigurer(tokenProvider);
    }


    @Bean
    public JWTFilter jwtAuthenticationFilter() {
        return new JWTFilter(tokenProvider);
    }
}


    /*
     @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // AGREGAMOS NUESTRA CONFIG DE JWT.
        http.apply(jwtConfigurer());

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/register").permitAll()
                    .requestMatchers("/api/authenticate").permitAll()
                    .requestMatchers("/api/prueba").hasAuthority(AuthorityConstant.ADMIN)
            )
            .anonymous(AbstractHttpConfigurer::disable)
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
            .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
            .httpBasic(Customizer.withDefaults());
    }

    @Bean
    public JwtConfigurer jwtConfigurer() {
        return new JwtConfigurer(tokenProvider);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenProvider);
    }
    */

