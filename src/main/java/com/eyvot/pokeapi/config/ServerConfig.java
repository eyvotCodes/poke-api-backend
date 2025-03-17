package com.eyvot.pokeapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "server")
public class ServerConfig {

    private final int localPort = 8080;
    private final int productionPort = 5000;
    private int port = Constants.ENVIRONMENT.equals(Constants.ENV_PRODUCTION) ? productionPort : localPort ;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setPort(port);
    }

}
