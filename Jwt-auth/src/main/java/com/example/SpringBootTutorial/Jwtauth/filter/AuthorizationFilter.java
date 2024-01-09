package com.example.SpringBootTutorial.Jwtauth.filter;

import com.example.SpringBootTutorial.Jwtauth.security.service.AccessTokenManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {
    private final AccessTokenManager accessTokenManager;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("filter works");
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(token);

        try {

            if(token != null && token.startsWith("Bearer ")){
                Claims claims = accessTokenManager.read( token.substring(7));
                String username = claims.get("username", String.class);


                UserDetails userDetails =  userDetailsService.loadUserByUsername(username);

            //     set user details to security context
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities())
                );

                System.out.println("Filter succeed");
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }


        filterChain.doFilter(request,response);
    }
}
