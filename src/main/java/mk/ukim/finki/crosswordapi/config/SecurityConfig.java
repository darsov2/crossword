package mk.ukim.finki.crosswordapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mk.ukim.finki.crosswordapi.model.CrosswordUser;
import mk.ukim.finki.crosswordapi.service.auth.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final String LOGIN_PROCESSING_URL = "/api/auth/login";
  private final String LOGOUT_URL = "/api/auth/logout";

  private final UserDetailsService userDetailsService;

  public SecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
        )
        .userDetailsService(userDetailsService)
        .authorizeHttpRequests((requests) -> requests
            .anyRequest()
            .permitAll()
        )
        .formLogin(form -> form
            .loginProcessingUrl(LOGIN_PROCESSING_URL)
            .usernameParameter("username")
            .passwordParameter("password")
            .successHandler((request, response, authentication) -> {
              response.setContentType("application/json");
              response.setCharacterEncoding("UTF-8");

              CrosswordUser userDetails = (CrosswordUser) authentication.getPrincipal();
              Map<String, Object> responseData = new HashMap<>();

              responseData.put("username", userDetails.getUsername());
              responseData.put("firstName", userDetails.getFirstName());
              responseData.put("lastName", userDetails.getLastName());
              responseData.put("email", userDetails.getEmail());
              responseData.put("id", userDetails.getId());
              // Add any other user data you want to return

              ObjectMapper mapper = new ObjectMapper();
              response.getWriter().write(mapper.writeValueAsString(responseData));
            })
            // Add failure handler for better error messages
            .failureHandler((request, response, exception) -> {
              response.setContentType("application/json");
              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

              Map<String, String> responseData = new HashMap<>();
              responseData.put("error", "Authentication failed");
              responseData.put("message", exception.getMessage());

              ObjectMapper mapper = new ObjectMapper();
              response.getWriter().write(mapper.writeValueAsString(responseData));
            })
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl(LOGOUT_URL)
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .permitAll()
        );

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:5173")); // Allow frontend origin
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
    configuration.setAllowCredentials(true); // Allow credentials (cookies, Authorization headers)

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}

