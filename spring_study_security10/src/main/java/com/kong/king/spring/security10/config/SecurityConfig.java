package com.kong.king.spring.security10.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER")
                .antMatchers("/guest/**").permitAll()
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/admin/**").hasRole("ADMIN");
        
//        
//        http.formLogin().loginPage("/login").defaultSuccessUrl("/sample/all");
          http.formLogin().defaultSuccessUrl("/sample/all");
//
//        http.formLogin(); //인가/인증에 문제시 로그인 화면
//        http.csrf().disable();
//        http.logout().logoutUrl("/logout").invalidateHttpSession(true);
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication().withUser("user1") //사용자 계정은 user1
//                .password("$2a$10$6sNWloIv7ywknvlvEEd6SOCdgw8wOEspvZShxLwlYMf0Em63j8v/O") //1111 패스워드 인코딩
//                .roles("USER");
//    }


}
