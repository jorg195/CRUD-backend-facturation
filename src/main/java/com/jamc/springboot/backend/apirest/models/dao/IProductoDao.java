package com.jamc.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jamc.springboot.backend.apirest.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{

	//Con QUERY
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);
	
	//Con Consulta Parametrizada de JPA
	public List<Producto> findByNombreContainingIgnoreCase(String term);
	
	//Busca solo al comienzo
	public List<Producto> findByNombreStartingWithIgnoreCase(String term);
	
}
