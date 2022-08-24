package mx.com.mundox.maximo.auth;

import static mx.com.mundox.maximo.util.Constantes.ESTATUS_INICIO_SESION_USUARIO;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dao.UsuarioDAO;
import mx.com.mundox.maximo.web.dto.CredencialUsuarioDTO;
import mx.com.mundox.maximo.web.dto.LoginDTO;
import mx.com.mundox.maximo.web.dto.RespuestaPrecargaUsuarioLoginDTO;
import mx.com.mundox.maximo.web.dto.RolMenusDTO;
import mx.com.mundox.maximo.web.dto.UsuarioDTO;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	private static final Logger LOG = LoggerFactory.getLogger(InfoAdicionalToken.class);

	@Autowired
    private HttpServletRequest request;
	
	@Resource
	UsuarioDAO usuarioDao;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		LoginDTO usuario = findByUsername(authentication.getName());
		List<RolMenusDTO> rolMenus; 
		UsuarioDTO cargaInfoUsuario = null;
		RequestJsonDto respuestaServicio = new RequestJsonDto();
		RespuestaPrecargaUsuarioLoginDTO respuestaPrecarga = null;
		
		List<CredencialUsuarioDTO> credencialUsuarioDTO; 
	
		
		try {

			cargaInfoUsuario = new UsuarioDTO();
			
			respuestaPrecarga = new RespuestaPrecargaUsuarioLoginDTO();
			
			usuario = usuarioDao.getUsuarioLoginInfo(authentication.getName());
//			*********************************************************************
			cargaInfoUsuario = usuarioDao.getCargaDatosUsuario(authentication.getName());
			rolMenus = usuarioDao.getMenuPorClaveRole(cargaInfoUsuario.getIdRol());

//			*********************************************************************
			
			respuestaPrecarga.setUsuario(cargaInfoUsuario);
			

			if (usuario.getExiste()) {
							
							if (cargaInfoUsuario.getCveUsuario() != null) {
								
									respuestaServicio.setCodE(0);
									respuestaServicio.setMsgE("Exito");
									respuestaServicio.setJsonResultado(respuestaPrecarga);
				
									request.getSession().setAttribute("banderaLogin",  "adelante");

									if (!rolMenus.isEmpty()) {
										respuestaPrecarga.setListaRolesMenus(rolMenus);
									} else {
										respuestaServicio.setCodE(1);
										respuestaServicio.setMsgE("No se encontraron roles para este usuario.");
										respuestaServicio.setJsonResultado(null);
									}							
								
							} else {
								respuestaServicio.setCodE(1);
								respuestaServicio.setMsgE("Error usuario/contraseña7.");
								respuestaServicio.setJsonResultado(null);
							}
				
			}
			else {
				respuestaServicio.setCodE(1);
				respuestaServicio.setMsgE("Error de usuario o contraseña.");
				respuestaServicio.setJsonResultado(null);

			}
			Map<String, Object> info = new HashMap<>();
			info.put("respuesta", respuestaServicio);
			
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
			LOG.info("Generando toquen_InfoAdicionalToken.java: ", accessToken);
			return accessToken;
		} catch (ConexionException e) {
			LOG.info("**********************Excepcion en InfoAdicionalToken:*********************** ", e);
		}
		
		return accessToken;
	}
	
	@Transactional(readOnly=true)
	public LoginDTO findByUsername(String username) {
		try {
			usuarioDao.getUsuarioLoginInfo(username);
		} catch (ConexionException e) {
			LOG.info("**********************Excepcion en InfoAdicionalToken.findByUsername:*********************** ", e);
		}
		return null;
	}


}
