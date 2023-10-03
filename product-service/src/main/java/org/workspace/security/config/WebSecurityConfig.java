package org.workspace.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher( "/productapi/products/**", HttpMethod.GET.name()))
                .hasAnyRole("USER","ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/productapi/products", HttpMethod.POST.name()))
                .hasAnyRole("ADMIN")
                .and().csrf().disable();
        return httpSecurity.build();
    }

}
