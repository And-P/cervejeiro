package com.umbrella.cervejeiro.config;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.umbrella.cervejeiro.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses= AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
		//Authenticação feita em memória para facilitar configurações iniciais do security
		/*auth.inMemoryAuthentication()
			.withUser("admin")
			.password("admin")
			.roles("CADASTRO_CLIENTE");
		*/	
	
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**")
			.antMatchers("/images/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers("/cidades/nova").hasRole("CADASTRAR_CIDADE")
				.antMatchers("/usuarios/**").hasRole("CADASTRAR_USUARIO")
				.anyRequest().authenticated()
				//.anyRequest().denyAll()
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
				.exceptionHandling()
				.accessDeniedPage("/403")
			.and()
				.sessionManagement()
				.invalidSessionUrl("/login");
			
		//adiciona limite de sessões permitidas por usuário
			/*.and()
			.sessionManagement()
			.maximumSessions(1)
			.expiredUrl("/login")*/
	}
	
=======
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

>>>>>>> 8ff219625608587fecc7d009552241817f9d8816
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
