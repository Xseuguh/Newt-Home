package org.wtg.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.wtg.services.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
     
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	//http.httpBasic().disable();//auth de base desactivé
    	http.csrf().disable();
    	http.authorizeRequests().antMatchers("/css/**").permitAll();
    	http.authorizeRequests().antMatchers("/js/**").permitAll();
    	http.authorizeRequests().antMatchers("/Accueil").permitAll();
    	http.authorizeRequests().antMatchers("/cgu").permitAll();
    	http.authorizeRequests().antMatchers("/aPropos").permitAll();
    	http.authorizeRequests().antMatchers("/register").permitAll();
    	
    	
        http.authorizeRequests()
            .antMatchers("/profil/**").authenticated()
            .antMatchers("/messagerie/**").authenticated()
            .antMatchers("/ads/**").authenticated()
            .antMatchers("/Admin/**").hasAuthority("Admin")
            .antMatchers("/annonce/**").authenticated()
            .and()
            .formLogin()
            	.loginPage("/register")
            	.loginProcessingUrl("/login")
                .usernameParameter("mail")
                .passwordParameter("password")
                .defaultSuccessUrl("/profil/")
                .permitAll()
            .and()
            .logout().logoutSuccessUrl("/Accueil").permitAll();
    }

}



