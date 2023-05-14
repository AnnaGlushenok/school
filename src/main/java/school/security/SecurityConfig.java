package school.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import school.model.JWTFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests(
//                        auth -> auth
//                                .requestMatchers("/", "/auth", "/registration").permitAll()//то что можно без авторизации
//                                .anyRequest().authenticated()
//                                .and()
//                                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                );
//        http.authorizeHttpRequests(req -> req
//                       // .requestMatchers("/**", "/styles/**", "/scripts/**").permitAll()
//                        .requestMatchers("/auth").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin()
//                .loginPage("/auth")
//                .defaultSuccessUrl("/")
//                .and()
//                .logout().permitAll();
        http.httpBasic().disable()
                .csrf()
                .and()
                .authorizeHttpRequests(req -> req.requestMatchers("/auth").permitAll())
        ;
        return http.build();
    }
}
