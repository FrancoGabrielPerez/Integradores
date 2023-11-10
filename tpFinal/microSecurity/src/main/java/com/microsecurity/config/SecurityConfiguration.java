package com.microsecurity.config;

import com.microsecurity.security.jwt.JwtConfigurer;
import com.microsecurity.security.jwt.TokenProvider;
import com.microsecurity.service.AuthorityConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    private final TokenProvider tokenProvider;


    /**
     * Password encoder
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        // AGREGAMOS NUESTRA CONFIG DE JWT.
        // http
        //     .apply( securityConfigurerAdapter() );
        http
            .csrf( AbstractHttpConfigurer::disable )
            
            // MANEJAMOS LOS PERMISOS A LOS ENDPOINTS.
            .authorizeHttpRequests( auth -> auth
                .requestMatchers("/monopatines").permitAll()
                .requestMatchers("/api/register").permitAll()
                .requestMatchers("/api/authenticate").permitAll()
                //.requestMatchers("/api/prueba").hasAuthority(AuthorityConstant.ADMIN)
            )
            .anonymous( AbstractHttpConfigurer::disable )
            .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
            .httpBasic( Customizer.withDefaults() );
        return http.build();
    }

    /**
     * Nuestra configuracion de JWT.
     */
    private JwtConfigurer securityConfigurerAdapter() {
        return new JwtConfigurer(tokenProvider);
    }
}
