package mx.com.mundox.maximo.web.services;

import java.util.Map;

import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.util.exception.ConexionException;

public interface PeliculaActualizarBaseDatosService {

	public RequestJsonDto actualizaDatosPelicula(Map <String, Object> datos) throws ConexionException ;

}
