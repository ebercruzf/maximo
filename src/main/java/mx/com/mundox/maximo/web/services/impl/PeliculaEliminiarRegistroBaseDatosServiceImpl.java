package mx.com.mundox.maximo.web.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dao.PeliculaEliminiarRegistroBaseDatosDAOService;
import mx.com.mundox.maximo.web.dto.PeliculaRegistrarEnBaseDTO;
import mx.com.mundox.maximo.web.services.PeliculaEliminiarRegistroBaseDatosService;

@Service
public class PeliculaEliminiarRegistroBaseDatosServiceImpl implements PeliculaEliminiarRegistroBaseDatosService{

	@Autowired
	PeliculaEliminiarRegistroBaseDatosDAOService peliculaEliminiarRegistroBaseDatosDAOService;
	
	@Override
	public RequestJsonDto eliminarNombrePelicula(Map<String, Object> datos) throws ConexionException {
		RequestJsonDto respuesta = new RequestJsonDto();
		PeliculaRegistrarEnBaseDTO datosRegistra = new PeliculaRegistrarEnBaseDTO();
		int registroPelicula = 0;
		
		String usuario = (datos.get("usuario").toString()); 
		String imdbid = (datos.get("imdbid").toString()); 
		

		
		datosRegistra.setImdbID(imdbid);
		datosRegistra.setUsuario(usuario);
		
		registroPelicula = peliculaEliminiarRegistroBaseDatosDAOService.eliminarPeliculaenBase(datosRegistra);
		if(registroPelicula>=1) {
			respuesta.setCodE(0);
			respuesta.setMsgE("Se eliminaron correctamente los datos");
			respuesta.setJsonResultado(null);
		}else {
			respuesta.setCodE(1);
			respuesta.setMsgE("No se pudo eliminar la información");
			respuesta.setJsonResultado(null);
		}
			
		
		
		
		
		return respuesta;
	}

}
