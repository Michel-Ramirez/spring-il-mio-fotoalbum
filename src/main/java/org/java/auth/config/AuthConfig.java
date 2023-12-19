package org.java.auth.config;

import org.java.auth.db.serv.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().cors().disable().authorizeHttpRequests().requestMatchers("/api/v1.0/**").permitAll()
				.requestMatchers("/**").permitAll()
				.requestMatchers("/pictures/**").hasAnyAuthority("ADMIN", "SUPERADMIN")
				.requestMatchers("/picture/**").hasAnyAuthority("ADMIN", "SUPERADMIN")
				.requestMatchers("/picture/create").hasAnyAuthority("ADMIN", "SUPERADMIN")
				.requestMatchers("/categories/**").hasAnyAuthority("ADMIN", "SUPERADMIN")
				.requestMatchers("/category/**").hasAnyAuthority("ADMIN", "SUPERADMIN")
				.requestMatchers("/messages/**").hasAnyAuthority("ADMIN", "SUPERADMIN")
				.requestMatchers("/message/**").hasAnyAuthority("ADMIN", "SUPERADMIN")
				.requestMatchers("/css/**", "/js/**", "/webjars/**").permitAll()
				.and().formLogin().and().logout();

		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new UserService();
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

}
