package com.apigateway.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityControllerConfig extends WebSecurityConfigurerAdapter{

//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		List<UserDetails> users = new ArrayList<>();
//		users.add(User.withDefaultPasswordEncoder().username("Jayesh").password("123456").roles("Admin").build());
//
//		return new InMemoryUserDetailsManager(users);
//	}
	@Autowired
	private UserDetailsService userDetailsService ;

	@Bean
	public AuthenticationProvider authenticationProvider() throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		  auth.inMemoryAuthentication()
//		  .withUser("jay").password("{noop}1234567").roles("USER");
//	}
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
			.authorizeRequests().antMatchers("/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").failureUrl("/logout-success").permitAll()
			.and().logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logout-success").permitAll();
		}

	//	@Override
	//	protected void configure(HttpSecurity http) throws Exception {
	//		http.csrf().disable()
	//		.authorizeRequests().antMatchers("/login").permitAll()
	//		.anyRequest().authenticated()
	//		.and()
	//		.formLogin().loginPage("/login").permitAll()
	//		.and().logout().invalidateHttpSession(true)
	//		.clearAuthentication(true)
	//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	//		.logoutSuccessUrl("/logout-success").permitAll();
	//	}
	//	
	//	@Override
	//	protected void configure(HttpSecurity http) throws Exception {
	//		http.authorizeRequests()
	//        .antMatchers("/login")
	//            .permitAll()
	//        .antMatchers("/**")
	//            .hasAnyRole("ADMIN", "USER")
	//        .anyRequest().authenticated()
	//        .and()
	//            .formLogin()
	//            .loginPage("/login")
	//            .defaultSuccessUrl("/home")
	//            .failureUrl("/login?error=true")
	//            .permitAll()
	//        .and()
	//            .logout()
	//            .logoutSuccessUrl("/login?logout=true")
	//            .invalidateHttpSession(true)
	//            .permitAll()
	//        .and()
	//            .csrf()
	//            .disable();
	//	}


}
