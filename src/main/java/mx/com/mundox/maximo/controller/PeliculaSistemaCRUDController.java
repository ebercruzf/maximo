package mx.com.mundox.maximo.controller;

import static mx.com.mundox.maximo.util.Constantes.NO_NULO;
import static mx.com.mundox.maximo.util.Constantes.NO_VACIO;
import static mx.com.mundox.maximo.util.Constantes.REQUERIDO;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.encoder.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;

import mx.com.mundox.maximo.util.AsistenteController;
import mx.com.mundox.maximo.util.Constantes;
import mx.com.mundox.maximo.util.Parametro;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.services.PeliculaActualizarBaseDatosService;
import mx.com.mundox.maximo.web.services.PeliculaAgregaBaseDatosService;
import mx.com.mundox.maximo.web.services.PeliculaBusquedaPorTituloService;
import mx.com.mundox.maximo.web.services.PeliculaConsultarBaseDatosLocalService;
import mx.com.mundox.maximo.web.services.PeliculaEliminiarRegistroBaseDatosService;

@RestController
@RequestMapping("/api/cliente")
public class PeliculaSistemaCRUDController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PeliculaSistemaCRUDController.class);
	 
	@Autowired
	private PeliculaBusquedaPorTituloService peliculaBusquedaPorTituloService;
	
	@Autowired
	private PeliculaAgregaBaseDatosService peliculaAgregaBaseDatosService;
	
	@Autowired
	private PeliculaConsultarBaseDatosLocalService peliculaConsultarBaseDatosLocalService;
	
	@Autowired
	private PeliculaActualizarBaseDatosService peliculaActualizarBaseDatosService;
	
	@Autowired
	private PeliculaEliminiarRegistroBaseDatosService peliculaEliminiarRegistroBaseDatosService;
	
//	@Secured({"ROLE_Administrador", "Admin" , "Usuario"})
	@GetMapping(value = "/consulta/pelicula/get/{nombreBusqueda}" , 
			produces = "application/json;charset=UTF-8")
	public void getConsultaPeliculaWeb(@PathVariable(value = "nombreBusqueda") String nombreBusqueda, HttpServletResponse httpServletResponse, 
    		RequestEntity<String> request) {

			    
				LOG.info("*******Entrando a consultar pelicula en la web:****** ");
				String json = nombreBusqueda;
		        AsistenteController asistenteController = new AsistenteController(httpServletResponse);
		        asistenteController.iniciaTiempo();
		        
		        try {
		            asistenteController.recibeParametro(new Parametro(json, REQUERIDO, Constantes.TIPO_CADENA, NO_NULO, NO_VACIO));

		                if (asistenteController.validaParametros()) {
		                	asistenteController.devuelvePagina(HttpServletResponse.SC_OK, peliculaBusquedaPorTituloService.getCadenaBuscar(nombreBusqueda));
		                	LOG.info("Consumo correcto del servicio PeliculaSistemaCRUDController/consultaPeliculaWeb - Tiempo de Ejecucion: " + asistenteController.finalizaTiempo() + " Ms");
		                } else {
		                	asistenteController.devuelvePagina(HttpServletResponse.SC_EXPECTATION_FAILED);
		                	LOG.info("Inconsistencia en servicio OPeliculaBusquedaPorTituloController/consultaPeliculaWeb - Tiempo de Ejecucion: " + asistenteController.finalizaTiempo() + " Ms");
		                }
		            	 
		        } catch (JsonParseException e) {
		            asistenteController.devuelveJsonException("JSON mal formado");
		        } catch (IOException e) {
		            asistenteController.devuelveJsonException("No fue posible realizar el mapeo");
		        } catch (ParseException e) {
		            asistenteController.devuelveJsonException("No fue posible realizar la validación");
		        } 
		       
			}
	
