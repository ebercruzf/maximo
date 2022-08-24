package mx.com.mundox.maximo.auth.service.impl;

import static mx.com.mundox.maximo.util.Constantes.ESTATUS_CIERRE_SESION_USUARIO;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.com.mundox.maximo.auth.service.CierraSessionService;
import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dao.CierraSesionDAO;
import mx.com.mundox.maximo.web.dto.UsuarioDTO;

@Service
public class CierraSessionServiceImpl implements CierraSessionService{
	
	private static final Logger log = LoggerFactory.getLogger(CierraSessionServiceImpl.class);

	@Resource
	CierraSesionDAO cierraSesionDAO;
	@Override
	public RequestJsonDto cierraSessionParametros(String usuario) {
		
		RequestJsonDto respuestaCierreSesion = null;
		respuestaCierreSesion = new RequestJsonDto();
		
		Boolean verficaExisteEnSessionActiva = false;
		Boolean respuestaBuena = false;
		try {
			
			
			verficaExisteEnSessionActiva = cierraSesionDAO.getUsuarioCerrarSessionActiva(usuario);
			

			if(verficaExisteEnSessionActiva==true) {	
					
					
					if(respuestaBuena==true) {
						respuestaCierreSesion.setCodE(0);
						respuestaCierreSesion.setMsgE("Sesión cerrada exitosamente..");
						respuestaCierreSesion.setJsonResultado(null);
					}else {
						respuestaCierreSesion.setCodE(1);
						respuestaCierreSesion.setMsgE("Excepción al cerrar la sesion en bd");
					}
					
				
				
			}else {
				respuestaCierreSesion.setCodE(1);
				respuestaCierreSesion.setMsgE("Excepción de usuario");
			}
		
		} catch (ConexionException e) {
			e.printStackTrace();
			log.warn("Excepción en la conexion", e);
		}
		
		
		return respuestaCierreSesion;
	}

	

}
