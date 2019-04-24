package com.prueba.crud.server;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

	@RequestMapping("/crear")
	public String Crear() {
		return "Crear registro";
	}
	
	@RequestMapping("/leer")
	public String Leer() {
		return "Leer registro";
	}
	
	@RequestMapping("/actualizar")
	public String Actualizar() {
		return "Actualizar registro";
	}
	
	@RequestMapping("/borrar")
	public String Borrar() {
		return "Borrar registro";
	}
	
	 @Override
		public void configure(HttpSecurity http) throws Exception {
			http.requestMatchers().antMatchers("/leer")
			.and().authorizeRequests()
			.antMatchers("/leer").access("hasRole('USER')")
			.and().requestMatchers().antMatchers("/crear")
			.and().authorizeRequests()
			.antMatchers("/crear").access("hasRole('ADMIN')")
			.and().requestMatchers().antMatchers("/actualizar")
			.and().authorizeRequests()
			.antMatchers("/actualizar").access("hasRole('ADMIN')")
			.and().requestMatchers().antMatchers("/borrar")
			.and().authorizeRequests()
			.antMatchers("/borrar").access("hasRole('ADMIN')");
		}
	 
	 @Bean
	    public RemoteTokenServices LocalTokenService() {
	        final RemoteTokenServices tokenService = new RemoteTokenServices();
	        tokenService.setCheckTokenEndpointUrl("http://localhost:8090/oauth/check_token");
	        tokenService.setClientId("cliente");
	        tokenService.setClientSecret("password");
	        return tokenService;
	    }
}