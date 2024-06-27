package com.pfc.revisiones.app.inventario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.pfc.revisiones.app.inventario.security.filter.JwtAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    SecurityFilterChain filtertChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(HttpMethod.GET, "/api/equipos").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/incidencias").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/equipos/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/equipos/nombre/{nombre}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/equipos/tipoProducto/{tipoProducto}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/incidencias/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/incidencias/equipo/{equipoId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/usuarios").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/usuarios/{username}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/albaranes").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/espacios").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/qrcode").permitAll()

                .requestMatchers(HttpMethod.POST, "/api/usuarios/registro").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/equipos/crear").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/incidencias/crear").permitAll()

                .requestMatchers(HttpMethod.DELETE, "/api/equipos/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/incidencias/{id}").permitAll()

                .requestMatchers(HttpMethod.PUT, "/api/equipos/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/incidencias/{id}").permitAll()

                .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
