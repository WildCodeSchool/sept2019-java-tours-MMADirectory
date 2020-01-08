package com.wildcodeschool.MMFCG.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class ClubConfig extends WebSecurityConfigurerAdapter{
	
	//Assignation des différents accès en fonction du rôle
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/")
        .permitAll();

        http
        .authorizeRequests()
        .antMatchers("/admin")
        .hasRole("admin")
        .and()
        .formLogin()
        .and()
        .httpBasic();

        http
        .authorizeRequests()
        .antMatchers("/admin/*")
        .hasRole("admin")
        .and()
        .formLogin()
        .and()
        .httpBasic();

    }
	
	//Création de l'admin et de son rôle 
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
        .inMemoryAuthentication()
        .withUser("admin")
        .password(encoder.encode("admin"))
        .roles("admin");
                
    }

}
