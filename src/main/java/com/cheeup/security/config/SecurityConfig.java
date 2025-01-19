package com.cheeup.security.config;


import com.cheeup.security.auth.service.OauthService;
import com.cheeup.security.filter.JwtAuthenticationFilter;
import com.cheeup.security.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
//    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final OauthSuccessHandler oauthSuccessHandler;
    private final OauthService oauthService;
    private final String CLIENT_URL;


    public SecurityConfig(OauthSuccessHandler oauthSuccessHandler, OauthService oauthService, @Value("${client.url}") String clientUrl) {
        this.oauthSuccessHandler = oauthSuccessHandler;
        this.oauthService = oauthService;
        this.CLIENT_URL = clientUrl;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http
            , JwtAuthorizationFilter jwtAuthorizationFilter
            , JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {


        // CSRF 설정 (Cross-site request forgery)
        http
                .csrf(csrf -> csrf.disable());


        // 세션X
        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // CORS 설정
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));// CORS 설정 추가


        // X-Frame-Option 에 관한 설정
        // SameOrigin으로 적용하여 H2-Console을 비롯한 iframe 동작
        http
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));


        // (시큐리티)인가 URL Pattern을 정의하는 설정
        http
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/h2-console/**").hasRole("ADMIN")
//                        .requestMatchers("/auth/**").permitAll()
//                        .requestMatchers("/login/**").permitAll()
//                        .requestMatchers("/*").permitAll()
//                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated());


        /*
         oAuth 설정
         */
        http
                .oauth2Login((oauth2) ->
                        oauth2
                                .userInfoEndpoint((config) -> config.userService(oauthService))
                                .successHandler(oauthSuccessHandler));


        // HttpBasicAuthentication 비활성화
        http
                .httpBasic(AbstractHttpConfigurer::disable);

        // formLogin 에 대한 설정
        http
                .formLogin(AbstractHttpConfigurer::disable);

        // 인증,인가에 대한 오류처리를 커스텀하여 적용
//        http.exceptionHandling(configurer -> configurer
//                .authenticationEntryPoint(customAuthenticationEntryPoint)
//                .accessDeniedHandler(customAccessDeniedHandler));

        http
                .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthorizationFilter, jwtAuthenticationFilter.getClass());

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(CLIENT_URL);
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 허용할 HTTP 메서드
        configuration.setAllowedHeaders(Arrays.asList("*")); // 모든 헤더 허용
        configuration.setAllowCredentials(true); // 쿠키 허용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}