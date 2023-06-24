package com.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.antMatchers("/register/**").permitAll()
                                .antMatchers("/index").hasRole("ADMIN")
                                .antMatchers("/users/**").hasRole("ADMIN")
                                .antMatchers("/static/css/**").permitAll()
                                .antMatchers("/static/images/**").permitAll()
                                .antMatchers("/static/js/**").permitAll()
                                .antMatchers("/items/**").hasRole("ADMIN")
                                .antMatchers("/sales/**").hasRole("ADMIN")
                                .antMatchers("/dashboard/").hasRole("ADMIN")
                                .antMatchers("/market/**").hasRole("USER")
                                .antMatchers("/buy-item/**").hasRole("USER")
                                .antMatchers("/analyst/**").hasRole("ADMIN")
                                // .anyRequest().authenticated()
                ).formLogin(
                form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                            if (roles.contains("ROLE_ADMIN")) {
                                response.sendRedirect("/index");
                            } else if (roles.contains("ROLE_USER")) {
                                response.sendRedirect("/market");
                            } else {
                                response.sendRedirect("/login");
                            }
                        })
                        .permitAll()
        ).logout(
                logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
        ).exceptionHandling(
            exceptionHandling -> exceptionHandling
                    .accessDeniedPage("/login") 
    );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
