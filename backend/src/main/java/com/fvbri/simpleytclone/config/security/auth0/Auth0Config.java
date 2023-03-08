package com.fvbri.simpleytclone.config.security.auth0;

import com.auth0.client.auth.AuthAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Auth0Config {
    private final String DOMAIN;
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;

    public Auth0Config(
            @Value("${auth0.domain}") String DOMAIN,
            @Value("${auth0.client-id}") String CLIENT_ID,
            @Value("${auth0.client-secret}") String CLIENT_SECRET) {
        this.DOMAIN = DOMAIN;
        this.CLIENT_ID = CLIENT_ID;
        this.CLIENT_SECRET = CLIENT_SECRET;
    }

    @Bean
    public AuthAPI authAPI() {
        return new AuthAPI(
                "dev-6k2wnavd.us.auth0.com",
                "dgMSgjp87fvNTjZOrZyT3gJqabojAtyW",
                "GZ-TsSCB_SN8HH98b6Y5kc0pj-ildIRp3zXk0Bz3C6rGgc6urzuJQ61orGALMEwQ");
    }
}
