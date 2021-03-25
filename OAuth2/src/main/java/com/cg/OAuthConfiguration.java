package com.cg;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableOAuth2Sso
public class OAuthConfiguration extends WebSecurityConfigurerAdapter{

	protected void cofigure(HttpSecurity http) throws Exception
    {
        http
        .csrf()
        .disable()
        .authorizeRequests().antMatchers("/login").permitAll()
        .anyRequest().authenticated();
    }
	
}
