package mx.com.mundox.maximo.web.services;

import java.util.Map;

import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.util.exception.ConexionException;

public interface PeliculaConsultarBaseDatosLocalService {

	public RequestJsonDto consultarPeliculaBaseLocal(Map <String, Object> datos) throws ConexionException ;
}



