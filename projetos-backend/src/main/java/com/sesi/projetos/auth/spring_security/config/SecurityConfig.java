package com.sesi.projetos.auth.spring_security.config;

import com.sesi.projetos.auth.jwt.filter.JwtFilter;
import com.sesi.projetos.auth.util.SecurityParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    ClearSiteDataHeaderWriter headerWriter = new ClearSiteDataHeaderWriter(
            ClearSiteDataHeaderWriter.Directive.COOKIES, ClearSiteDataHeaderWriter.Directive.STORAGE, ClearSiteDataHeaderWriter.Directive.CACHE
    );
    HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(headerWriter);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                SecurityParameters.PUBLIC_ENDPOINTS.toArray(new String[0])
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, SecurityParameters.ADMIN_POST_ENDPOINTS.toArray(new String[0])).hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, SecurityParameters.ADMIN_GET_ENDPOINTS.toArray(new String[0])).hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, SecurityParameters.PROFESSOR_POST_ENDPOINTS.toArray(new String[0])).hasRole("PROFESSOR")
//                        .requestMatchers(HttpMethod.GET, SecurityParameters.PROFESSOR_GET_ENDPOINTS.toArray(new String[0])).hasRole("PROFESSOR")
//                        .requestMatchers(HttpMethod.POST, SecurityParameters.ALUNO_POST_ENDPOINTS.toArray(new String[0])).hasRole("ALUNO")
//                        .requestMatchers(HttpMethod.GET, SecurityParameters.ALUNO_GET_ENDPOINTS.toArray(new String[0])).hasRole("ALUNO")
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT))
                        .invalidateHttpSession(true)
                        .deleteCookies("sessionToken", "sessionCookie")
                        .addLogoutHandler(clearSiteData)
                )
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge((SecurityParameters.TOKEN_COOKIE_LONG_MAX_AGE_SECS));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(SecurityParameters.ENCODER_STRENGTH));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
