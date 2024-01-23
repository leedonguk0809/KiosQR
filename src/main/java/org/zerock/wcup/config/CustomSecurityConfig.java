package org.zerock.wcup.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.zerock.wcup.security.CustomUserDetailsService;

import javax.sql.DataSource;

/*
CREATE TABLE persistent_logins (
  series VARCHAR(64) NOT NULL,
  username VARCHAR(64) NOT NULL,
  token VARCHAR(64) NOT NULL,
  last_used DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY USING BTREE (series)
);
 */

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableMethodSecurity
public class CustomSecurityConfig {

  private final DataSource dataSource;

  private final CustomUserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }




@Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    
    log.info("---------------------security config---------------------------");

    http.cors(httpSecurityCorsConfigurer -> {
      httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
    });


    http.csrf(config -> config.disable());

    http.formLogin(config -> {
      config.loginPage("/customLogin");

    });

    http.rememberMe(config -> {
      config.tokenValiditySeconds(60 * 60 * 24 * 30);
      config.userDetailsService(userDetailsService);
      config.tokenRepository(persistentTokenRepository());
    });

    return http.build();
  }


  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
    repo.setDataSource(dataSource);
    return repo;
  }


    @Bean
  public CorsConfigurationSource corsConfigurationSource() {

    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOriginPatterns(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
  
}
