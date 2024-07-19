package com.inacap.partyok.controller;

import com.inacap.partyok.model.Categoria;
import com.inacap.partyok.model.Proveedor;
import com.inacap.partyok.model.ProveedorDto;
import com.inacap.partyok.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping(value = "/")
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor proveedor){
        return new ResponseEntity<>(this.proveedorService.crearProveedor(proveedor), HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Proveedor> actualizar(@RequestBody Proveedor proveedor){
        return new ResponseEntity<>(this.proveedorService.actualizarProveedor(proveedor), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Proveedor>> obtenerTodos(){
        return new ResponseEntity<>(this.proveedorService.obtenerProveedores(), HttpStatus.OK);
    }

    @GetMapping(value = "/obtener{id}")
    public ResponseEntity<Proveedor> obtener(@RequestParam Long id){
        return new ResponseEntity<>(this.proveedorService.obtenerProveedor(id), HttpStatus.OK);
    }
    @GetMapping(value = "/top")
    public ResponseEntity<List<ProveedorDto>> obtenerMasSolicitados(){
        return new ResponseEntity<>(this.proveedorService.obtenerMasSolicitados(), HttpStatus.OK);
    }
    @GetMapping(value = "/categorias")
    public ResponseEntity<List<Categoria>> obtenerCategorias(){
        return new ResponseEntity<>(this.proveedorService.buscarCategorias(), HttpStatus.OK);
    }

    @PostMapping( "/uploadImage")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
        Map<String, Object> response = new HashMap<>();
        Proveedor proveedor = this.proveedorService.obtenerProveedor(id);
        if(!archivo.isEmpty()){
            String nombreArchivo = UUID.randomUUID().toString() +"_"+ archivo.getOriginalFilename();
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
            try{
                Files.copy(archivo.getInputStream(), rutaArchivo);
            }catch (IOException e){
                e.printStackTrace();
            }
            proveedor.setImagen(nombreArchivo);
            this.proveedorService.actualizarProveedor(proveedor);

        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/uploadImage/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

        Resource recurso = null;

        try {
            recurso = proveedorService.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

    @GetMapping(value = "/buscar/categorias{id}")
    public ResponseEntity<List<Proveedor>> obtenerPorCategoria(@RequestParam Long id){
        return new ResponseEntity<>(this.proveedorService.obtenerPorCategoria(id), HttpStatus.OK);
    }
}
