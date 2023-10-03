package org.workspace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class MySecurityConfig {

//    @Bean
//    UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails user = User.withUsername("tom")
//                .password(passwordEncoder().encode("cruise"))
//                .authorities("read").build();
//        userDetailsManager.createUser(user);
//        return userDetailsManager;
//    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("Before my security config");
        http.httpBasic();
        http.authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/hello")).authenticated()
                .anyRequest().denyAll();
        http.addFilterAfter(new MySecurityFilter(), BasicAuthenticationFilter.class);
        System.out.println("After my security config");
        return http.build();
    }
}
