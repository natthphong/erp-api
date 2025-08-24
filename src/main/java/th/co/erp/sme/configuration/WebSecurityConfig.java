package th.co.erp.sme.configuration;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import th.co.erp.sme.model.base.AccessException;
import th.co.erp.sme.model.base.LoggingRequestFilter;


import java.io.IOException;

@Configuration
@Component
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final LoggingRequestFilter loggingRequestFilter ;

    public WebSecurityConfig(LoggingRequestFilter loggingRequestFilter) {
        this.loggingRequestFilter = loggingRequestFilter;
    }


    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/favicon.ico").permitAll();
//                    auth.requestMatchers("/**").permitAll();
                    auth.anyRequest().authenticated();
                })

                .exceptionHandling(e-> e.authenticationEntryPoint(new AccessException()))
                .addFilterBefore(this.loggingRequestFilter , UsernamePasswordAuthenticationFilter.class).build();

    }
}
