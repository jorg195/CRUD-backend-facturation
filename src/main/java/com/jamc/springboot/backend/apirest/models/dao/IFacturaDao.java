package com.jamc.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.jamc.springboot.backend.apirest.models.entity.Factura;

//DAO
public interface IFacturaDao extends CrudRepository<Factura, Long> {

}
