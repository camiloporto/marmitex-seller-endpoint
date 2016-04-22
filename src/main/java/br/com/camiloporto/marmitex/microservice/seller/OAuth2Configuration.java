package br.com.camiloporto.marmitex.microservice.seller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Created by ur42 on 22/04/2016.
 */
@Configuration
@EnableWebSecurity
@EnableResourceServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {
}
