package mx.com.mundox.maximo.web.dao;

import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dto.PeliculaRegistrarEnBaseDTO;

public interface ConsultaAltaPeliculaBaseLocalDAOService {
	
	PeliculaRegistrarEnBaseDTO consultaPeliculaenBaseLocal (PeliculaRegistrarEnBaseDTO registro) throws ConexionException;

}

