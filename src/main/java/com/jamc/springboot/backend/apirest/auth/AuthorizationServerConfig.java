package com.jamc.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

//AUTHORIZATION SERVER

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired // Encripta una password
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired // Muestra inf. adicional en el payload
	private InfoAdicionalToken infoAdicionalToken;

	// Se usa para el proceso de identificación en el endpoint
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@Bean //Lee data del mismo token
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	//Almacena los datos del usuario (CLAIMS) y la firma de RSA
	//Codifica y decodifica la inf.
	//SIRVE PARA QUE EL TOKEN NO PUEDA SER MANIPULADO
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
		return jwtAccessTokenConverter;
	}

	// -- MÉTODOS DE CONFIGURACIÓN --

	// Permisos de nuestro ENDPOINT con OAuth2
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()") //Permiso a cualquier usuario para poder autenticarse en el endpoint
		.checkTokenAccess("isAuthenticated()"); //Endpoint que verifica el token y su firma "/oauth/check_token"
	}

	// Doble Autenticación (Front-End y el Password)
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("angularapp").secret(passwordEncoder.encode("12345"))
		.scopes("read", "write") //Permisos para el Front 
		.authorizedGrantTypes("password", "refresh_token") //Obtenemos un token renovado antes de que expire
		.accessTokenValiditySeconds(3600) // Limite de exp del
		.refreshTokenValiditySeconds(3600); //Limite de exp del refresh_token
	}

	// ENDPOINT que se encarga de todo el proceso de autenticación y validar el token
	// Si todo sale bien entrega el token al usuario
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhacerChain = new TokenEnhancerChain(); //Une nueva info al token
		// Se agrega la info adicional al token
		tokenEnhacerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter()).tokenEnhancer(tokenEnhacerChain);
	}

}
