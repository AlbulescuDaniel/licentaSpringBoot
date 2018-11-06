package net.licenta.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource(name = "loginService")
  private UserDetailsService userDetailsService;
  
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable().authorizeRequests()
      .antMatchers(HttpMethod.POST, "/login").permitAll()
    	.and()
      .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
      .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    	
    	 http.cors().configurationSource(request -> {
         CorsConfiguration corsConfiguration = new CorsConfiguration();
         corsConfiguration.addAllowedMethod( "DELETE");
         corsConfiguration.addAllowedMethod( "GET");
         corsConfiguration.addAllowedMethod( "POST");
         corsConfiguration.addAllowedMethod( "PUT");
         corsConfiguration.addAllowedMethod( "PATCH");
         corsConfiguration.applyPermitDefaultValues();
         corsConfiguration.addExposedHeader("Authorization");
         corsConfiguration.addAllowedHeader("Authorization");
         corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
         corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
         corsConfiguration.addAllowedMethod(HttpMethod.PUT);

         return corsConfiguration;
       });
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService);
    }
}