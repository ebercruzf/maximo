package mx.com.mundox.maximo.junit;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dao.RegistrarAltaPeliculaDAOService;
import mx.com.mundox.maximo.web.dto.PeliculaRegistrarEnBaseDTO;

public class PeliculaAgregarServiceImpl {
	@Autowired
	RegistrarAltaPeliculaDAOService registrarAltaPeliculaDAOService;
	
public RequestJsonDto agregaNombrePelicula(Map<String, String> datos) throws ConexionException {
		
	RequestJsonDto respuesta = new RequestJsonDto();
	PeliculaRegistrarEnBaseDTO datosRegistra = new PeliculaRegistrarEnBaseDTO();
	int registroPelicula = 0;
	
	datosRegistra.setImdbID(datos.get("imdbID").toString());
	datosRegistra.setNombreBusqueda(datos.get("tituloPelicula").toString());
	datosRegistra.setComentarios(datos.get("comentarios").toString());
	datosRegistra.setPuntuacionRating(datos.get("puntuacionRating").toString());
	datosRegistra.setFechaCuandoLaVi(datos.get("fechaCuandoLaVi").toString());
	datosRegistra.setPoster(datos.get("poster").toString());
	datosRegistra.setUsuario(datos.get("usuario").toString());
	
	registroPelicula = 1; //registrarAltaPeliculaDAOService.registrarPeliculaenBase(datosRegistra);
	if(registroPelicula==1) {
		respuesta.setCodE(0);
		respuesta.setMsgE("ok");
		respuesta.setJsonResultado(null);
	}else {
		respuesta.setCodE(1);
		respuesta.setMsgE("Nook");
		respuesta.setJsonResultado(null);
	}
		
	
	
	
	
	return respuesta;
	}

}