//	@Secured({"ROLE_Administrador", "Admin" , "Usuario"})
	@RequestMapping(method = RequestMethod.POST,
            value = "/consulta/pelicula",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
	 public void consultaPeliculaWeb(HttpServletResponse httpServletResponse, 
	    		RequestEntity<String> request, HttpServletRequest httpServletRequest) {
			    
				LOG.info("*******Entrando a consultar pelicula en la web:****** ");
				String json = request.getBody();
		        AsistenteController asistenteController = new AsistenteController(httpServletResponse);
		        asistenteController.infoIn(request, httpServletRequest, " Consultar datos en la web. ");
		        asistenteController.iniciaTiempo();
		        
		        try {
		            asistenteController.mapea(json);
		            asistenteController.recibeParam(new Parametro("nombreBusqueda", REQUERIDO, Constantes.TIPO_CADENA, NO_NULO, NO_VACIO));

		            asistenteController.getMap().put("ipRemoteAdrres", Encode.forXmlContent(httpServletRequest.getRemoteAddr()));
		            asistenteController.getMap().put("ipLocalAddress", Encode.forXmlContent(httpServletRequest.getLocalAddr()));
		                if (asistenteController.validaParametros()) {
		                	asistenteController.devuelvePagina(HttpServletResponse.SC_OK, peliculaBusquedaPorTituloService.consultaNombrePelicula(asistenteController.getMap()));
		                	LOG.info("Consumo correcto del servicio PeliculaSistemaCRUDController/consultaPeliculaWeb - Tiempo de Ejecucion: " + asistenteController.finalizaTiempo() + " Ms");
		                } else {
		                	asistenteController.devuelvePagina(HttpServletResponse.SC_EXPECTATION_FAILED);
		                	LOG.info("Inconsistencia en servicio OPeliculaBusquedaPorTituloController/consultaPeliculaWeb - Tiempo de Ejecucion: " + asistenteController.finalizaTiempo() + " Ms");
		                }
		            	 
		        } catch (JsonParseException e) {
		            asistenteController.devuelveJsonException("JSON mal formado");
		        } catch (IOException e) {
		            asistenteController.devuelveJsonException("No fue posible realizar el mapeo");
		        } catch (ParseException e) {
		            asistenteController.devuelveJsonException("No fue posible realizar la validación");
		        } 
		        catch (GeneralSecurityException ex) {
		            asistenteController.devuelveJsonException("No fue posible el mapeo por Seguridad");
		        }
			}
	
	
	@RequestMapping(method = RequestMethod.POST,
            value = "/agrega/pelicula",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
	 public void agregarPeliculaenBaseDatosLocal(HttpServletResponse httpServletResponse, 
	    		RequestEntity<String> request, HttpServletRequest httpServletRequest) {
			    
				LOG.info("*******Entrando a consultar pelicula en la web:****** ");
				String json = request.getBody();
		        AsistenteController asistenteController = new AsistenteController(httpServletResponse);
		        asistenteController.infoIn(request, httpServletRequest, " Consultar datos en la web. ");
		        asistenteController.iniciaTiempo();
		        
		        try {
		            asistenteController.mapea(json);
		            asistenteController.recibeParam(new Parametro("imdbID", REQUERIDO, Constantes.TIPO_CADENA, NO_NULO, NO_VACIO));
		            asistenteController.recibeParam(new Parametro("tituloPelicula", REQUERIDO, Constantes.TIPO_CADENA, NO_NULO, NO_VACIO));
		            asistenteController.recibeParam(new Parametro("comentarios", REQUERIDO, Constantes.TIPO_CADENA, NO_NULO, NO_VACIO));
		            asistenteController.recibeParam(new Parametro("puntuacionRating", REQUERIDO, Constantes.TIPO_CADENA, NO_NULO, NO_VACIO));
		            asistenteController.recibeParam(new Parametro("fechaCuandoLaVi", REQUERIDO, Constantes.TIPO_CADENA, NO_NULO, NO_VACIO));
		            asistenteController.recibeParam(new Parametro("poster", REQUERIDO,  NO_NULO, NO_VACIO));
		            asistenteController.recibeParam(new Parametro("usuario", REQUERIDO, Constantes.TIPO_MENSAJE, NO_NULO, NO_VACIO));
		                                                        

		            asistenteController.getMap().put("ipRemoteAdrres", Encode.forXmlContent(httpServletRequest.getRemoteAddr()));
		            asistenteController.getMap().put("ipLocalAddress", Encode.forXmlContent(httpServletRequest.getLocalAddr()));
		                if (asistenteController.validaParametros()) {
		                	asistenteController.devuelvePagina(HttpServletResponse.SC_OK, peliculaAgregaBaseDatosService.agregaNombrePelicula(asistenteController.getMap()));
		                	LOG.info("Consumo correcto del servicio PeliculaSistemaCRUDController/agregarPeliculaenBaseDatosLocal - Tiempo de Ejecucion: " + asistenteController.finalizaTiempo() + " Ms");
		                } else {
		                	asistenteController.devuelvePagina(HttpServletResponse.SC_EXPECTATION_FAILED);
		                	LOG.info("Inconsistencia en servicio OPeliculaBusquedaPorTituloController/agregarPeliculaenBaseDatosLocal - Tiempo de Ejecucion: " + asistenteController.finalizaTiempo() + " Ms");
		                }
		            	 
		        } catch (JsonParseException e) {
		            asistenteController.devuelveJsonException("JSON mal formado");
		        } catch (IOException e) {
		            asistenteController.devuelveJsonException("No fue posible realizar el mapeo");
		        } catch (ParseException e) {
		            asistenteController.devuelveJsonException("No fue posible realizar la validación");
		        } catch (GeneralSecurityException ex) {
		            asistenteController.devuelveJsonException("No fue posible el mapeo por Seguridad");
		        } catch (ConexionException e) {
		        	asistenteController.devuelveJsonException("Ocurrio una excepcion al realizar el registro en la base de datos");
				}
			}
	

	@RequestMapping(method = RequestMethod.POST,
            value = "/consulta/pelicula/bdlocal",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
	 public void consultaPeliculaenBaseDatosLocal(HttpServletResponse httpServletResponse, 
	    		RequestEntity<String> request, HttpServletRequest httpServletRequest) {
			    
				LOG.info("*******Entrando a consultar pelicula en la web:****** ");
				String json = request.getBody();
		        AsistenteController asistenteController = new AsistenteController(httpServletResponse);
		        asistenteController.infoIn(request, httpServletRequest, " Consultar datos en base local. ");
		        asistenteController.iniciaTiempo();
		        
		        try {
		            asistenteController.mapea(json);
		            asistenteController.recibeParam(new Parametro("usuario", REQUERIDO, Constantes.TIPO_MENSAJE, NO_NULO, NO_VACIO));
		            asistenteController.recibeParam(new Parametro("tutuloPelicula", REQUERIDO, Constantes.TIPO_MENSAJE, NO_NULO, NO_VACIO));
                                         

		            asistenteController.getMap().put("ipRemoteAdrres", Encode.forXmlContent(httpServletRequest.getRemoteAddr()));
		            asistenteController.getMap().put("ipLocalAddress", Encode.forXmlContent(httpServletRequest.getLocalAddr()));
		                if (asistenteController.validaParametros()) {
		                	asistenteController.devuelvePagina(HttpServletResponse.SC_OK, peliculaConsultarBaseDatosLocalService.consultarPeliculaBaseLocal(asistenteController.getMap()));
		                	LOG.info("Consumo correcto del servicio PeliculaSistemaCRUDController/consultaPeliculaenBaseDatosLocal - Tiempo de Ejecucion: " + asistenteController.finalizaTiempo() + " Ms");
		                } else {
		                	asistenteController.devuelvePagina(HttpServletResponse.SC_EXPECTATION_FAILED);
		                	LOG.info("Inconsistencia en servicio OPeliculaBusquedaPorTituloController/consultaPeliculaenBaseDatosLocal - Tiempo de Ejecucion: " + asistenteController.finalizaTiempo() + " Ms");
		                }
		            	 
		        } catch (JsonParseException e) {
		            asistenteController.devuelveJsonException("JSON mal formado");
		        } catch (IOException e) {
		            asistenteController.devuelveJsonException("No fue posible realizar el mapeo");
		        } catch (ParseException e) {
		            asistenteController.devuelveJsonException("No fue posible realizar la validación");
		        } catch (GeneralSecurityException ex) {
		            asistenteController.devuelveJsonException("No fue posible el mapeo por Seguridad");
			    } catch (ConexionException e) {
			    	asistenteController.devuelveJsonException("Ocurrio algun detalle con la coneccion de la base de datos");
				}
	}


	
	@RequestMapping(method = RequestMethod.POST,
            value = "/editar/pelicula/bdlocal",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
	        public void editarPeliculaenBaseDatosLocal(HttpServletResponse httpServletResponse, 
	    		RequestEntity<String> request, HttpServletRequest httpServletRequest) {
			    
				LOG.info("*******Entrando a consultar pelicula en la web:****** ");
				String json = request.getBody();
		        AsistenteController asistenteController = new AsistenteController(httpServletResponse);
		        asistenteController.infoIn(request, httpServletRequest, " Consultar datos en base local. ");
		        asistenteController.iniciaTiempo();
		        
		        try {
		            asistenteController.mapea(json);
		            asistenteController.recibeParam(new Parametro("usuario", REQUERIDO, Constantes.TIPO_MENSAJE, NO_NULO, NO_VACIO));
		            asistenteController.recibeParam(new Parametro("tituloPelicula", REQUERIDO, Constantes.TIPO_MENSAJE, NO_NULO, NO_VACIO));
		            
		            asistenteController.recibeParam(new Parametro("imdbID", REQUERIDO, Constantes.TIPO_MENSAJE, NO_NULO, NO_VACIO));
		            asistenteController.recibeParam(new Parametro("puntuacionRating", REQUERIDO, Constantes.TIPO_MENSAJE, NO_NULO, NO_VACIO));
		            asistenteController.recibeParam(new Parametro("fechaCuandoLaVi", REQUERIDO, Constantes.TIPO_CADENA, NO_NULO, NO_VACIO));
		            asistenteController.recibeParam(new Parametro("comentarios", REQUERIDO, Constantes.TIPO_CADENA, NO_NULO, NO_VACIO));
		            
		            asistenteController.getMap().put("ipRemoteAdrres", Encode.forXmlContent(httpServletRequest.getRemoteAddr()));
		            asistenteController.getMap().put("ipLocalAddress", Encode.forXmlContent(httpServletRequest.getLocalAddr()));
		                if (asistenteController.validaParametros()) {
		                	asistenteController.devuelvePagina(HttpServletResponse.SC_OK, peliculaActualizarBaseDatosService.actualizaDatosPelicula(asistenteController.getMap()));
		                	LOG.info("Consumo correcto del servicio PeliculaSistemaCRUDController/editarPeliculaenBaseDatosLocal - Tiempo de Ejecucion: " + asistenteController.finalizaTiempo() + " Ms");
		                } else {
		                	asistenteController.devuelvePagina(HttpServletResponse.SC_EXPECTATION_FAILED);
		                	LOG.info("Inconsistencia en servicio OPeliculaBusquedaPorTituloController/editarPeliculaenBaseDatosLocal - Tiempo de Ejecucion: " + asistenteController.finalizaTiempo() + " Ms");
		                }
		            	 
		        } catch (JsonParseException e) {
		            asistenteController.devuelveJsonException("JSON mal formado");
		        } catch (IOException e) {
		            asistenteController.devuelveJsonException("No fue posible realizar el mapeo");
		        } catch (ParseException e) {
		            asistenteController.devuelveJsonException("No fue posible realizar la validación");
		        } catch (GeneralSecurityException ex) {
		            asistenteController.devuelveJsonException("No fue posible el mapeo por Seguridad");
			    } catch (ConexionException e) {
			    	asistenteController.devuelveJsonException("Ocurrio algun detalle con la coneccion de la base de datos");
				}
	}
	
	@RequestMapping(method = RequestMethod.POST,
            value = "/eliminar/pelicula/bdlocal",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
	        public void eliminarPeliculaenBaseDatosLocal(HttpServletResponse httpServletResponse, 
	    		RequestEntity<String> request, HttpServletRequest httpServletRequest) {
			    
				LOG.info("*******Entrando a consultar pelicula en la web:****** ");
				String json = request.getBody();
		        AsistenteController asistenteController = new AsistenteController(httpServletResponse);
		        asistenteController.infoIn(request, httpServletRequest, " Consultar datos en base local. ");
		        asistenteController.iniciaTiempo();
		        
		        try {
		            asistenteController.mapea(json);
		            asistenteController.recibeParam(new Parametro("usuario", REQUERIDO, Constantes.TIPO_MENSAJE, NO_NULO, NO_VACIO));
		            asistenteController.recibeParam(new Parametro("imdbid", REQUERIDO, Constantes.TIPO_MENSAJE, NO_NULO, NO_VACIO));
		            
		            asistenteController.getMap().put("ipRemoteAdrres", Encode.forXmlContent(httpServletRequest.getRemoteAddr()));
		            asistenteController.getMap().put("ipLocalAddress", Encode.forXmlContent(httpServletRequest.getLocalAddr()));
		                if (asistenteController.validaParametros()) {
		                	asistenteController.devuelvePagina(HttpServletResponse.SC_OK, peliculaEliminiarRegistroBaseDatosService.eliminarNombrePelicula(asistenteController.getMap()));
		                	LOG.info("Consumo correcto del servicio PeliculaSistemaCRUDController/OPeliculaBusquedaPorTituloController - Tiempo de Ejecucion: " + asistenteController.finalizaTiempo() + " Ms");
		                } else {
		                	asistenteController.devuelvePagina(HttpServletResponse.SC_EXPECTATION_FAILED);
		                	LOG.info("Inconsistencia en servicio OPeliculaBusquedaPorTituloController/OPeliculaBusquedaPorTituloController - Tiempo de Ejecucion: " + asistenteController.finalizaTiempo() + " Ms");
		                }
		            	 
		        } catch (JsonParseException e) {
		            asistenteController.devuelveJsonException("JSON mal formado");
		        } catch (IOException e) {
		            asistenteController.devuelveJsonException("No fue posible realizar el mapeo");
		        } catch (ParseException e) {
		            asistenteController.devuelveJsonException("No fue posible realizar la validación");
		        } catch (GeneralSecurityException ex) {
		            asistenteController.devuelveJsonException("No fue posible el mapeo por Seguridad");
			    } catch (ConexionException e) {
			    	asistenteController.devuelveJsonException("Ocurrio algun detalle con la coneccion de la base de datos");
				}
	}
}
