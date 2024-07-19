package com.inacap.partyok.service;

import com.inacap.partyok.exception.ProveedorException;
import com.inacap.partyok.model.Categoria;
import com.inacap.partyok.model.Cotizacion;
import com.inacap.partyok.model.Proveedor;
import com.inacap.partyok.model.ProveedorDto;
import com.inacap.partyok.repository.ICategoriaRepository;
import com.inacap.partyok.repository.ICotizacionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.inacap.partyok.repository.IProveedorRepository;
import org.springframework.util.ObjectUtils;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service	
public class ProveedorService {
	
	@Autowired
	private IProveedorRepository proveedorRepository;
	@Autowired
	private ICategoriaRepository categoriaRepository;

	@Autowired
	private ICotizacionRepository cotizacionRepository;


	private final static String DIRECTORIO_UPLOAD = "uploads";

	public Proveedor crearProveedor(Proveedor proveedor){
		if(this.proveedorRepository.existsByRut(proveedor.getRut())){
			throw new ProveedorException("Ya existe un registro para este proveedor.");
		}
		this.proveedorRepository.save(proveedor);
		return proveedor;
	}

	public Proveedor actualizarProveedor(Proveedor proveedor){
		Proveedor proveedorEncontrado = this.proveedorRepository.findById(proveedor.getId()).get();
		proveedor.setComuna(proveedorEncontrado.getComuna());
		proveedor.setUsuario(proveedorEncontrado.getUsuario());
		this.proveedorRepository.save(proveedor);
		return  proveedor;
	}

	public List<Proveedor> obtenerProveedores(){

		List<Proveedor> proveedores = this.proveedorRepository.findAll();

		//En caso de no exitir en base de datos los proveedores, el sistema alerta con una excepcion
		if(ObjectUtils.isEmpty(proveedores)){
			throw new ProveedorException("No existen proveedores en la base de datos.");
		}
		return proveedores;
	}

 	public void eliminarProveedor(Long id){
		try{
			this.proveedorRepository.deleteById(id);
		}catch (IllegalArgumentException e){
			throw new ProveedorException(e.getMessage());
		}
	}

	public List<ProveedorDto> obtenerMasSolicitados(){

		//Lista de todos los proveedores existentes
		List<Proveedor> proveedores = this.obtenerProveedores();
		//Lista para almacenar el top 4
		List<ProveedorDto> proveedorDtos = new ArrayList<>();

		//Foreach para buscar las cotizazcion de cada proveedor
		proveedores.forEach(proveedor -> {
			ProveedorDto proveedorDto = new ProveedorDto();
			BeanUtils.copyProperties(proveedor, proveedorDto);
			List<Cotizacion> cotizacions = this.cotizacionRepository.findAllByProveedorId(proveedorDto.getId(), false);

			proveedorDto.setCotizacions(cotizacions);
			proveedorDtos.add(proveedorDto);
		});

		//ordenamos la lista por la cantidad de cotizaciones que tiene cada proveedor
		proveedorDtos.sort((a,b) -> b.getTotalCotizaciones().compareTo(a.getTotalCotizaciones()));

		//obtenemos el lugar en el top y la cantidad de cotizacion de cada proveedor
		for (int i = 0; i < proveedorDtos.size(); i++) {
			proveedorDtos.get(i).setTop(i); //top
			proveedorDtos.get(i).setCantidadCotizaciones(proveedorDtos.get(i).getTotalCotizaciones()); //cotizaciones

		}

		return proveedorDtos;
	}
	public Proveedor obtenerProveedor(Long id){

		Optional<Proveedor> proveedor = this.proveedorRepository.findById(id);

		//En caso de no exitir en base de datos el proveedor, el sistema alerta con una excepcion
		if(!proveedor.isPresent()){
			throw new ProveedorException("No existen proveedor.");
		}
		return proveedor.get();
	}

	public Resource cargar(String nombreFoto) throws MalformedURLException {

		//obtener la ruta del archivo
		Path rutaArchivo = getPath(nombreFoto);

		//creamos un nuevo objeto Resource para almacenmar la imagen
		Resource recurso = new UrlResource(rutaArchivo.toUri());
		if(!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
			recurso = new UrlResource(rutaArchivo.toUri());
		}
		return recurso;
	}
	public Path getPath(String nombreFoto) {
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}
	public List<Categoria>  buscarCategorias(){
		return this.categoriaRepository.findAll();
	}
	public List<Proveedor> obtenerPorCategoria(Long id) {
		return this.proveedorRepository.findAllByCategoria_Id(id);
	}
}
