package mx.com.mundox.maximo;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import mx.com.mundox.maximo.junit.PeliculaAgregarServiceImpl;
import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dto.PeliculaRegistrarEnBaseDTO;

public class PeliculaAgregarServiceImplTest {
	/**
	 * Tes para probar la clse del servicio que si esta en uno correspondiendo a que los datos se fueron guardado
	 * responderia de forma correcta en el caso que fuera diferente de 1 mandaria la prueba fallida.
	 * @throws ConexionException
	 */
	
    @Test 
	public void pruebaAgregaBdPelicula() throws ConexionException {
    	PeliculaAgregarServiceImpl peliculaAgregarServiceImpl = new PeliculaAgregarServiceImpl();
    
    	RequestJsonDto respuesta = new RequestJsonDto();
    	PeliculaRegistrarEnBaseDTO datosRegistra = new PeliculaRegistrarEnBaseDTO();
    	
    	Map<String, String> datos = new HashMap<String, String>();
    	    	
    	datos.put("imdbID", "tt0120611");
    	datos.put("tituloPelicula", "Blade");
    	datos.put("comentarios", "comenta");
    	datos.put("puntuacionRating", "7");
    	datos.put("fechaCuandoLaVi", "23/11/1985");
    	datos.put("poster", "https://m.media-amazon.com/images/M/MV5BOTk2NDNjZWQtMGY0Mi00YTY2LWE5MzctMGRhZmNlYzljYTg5XkEyXkFqcGdeQXVyMTAyNjg4NjE0._V1_SX300.jpg");
    	datos.put("usuario", "eduardo.perez");
    	
    	respuesta = peliculaAgregarServiceImpl.agregaNombrePelicula(datos);
    	assertNotNull(respuesta.getCodE());
    	assertTrue(respuesta.getMsgE().equals("ok"));
    	
       
    	
		
		
    	
    	
    	
    }
	

	
}
