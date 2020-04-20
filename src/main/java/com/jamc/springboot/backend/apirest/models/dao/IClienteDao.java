package com.jamc.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jamc.springboot.backend.apirest.models.entity.Cliente;
import com.jamc.springboot.backend.apirest.models.entity.Region;

//Se crea la clase IClienteDao para acceder a los datos de la tabla 
//Clientes en la base de datos

public interface IClienteDao extends JpaRepository<Cliente, Long>{

	//Obtener listado de regiones
	@Query("from Region")
	public List<Region> findAllRegiones();
	
}
