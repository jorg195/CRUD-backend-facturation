package com.jamc.springboot.backend.apirest.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jamc.springboot.backend.apirest.models.dao.IClienteDao;
import com.jamc.springboot.backend.apirest.models.dao.IFacturaDao;
import com.jamc.springboot.backend.apirest.models.dao.IProductoDao;
import com.jamc.springboot.backend.apirest.models.entity.Cliente;
import com.jamc.springboot.backend.apirest.models.entity.Factura;
import com.jamc.springboot.backend.apirest.models.entity.Producto;
import com.jamc.springboot.backend.apirest.models.entity.Region;

@Service
public class ClienteServiceImpl implements IClienteService{

	//Inyectamos el ClienteDao
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	//Regresa la lista de Clientes
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		
		return (List<Cliente>) clienteDao.findAll();
		
	}
	
	//Muestra la información en rango de paginas
		@Override
		@Transactional(readOnly = true)
		public Page<Cliente> findAll(Pageable pageable) {
			return clienteDao.findAll(pageable);
		}

	//Busca un Cliente por su Id
	@Override
	@Transactional
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	//Salva una entidad cliente
	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	//Borra un Cliente
	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	//Regresa el listado de todas las regiones para que
	//Después en angular se puedan seleccionar 
	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		return clienteDao.findAllRegiones(); 
	}

	//Busca la factura por su id
	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	//Guarda la factura 
	@Override
	@Transactional
	public Factura saveFactura(Factura factura) {
		return facturaDao.save(factura);
	}

	//Borra la factura
	@Override
	@Transactional
	public void deleteFacturaById(Long id) {
		facturaDao.deleteById(id);
	}

	//Servicio que filtra los nombres de producto del termino mandado por parámetro
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByProductoContainingIgnoreCase(String term) {
		return this.productoDao.findByNombreContainingIgnoreCase(term);
	}

	

}
