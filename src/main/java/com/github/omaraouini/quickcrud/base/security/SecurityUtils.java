package com.github.omaraouini.quickcrud.base.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author aouin
 * Date: 18/01/2025
 * Time: 12:40
 */
public class SecurityUtils {

    public SecurityUtils() {}

    public static String getPrincipalName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String name = authentication.getName();
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                name = userDetails.getUsername();
            }
            return name;
        } else {
            return null;
        }
    }
}
