package com.telran.securitystaticusers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("client")
                .password(passwordEncoder().encode("12345.com"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("54321.com"))
                .roles("USER","ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers("/message/hello").hasRole("ADMIN");
//        .anyRequest().denyAll();
    }

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/message/hello").authenticated()
////                .antMatchers(HttpMethod.POST,"/message").authenticated()
//                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN");
//    }
}
