package com.audgnssweet.config;

import com.audgnssweet.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Autowired
    public SecurityConfig (CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/main", "/auth/**")
            .permitAll()
            .antMatchers("/members/**").hasRole("USER")
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/auth/loginform")
            .usernameParameter("username")  //input의 name 지정. (ajax 통신 x)
            .passwordParameter("password")
            .loginProcessingUrl("/authenticate")    //이 주소로 오면 니가 대신 가로채서 로그인해줘.
            .failureForwardUrl("/auth/loginerror?login_error=1")
            .defaultSuccessUrl("/members/welcome", true)
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
