package com.game.football.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN", "MANAGER","COACH","REFEREE","PLAYER", "USER")
                .build();

        UserDetails leagueManager = User.builder()
                .username("manager")
                .password("{noop}manager")
                .roles("MANAGER", "USER")
                .build();

        UserDetails referee = User.builder()
                .username("referee")
                .password("{noop}referee")
                .roles("REFEREE", "USER")
                .build();

        UserDetails coach = User.builder()
                .username("coach")
                .password("{noop}coach")
                .roles("COACH", "USER")
                .build();

        UserDetails player = User.builder()
                .username("player")
                .password("{noop}player")
                .roles("PLAYER", "USER")
                .build();
        return new InMemoryUserDetailsManager(admin, leagueManager, referee, coach, player);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configure -> configure
                .requestMatchers(HttpMethod.POST, "/leagues").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/leagues/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/leagues/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/leagues-managers/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/leagues-managers/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/leagues-managers/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/actuator/info").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/actuator/health").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/referees/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/referees/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/referees/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/teams/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/teams/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/teams/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/coaches/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/coaches/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/coaches/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/players/**").hasRole("COACH")
                .requestMatchers(HttpMethod.PUT, "/players/**").hasRole("COACH")
                .requestMatchers(HttpMethod.DELETE, "/players/**").hasRole("COACH")
                .requestMatchers(HttpMethod.POST, "/matches/play/**").hasRole("REFEREE")
                .requestMatchers(HttpMethod.POST, "/matches/kick/**").hasRole("PLAYER")
                .requestMatchers(HttpMethod.GET, "/leagues/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/leagues-managers/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/referees/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/teams/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/coaches/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/players/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/matches/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/goals/**").hasRole("USER")

        );
        http.httpBasic();
        http.csrf().disable();
        return http.build();
    }
}
