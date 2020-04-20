package com.jamc.springboot.backend.apirest.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//IMPLEMENTACIÓN DE LA LÓGICA DE NEGOCIO PARA MANEJO DE ARCHIVOS

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	// CONSTANTE DEL DIRECTORIO
	private final static String DIRECTORIO_UPLOAD = "uploads";

	// Método para cargar la imagen
	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {

		// Obtiene la ruta del archivo
		Path rutaArchivo = getPath(nombreFoto);

		// Log
		log.info(rutaArchivo.toString());

		// Obtiene el recurso como URL y si hay error lo muestra (El método implementa
		// el error por eso no el try-catch)
		Resource recurso = new UrlResource(rutaArchivo.toUri());

		// Si no se pudo cargar la URL manda error
		if (!recurso.exists() && !recurso.isReadable()) {

			//La ruta del archivo se asigna a la imagen por default que se muestra cuando el usuario no tiene foto
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-image.png").toAbsolutePath();

			// Obtiene el recurso como URL y si hay error lo muestra
			recurso = new UrlResource(rutaArchivo.toUri());

			log.error("Error no se pudo cargar la imagen: " + nombreFoto);
		};

		// Retorna el recurso
		return recurso;
	}

	// Método para copiar la imagen y asignarla al usuario
	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		// Se activa una nombre único para cada foto
		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");

		// Se obtiene la ruta absoluta de la carpeta donde se guardará
		Path rutaArchivo = getPath(nombreArchivo);

		log.info(rutaArchivo.toString());

		// Copia el archivo en la ruta
		Files.copy(archivo.getInputStream(), rutaArchivo);

		return nombreArchivo;
	}

	// Método que maneja la eliminación
	@Override
	public boolean eliminar(String nombreFoto) {
		if (nombreFoto != null && nombreFoto.length() > 0) {
			Path rutaFotoAnterior = getPath(nombreFoto);
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
		}
		return false;
	}

	// Obtiene la ruta absoluta de la foto
	@Override
	public Path getPath(String nombreFoto) {
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
