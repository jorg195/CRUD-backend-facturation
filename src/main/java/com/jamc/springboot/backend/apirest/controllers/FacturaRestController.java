package com.jamc.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jamc.springboot.backend.apirest.models.entity.Factura;
import com.jamc.springboot.backend.apirest.models.entity.Producto;
import com.jamc.springboot.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	//Accede a JPA y hace la petición a la base de datos y muestra una factura a través de su id
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Factura show(@PathVariable Long id) {
		return clienteService.findFacturaById(id);
	}
	
	//Accede JPA a la base de datos y borra una factura a través de su id
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.deleteFacturaById(id);
	}
	
	//Accede JPA a la base de datos y filtra todos los productos dependiendo el parámetro
	@Secured("ROLE_ADMIN")
	@GetMapping("/facturas/filtrar-producto/{term}")
	public List<Producto> filtrarProductos(@PathVariable String term){
		return clienteService.findByProductoContainingIgnoreCase(term);
	}
	
	//Accede JPA a la base de datos y crea una nueva factura en la base de datos
	@Secured("ROLE_ADMIN")
	@PostMapping("/facturas")
	@ResponseStatus(HttpStatus.CREATED)
	public Factura crear(@RequestBody Factura factura) {
		return clienteService.saveFactura(factura);
	}

}
