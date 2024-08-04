package com.alchives.alchiveserver.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.alchives.alchiveserver.repository.UserRepo;
import com.alchives.alchiveserver.security.jwt.JWTAuthenticationFilter;
import com.alchives.alchiveserver.security.jwt.JWTAuthorizationFilter;
import com.alchives.alchiveserver.security.jwt.SecurityConstants;
import com.alchives.alchiveserver.security.user.UserDetailService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
  @Autowired
	UserRepo userRepo;

	@Value("${cors.allowedOrigins}")
	private String allowedOrigins;

  private UserDetailService userDetailsService;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public WebSecurity(UserDetailService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

  @Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

  @Override
  protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.authorizeRequests()
			// .antMatchers(SecurityConstants.ERROR_URL).permitAll()
			.antMatchers(SecurityConstants.PUBLIC_URL).permitAll()
			// .antMatchers(SecurityConstants.SERVICE_URL).authenticated()
			.antMatchers(SecurityConstants.SERVICE_URL).permitAll()
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager(), userRepo))
			.addFilter(new JWTAuthorizationFilter(authenticationManager()))
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.csrf()
			.disable();
  }

  @Bean
	@Override
	public AuthenticationManager authenticationManager () throws Exception {
		return super.authenticationManager();
	}

  @Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		configuration.addAllowedHeader("Content-Type");
		configuration.addAllowedHeader("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
