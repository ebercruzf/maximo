package mx.com.mundox.maximo.web.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dao.impl.ActualizarAltaPeliculaDAOServiceImpl;
import mx.com.mundox.maximo.web.dto.PeliculaRegistrarEnBaseDTO;
import mx.com.mundox.maximo.web.services.PeliculaActualizarBaseDatosService;

@Service
public class PeliculaActualizarBaseDatosServiceImpl implements PeliculaActualizarBaseDatosService{

	@Autowired
	ActualizarAltaPeliculaDAOServiceImpl actualizarAltaPeliculaDAOServiceImpl;
	
	@Override
	public RequestJsonDto actualizaDatosPelicula(Map<String, Object> datos) throws ConexionException {
		RequestJsonDto respuesta = new RequestJsonDto();
		PeliculaRegistrarEnBaseDTO datosRegistra = new PeliculaRegistrarEnBaseDTO();
		int registroPelicula = 0;
		
		String imdbID = (datos.get("imdbID").toString()); 
		String nombreBusqueda = (datos.get("tituloPelicula").toString()); 
		String comentarios = (datos.get("comentarios").toString()); 
		String puntuacionRating = (datos.get("puntuacionRating").toString()); 
		
		
		String fechaCuandoLaVi = (datos.get("fechaCuandoLaVi").toString()); 
		
		datosRegistra.setFechaCuandoLaVi(fechaCuandoLaVi);
		
		
//		String poster = (datos.get("poster").toString());
		String usuario = (datos.get("usuario").toString());
		

		
		datosRegistra.setImdbID(imdbID);
		datosRegistra.setNombreBusqueda(nombreBusqueda);
		datosRegistra.setComentarios(comentarios);
		datosRegistra.setPuntuacionRating(puntuacionRating);
//		datosRegistra.setPoster(poster);
		datosRegistra.setUsuario(usuario);
		
		registroPelicula = actualizarAltaPeliculaDAOServiceImpl.actualizarPeliculaenBase(datosRegistra);
		if(registroPelicula>=1) {
			respuesta.setCodE(0);
			respuesta.setMsgE("Se actualizaro correctamente los datos");
			respuesta.setJsonResultado(null);
		}else {
			respuesta.setCodE(1);
			respuesta.setMsgE("No se pudo registrar la información");
			respuesta.setJsonResultado(null);
		}
			
		
		
		
		
		return respuesta;
	}

}
