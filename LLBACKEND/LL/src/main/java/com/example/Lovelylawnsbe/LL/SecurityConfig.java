//package com.example.Lovelylawnsbe.LL;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private CustomUserDetailService userDetailService;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
//        requestCache.setMatchingRequestParameterName(null);
//        http
//        .csrf(AbstractHttpConfigurer::disable)
//        .authorizeHttpRequests((authorize) -> authorize
//                .requestMatchers("/index").permitAll()
//                .requestMatchers("/USER/**").hasAuthority("USER")
//                .requestMatchers("/ADMIN/**").hasAuthority("ADMIN")
//                .anyRequest().authenticated()
//        )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                ).exceptionHandling((x) -> x.accessDeniedPage("/403"))
//                .logout((logout) -> logout.permitAll())
//                .requestCache((cache) -> cache
//                        .requestCache(requestCache)
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}