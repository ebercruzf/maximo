package mx.com.mundox.maximo.web.services;

import java.util.Map;

import mx.com.mundox.maximo.util.RequestJsonDto;

public interface PeliculaBusquedaPorTituloService {
	
	public RequestJsonDto consultaNombrePelicula(Map <String, Object> datos) ;
	public RequestJsonDto getCadenaBuscar(String nombredeBusqueda);

}
