package mx.com.mundox.maximo.web.services.impl;

import static mx.com.mundox.maximo.util.Constantes.NO_NULO;
import static mx.com.mundox.maximo.util.Constantes.NO_VACIO;
import static mx.com.mundox.maximo.util.Constantes.REQUERIDO;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.mundox.maximo.util.Constantes;
import mx.com.mundox.maximo.util.Parametro;
import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dao.RegistrarAltaPeliculaDAOService;
//import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.web.dto.PeliculaRegistrarEnBaseDTO;
import mx.com.mundox.maximo.web.services.PeliculaAgregaBaseDatosService;

@Service
public class PeliculaAgregaBaseDatosServiceImpl implements PeliculaAgregaBaseDatosService{

	private static final Logger LOG = LoggerFactory.getLogger(PeliculaAgregaBaseDatosServiceImpl.class);
	
	@Autowired
	RegistrarAltaPeliculaDAOService registrarAltaPeliculaDAOService;
	
	@Override
	public RequestJsonDto agregaNombrePelicula(Map<String, Object> datos) throws ConexionException {
		
		RequestJsonDto respuesta = new RequestJsonDto();
		PeliculaRegistrarEnBaseDTO datosRegistra = new PeliculaRegistrarEnBaseDTO();
		int registroPelicula = 0;
		
		String imdbID = (datos.get("imdbID").toString()); 
		String nombreBusqueda = (datos.get("tituloPelicula").toString()); 
		String comentarios = (datos.get("comentarios").toString()); 
		String puntuacionRating = (datos.get("puntuacionRating").toString()); 
		
		
		String fechaCuandoLaVi = (datos.get("fechaCuandoLaVi").toString()); 
		
		datosRegistra.setFechaCuandoLaVi(fechaCuandoLaVi);
		
		
		String poster = (datos.get("poster").toString());
		String usuario = (datos.get("usuario").toString());
		

		
		datosRegistra.setImdbID(imdbID);
		datosRegistra.setNombreBusqueda(nombreBusqueda);
		datosRegistra.setComentarios(comentarios);
		datosRegistra.setPuntuacionRating(puntuacionRating);
		datosRegistra.setPoster(poster);
		datosRegistra.setUsuario(usuario);
		
		registroPelicula = registrarAltaPeliculaDAOService.registrarPeliculaenBase(datosRegistra);
		if(registroPelicula==1) {
			respuesta.setCodE(0);
			respuesta.setMsgE("Se registraron correctamente los datos");
			respuesta.setJsonResultado(null);
		}else {
			respuesta.setCodE(1);
			respuesta.setMsgE("No se pudo registrar la información");
			respuesta.setJsonResultado(null);
		}
			
		
		
		
		
		return respuesta;
	}

}
