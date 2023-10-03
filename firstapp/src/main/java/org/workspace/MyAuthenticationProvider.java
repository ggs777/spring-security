package org.workspace;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Before my authentication provider");
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("After my authentication provider");
        if("tom".equals(userName) && "cruise".equals(password)){
            System.out.println("success authentication");
            return new UsernamePasswordAuthenticationToken(userName, password, Arrays.asList());
        }
        else{
            System.out.println("failed authentication");
            throw new BadCredentialsException("Invalid Username or password");
        }


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
