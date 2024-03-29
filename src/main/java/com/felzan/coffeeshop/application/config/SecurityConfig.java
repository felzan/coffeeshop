package com.felzan.coffeeshop.application.config;

import static com.felzan.coffeeshop.adapters.web.ConstantsController.MERCHANT;
import static com.felzan.coffeeshop.adapters.web.ConstantsController.USER;
import static com.felzan.coffeeshop.adapters.web.ConstantsController.WHITE_LABEL;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.felzan.coffeeshop.adapters.web.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  JwtTokenFilter jwtTokenFilter;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().and().csrf().disable()
        .headers().frameOptions().disable()
        .and()
        .authorizeRequests()
        .antMatchers(POST, "/**/" + USER + "/**").permitAll()
        .antMatchers(GET, "/**/" + MERCHANT + "/**").permitAll()
        .antMatchers(POST, "/**/" + MERCHANT + "/**").permitAll()
        .antMatchers(GET, "/**/" + WHITE_LABEL + "/**").permitAll()
        .antMatchers(POST, "/**/" + WHITE_LABEL + "/**").permitAll()
        .antMatchers(PUT, "/**/" + WHITE_LABEL + "/**").permitAll()
        .antMatchers(PATCH, "/**/" + USER + "/**").permitAll()
        .antMatchers("/h2-console/**/**").permitAll()
        .anyRequest().permitAll()
        //TODO: use authentication
//        .anyRequest().authenticated()
        .and().sessionManagement().sessionCreationPolicy(STATELESS)
        .and().logout().permitAll()
        .and()
        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
