package com.eyvot.pokeapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(Constants.ALLOWED_ORIGINS);
        config.setAllowedMethods(Constants.ALLOWED_METHODS);
        config.setAllowedHeaders(Constants.ALLOWED_HEADERS);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
