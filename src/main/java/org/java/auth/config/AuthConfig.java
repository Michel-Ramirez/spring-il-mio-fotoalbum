package org.java.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().cors().disable().authorizeHttpRequests().requestMatchers("/**").permitAll()
				.requestMatchers("/api/**").permitAll().and().formLogin().and().logout();

		return http.build();
	}

}
