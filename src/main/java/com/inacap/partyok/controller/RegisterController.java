package com.inacap.partyok.controller;

import com.inacap.partyok.exception.LoginException;
import com.inacap.partyok.model.Categoria;
import com.inacap.partyok.model.Comuna;
import com.inacap.partyok.model.Proveedor;
import com.inacap.partyok.model.RegisterDto;
import com.inacap.partyok.security.entity.Rol;
import com.inacap.partyok.security.entity.Usuario;
import com.inacap.partyok.security.repository.UsuarioRepository;
import com.inacap.partyok.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     *
     * Obtenemos los datos del front y registramos a nuestro usuario
     *
     * */
    @PostMapping(value = "/")
    public ResponseEntity<Usuario> registrar(@RequestBody RegisterDto registerDto) {

        Usuario usuario = new Usuario();
        usuario.setCorreo(registerDto.getCorreo());
        usuario.setContrasena(passwordEncoder.encode(registerDto.getContrasena()));
        usuario.setEliminado(false);
        List<Rol> rol = new ArrayList<Rol>();
        rol.add(new Rol(1L, "PROVEEDOR"));
        usuario.setRols(rol);

        if (!ObjectUtils.isEmpty(this.usuarioRepository.findByCorreo(usuario.getCorreo()))) {
            throw new LoginException("Ya existe un usuario registrado con este correo.");
        }

        Usuario userGuardado = this.usuarioRepository.save(usuario);

        Proveedor proveedor = new Proveedor();
        proveedor.setRut(registerDto.getRut());
        proveedor.setNombre(registerDto.getNombre());
        proveedor.setPrimerApellido(registerDto.getPrimerApellido());
        proveedor.setSegundoApellido((registerDto.getSegundoApellido()));
        proveedor.setDescripcion(registerDto.getDescripcion());
        proveedor.setUsuario(userGuardado);

        Categoria categoria = new Categoria();
        categoria.setId(registerDto.getCategoria().getId());

        proveedor.setCategoria(categoria);

        Comuna comuna = new Comuna();
        comuna.setId(registerDto.getComuna().getId());
        proveedor.setComuna(comuna);



        return new ResponseEntity<>(this.proveedorService.crearProveedor(proveedor).getUsuario(), HttpStatus.OK);
    }
}
