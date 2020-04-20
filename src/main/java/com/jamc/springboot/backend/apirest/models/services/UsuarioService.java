package com.jamc.springboot.backend.apirest.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jamc.springboot.backend.apirest.models.dao.IUsuarioDao;
import com.jamc.springboot.backend.apirest.models.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{

	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	//Carga el usuario y lo regresa con todos sus detalles
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		
		//Busca al usuario por su nombre
		Usuario usuario = usuarioDao.findByUsername(username);
		
		//Bad Credentials
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario " + username + " en el sistema");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario : " + username + " en el sistema");
		}
		
		//Se obtienen los roles del usuario
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role : " + authority.getAuthority())) //Muestra los Role(s)
				.collect(Collectors.toList());;
				
		System.out.println("Roles: " + usuario.getRoles());
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	//Busca un usuario por su nombre de usuario
	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}


}
