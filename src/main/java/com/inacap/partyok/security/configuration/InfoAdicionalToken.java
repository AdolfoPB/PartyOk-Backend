package com.inacap.partyok.security.configuration;

import com.inacap.partyok.model.Proveedor;
import com.inacap.partyok.repository.IProveedorRepository;
import com.inacap.partyok.security.entity.Usuario;
import com.inacap.partyok.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IProveedorRepository iProveedorRepository;

    /**
     * Agrega informacion adicional a nuestro token, como el correo, nombre,imagen, rut
     * */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Usuario usuario = usuarioRepository.findByCorreo(authentication.getName());
        Map<String, Object> info = new HashMap<>();
        info.put("info_adicional", "Hola que tal!: ".concat(authentication.getName()));
        info.put("Correo", usuario.getCorreo());
        info.put("idUsuario", usuario.getId());

        Proveedor proveedor = iProveedorRepository.findByUserId(usuario.getId());
        info.put("idProveedor", proveedor.getId());
        info.put("nombre", proveedor.getNombre());
        info.put("primerApellido", proveedor.getPrimerApellido());
        info.put("segundoApellido", proveedor.getSegundoApellido());
        info.put("image", proveedor.getImagen());
        info.put("rut", proveedor.getRut());
        info.put("email", usuario.getCorreo());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }

}