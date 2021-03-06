package com.example.semprace.security;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{

    private final JwtUserDetailsService userDetailsService;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtRequestFilter jwtRequestFilter;


    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable().authorizeRequests()
                // dont authenticate this particular request
                .antMatchers("/api/authenticate", "/api/courts/types/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/courts").permitAll()
                .antMatchers(HttpMethod.GET, "/api/reservabletypes").permitAll()
                .antMatchers("/api/reservations/anonym/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // all other requests need to be authenticated
                // use .hasAuthority("STH")
                .antMatchers("/api/reservations").hasAnyAuthority("USER", "STAFF", "ADMIN")
                .antMatchers("/api/reservations/**").hasAnyAuthority("USER", "STAFF", "ADMIN")
                .antMatchers("/api/users").hasAnyAuthority( "STAFF", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/reservabletypes").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/reservabletypes").hasAuthority("ADMIN")
                .antMatchers("/api/reservabletypes/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/courts").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/courts").hasAuthority("ADMIN")
                .anyRequest().authenticated().and()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
