package vidaplus.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Configurations {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomTokenAuthFilter customTokenAuthFilter) throws Exception {
        return http
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).disable())
                .headers(headers -> headers
                        .frameOptions(FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests(req -> {

                    //Development endpoint
                    req.requestMatchers("/h2-console/**").permitAll();

                    //Endpoints de Suprimentos
                    req.requestMatchers(HttpMethod.GET,"/suprimentos/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/suprimentos/**").authenticated();
                    req.requestMatchers(HttpMethod.PUT, "/suprimentos/**").authenticated();

                    //Endpoints de Leitos
                    req.requestMatchers(HttpMethod.GET,"/leitos/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/leitos/**").authenticated();
                    req.requestMatchers(HttpMethod.PUT, "/leitos/**").authenticated();
                })
                .addFilterBefore(customTokenAuthFilter, AuthenticationFilter.class)
                .build();
    }
    
}
