package org.workspace.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin();
        httpSecurity.authorizeHttpRequests();
        httpSecurity.authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher( "/couponapi/coupons/**", HttpMethod.GET.name()))
                .hasAnyRole("USER","ADMIN")
                .requestMatchers(new AntPathRequestMatcher( "/showGetCoupon", HttpMethod.GET.name()))
                .hasAnyRole("USER","ADMIN")
                .requestMatchers(new AntPathRequestMatcher( "/getCoupon", HttpMethod.GET.name()))
                .hasAnyRole("USER","ADMIN")
                .requestMatchers(new AntPathRequestMatcher( "/showCreateCoupon", HttpMethod.GET.name()))
                .hasAnyRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher( "/createCoupon", HttpMethod.GET.name()))
                .hasAnyRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher( "/createResponse", HttpMethod.GET.name()))
                .hasAnyRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher( "/showCreateCoupon", HttpMethod.GET.name()))
                .hasAnyRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher( "/saveCoupon", HttpMethod.POST.name()))
                .hasAnyRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/couponapi/coupons", HttpMethod.POST.name()))
                .hasAnyRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/getCoupon", HttpMethod.POST.name()))
                .hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable();
        return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(){
        var authManager = new DaoAuthenticationProvider();
        authManager.setUserDetailsService(userDetailsService);
        authManager.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authManager);
    }
}
