package com.jamc.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jamc.springboot.backend.apirest.models.entity.Cliente;
import com.jamc.springboot.backend.apirest.models.entity.Region;
import com.jamc.springboot.backend.apirest.models.services.IClienteService;
import com.jamc.springboot.backend.apirest.models.services.IUploadFileService;

//Clase Rest Controlador 

@CrossOrigin(origins = { "http://localhost:4200", "*" }) // Permite el envio de información con Angular
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	// Se inyecta la Interface
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

	// Obtiene la lista de clientes
	@GetMapping("/clientes")
	public List<Cliente> index() {

		return clienteService.findAll();

	}

	// Paginación: Se obtendrán 4 registros por pagina
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) {

		return clienteService.findAll(PageRequest.of(page, 4));

	}

	// Muestra un cliente
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) { // Regresa cualquier tipo de objeto

		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		// Obtiene el cliente de la BD
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			// En caso de que ocurra un error en la consulta, mandará un mensaje de error,
			// el status http 500 y la respuesta
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Si el cliente no existe mandará un mensaje y el status http 404 y la
		// respuesta
		if (cliente == null) {
			response.put("mensaje", "El cliente ID: " + id.toString() + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		// Retorna el cliente si lo encuentra
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	// Crea un cliente
	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();

		// Si encuentra errores
		if (result.hasErrors()) {
			/*
			 * List<String> errors = new ArrayList<>();
			 * 
			 * for(FieldError err: result.getFieldErrors()) { errors.add("El campo '" +
			 * err.getField() + "' " + err.getDefaultMessage()); }
			 */

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		// Conexión a la bd, si falla manda un mensaje de error
		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el registro en la base de datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Crea el cliente
		response.put("mensaje", "El Cliente ha sido creado con éxito!");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Para editar se usa PutMapping
	// Edita un cliente
	@Secured("ROLE_ADMIN")
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {

		Cliente clienteActual = clienteService.findById(id); // Busca el cliente

		Cliente clienteUpdated = null;

		Map<String, Object> response = new HashMap<>();

		// Si encuentra errores
		if (result.hasErrors()) {
			/*
			 * List<String> errors = new ArrayList<>();
			 * 
			 * for(FieldError err: result.getFieldErrors()) { errors.add("El campo '" +
			 * err.getField() + "' " + err.getDefaultMessage()); }
			 */

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		// Si el id que se mando devuelve null, regresa que no se encontró
		if (clienteActual == null) {
			response.put("mensaje",
					"Error: No se pudo editar el Cliente ID: " + id.toString() + ", No existe en la Base de Datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		// Hace la consulta en la BD, y si todo sale bien, se actualiza el cliente, sino
		// manda un error
		try {
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteActual.setRegion(cliente.getRegion());

			clienteUpdated = clienteService.save(clienteActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Mensaje y cliente actualizado
		response.put("mensaje", "El Cliente ha sido actualizado con éxito");
		response.put("cliente", clienteUpdated);

		// Regresa la respuesta
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Borra un cliente
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			//Se busca el cliente por ID
			Cliente cliente = clienteService.findById(id);
			
			//Se obtiene el nombre de la foto
			String nombreFotoAnterior = cliente.getFoto();
			
			//Borra la imagen
			uploadFileService.eliminar(nombreFotoAnterior);

			//Se elimina el cliente
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente en la base de datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//Respuesta
		response.put("mensaje", "El Cliente ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes/upload")
	// Sube una img para el usuario
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

		Map<String, Object> response = new HashMap<>();

		//Se obtiene el id del cliente
		Cliente cliente = clienteService.findById(id);

		//En caso de que se quiere reemplazar la foto
		if (!archivo.isEmpty()) {
			
			
			String nombreArchivo = null;
			
			try {
				//Copia el nombre del archivo
				nombreArchivo = uploadFileService.copiar(archivo);
			} catch (IOException e) {
				
				//En caso de que exista un error
				response.put("mensaje", "Error al subir la imagen");
				response.put("error", e.getMessage() + " : " + e.getCause().getMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}

			//Se obtiene el nombre de la foto anterior
			String nombreFotoAnterior = cliente.getFoto();

			//Se elimina el nombre de la foto anterior
			uploadFileService.eliminar(nombreFotoAnterior);
			
			//Se asigna la foto al usuario en la BBDD
			cliente.setFoto(nombreArchivo);

			//salva los cambios
			clienteService.save(cliente);

			//Cliente y mensaje
			response.put("cliente", cliente);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}

		//Se retorna el status
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Método que permite descargar la foto
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		
		Resource recurso = null;
		
		try {
			//Carga la imagen ya sea que tenga una propia o la default (no-image.png)
			recurso = uploadFileService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Para obligar a descargar la imagen
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	// Obtiene la lista de clientes
	@Secured("ROLE_ADMIN")
		@GetMapping("/clientes/regiones")
		public List<Region> listarRegiones() {

			return clienteService.findAllRegiones();

		}

}
