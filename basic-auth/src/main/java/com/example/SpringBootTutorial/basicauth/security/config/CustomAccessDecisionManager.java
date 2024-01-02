//package com.example.SpringBootTutorial.basicauth.security.config;
//
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.PermissionEvaluator;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
//@Component
//public class CustomAccessDecisionManager implements AccessDecisionManager {
//
//    @Override
//    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {
//        if (configAttributes.isEmpty()) {
//            return;
//        }
//
//        boolean hasAnyRole = false;
//
//        for (ConfigAttribute attribute : configAttributes) {
//            for (GrantedAuthority authority : authentication.getAuthorities()) {
//                if (attribute.getAttribute().equals(authority.getAuthority())) {
//                    hasAnyRole = true;
//                    break;
//                }
//            }
//        }
//
//        System.out.println("hasAnyRole: " + hasAnyRole);
//
//        if (!hasAnyRole) {
//            throw new AccessDeniedException("Access is denied");
//        }
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute attribute) {
//        return true;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return true;
//    }
//}
//
