package com.autarklab.Oauth.config;

import com.autarklab.Oauth.service.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class DefaultSecurityConfig {

    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    public DefaultSecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( authorizeRequests ->
                authorizeRequests.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Autowired
    public void bindAuthenticationProvider(AuthenticationManagerBuilder managerBuilder){
        managerBuilder.authenticationProvider(customAuthenticationProvider);
    }
}
