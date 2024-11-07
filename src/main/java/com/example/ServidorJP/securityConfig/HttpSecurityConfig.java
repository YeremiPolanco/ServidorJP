package com.example.ServidorJP.securityConfig;

import com.example.ServidorJP.securityConfig.filter.JwtAuthenticationFilter;
import com.example.ServidorJP.util.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf( csrfConfig -> csrfConfig.disable())
                .sessionManagement( sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( authConfig -> {

                    authConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/auth/public-access").permitAll();
                    authConfig.requestMatchers("/error").permitAll();

                    authConfig.requestMatchers(HttpMethod.POST, "/proveedor")
                            .hasAuthority(Permission.SAVE_PROVEEDOR.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/proveedor/buscar")
                            .hasAuthority(Permission.SEARCH_BY_RUC.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/proveedor")
                            .hasAuthority(Permission.LIST_PROVEEDOR.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/proveedor/{id}")
                            .hasAuthority(Permission.DELETE_BY_RUC.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/proveedor/filtrar-por-fechas")
                            .hasAuthority(Permission.FILTER_BY_DATE.name());

                    authConfig.anyRequest().denyAll();
                });
        return http.build();
    }
}
