package mx.com.mundox.maximo.web.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dao.ConsultaAltaPeliculaBaseLocalDAOService;
import mx.com.mundox.maximo.web.dao.RegistrarAltaPeliculaDAOService;
import mx.com.mundox.maximo.web.dto.PeliculaRegistrarEnBaseDTO;
import mx.com.mundox.maximo.web.services.PeliculaConsultarBaseDatosLocalService;

@Service
public class PeliculaConsultarBaseDatosLocalServiceImpl implements PeliculaConsultarBaseDatosLocalService{

	@Autowired
	RegistrarAltaPeliculaDAOService registrarAltaPeliculaDAOService;
	
	@Autowired
	ConsultaAltaPeliculaBaseLocalDAOService consultaAltaPeliculaBaseLocalDAOService;
	
	@Override
	public RequestJsonDto consultarPeliculaBaseLocal(Map<String, Object> datos) throws ConexionException {
		RequestJsonDto respuesta = new RequestJsonDto();
		PeliculaRegistrarEnBaseDTO datosbuscar = new PeliculaRegistrarEnBaseDTO();
		PeliculaRegistrarEnBaseDTO respuesaConsultaPelicula = new PeliculaRegistrarEnBaseDTO();
		
		String tutuloPelicula = (datos.get("tutuloPelicula").toString()); 
		String usuario = (datos.get("usuario").toString()); 
		
		datosbuscar.setNombreBusqueda(tutuloPelicula);
		datosbuscar.setUsuario(usuario);
		
		respuesaConsultaPelicula = consultaAltaPeliculaBaseLocalDAOService.consultaPeliculaenBaseLocal(datosbuscar);
		if(respuesaConsultaPelicula!=null) {
			respuesta.setCodE(0);
			respuesta.setMsgE("Consulta exitosas");
			respuesta.setJsonResultado(respuesaConsultaPelicula);
		}else {
			respuesta.setCodE(1);
			respuesta.setMsgE("No se encontraron datos");
			respuesta.setJsonResultado(null);
		}
			
		
		
		
		
		return respuesta;
	}
	
	

}
