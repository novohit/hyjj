package com.hyjj.hyjjservice.config;

import com.hyjj.security.filter.TokenAuthenticationFilter;

import com.hyjj.security.filter.VerificationCodeFilter;
import com.hyjj.security.security.*;
import com.hyjj.util.tool.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private RedisUtil redisUtil;

    private UserDetailsService userDetailsService;

    private TokenManager tokenManager;

    private DefaultPasswordEncoder defaultPasswordEncoder;

    private RedisTemplate redisTemplate;

    @Autowired
    public WebSecurityConfig(VerificationCodeFilter verificationCodeFilter, UserDetailsService userDetailsService, TokenManager tokenManager, DefaultPasswordEncoder defaultPasswordEncoder, RedisTemplate redisTemplate) {

        this.userDetailsService = userDetailsService;
        this.tokenManager = tokenManager;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint()).and()
                .csrf().disable()
                .authorizeRequests().antMatchers("/company/list").hasRole("User")
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/user/logout")
                .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate)).and()


                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate));
        //addFilterAfter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate),UsernamePasswordAuthenticationFilter.class).httpBasic();
        http.cors(Customizer.withDefaults());

        http.addFilterBefore(new VerificationCodeFilter(redisUtil), UsernamePasswordAuthenticationFilter.class);
        http.formLogin()
                .loginProcessingUrl("/user/login").permitAll().successHandler(new LoginAuthenticationSuccessHandler(authenticationManager(), tokenManager, redisTemplate))
                .failureHandler(new LoginAuthenticationFailureHandler());
        http.userDetailsService(userDetailsService);


    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**",
                "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/captcha/get", "/companyimage/**", "/publishimage/**","urge/update"
        );


    }

    //跨域配置，spring security要求cors配置必需在它之前，否则都会遭到拒绝
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        log.info("spring security cors已开启");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
