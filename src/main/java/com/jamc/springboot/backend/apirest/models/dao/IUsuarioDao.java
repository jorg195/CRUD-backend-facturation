package com.jamc.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.jamc.springboot.backend.apirest.models.entity.Usuario;

//CLASE PARA ACCEDER A LOS DATOS DE PERSISTENCIA USUARIO
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
	
	
}
