package mx.com.mundox.maximo.web.dao;

import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dto.PeliculaRegistrarEnBaseDTO;

public interface ActualizarAltaPeliculaDAOService {

	int actualizarPeliculaenBase (PeliculaRegistrarEnBaseDTO registro) throws ConexionException;
	
}
