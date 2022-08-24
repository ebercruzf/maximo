package mx.com.mundox.maximo.auth;

import static mx.com.mundox.maximo.util.Constantes.NO_NULO;
import static mx.com.mundox.maximo.util.Constantes.NO_VACIO;
import static mx.com.mundox.maximo.util.Constantes.REQUERIDO;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;

import mx.com.mundox.maximo.auth.service.CierraSessionService;
import mx.com.mundox.maximo.util.AsistenteController;
import mx.com.mundox.maximo.util.Constantes;
import mx.com.mundox.maximo.util.Parametro;
import mx.com.mundox.maximo.util.RequestJsonDto;

/**
 * Controlador para tratar la session de usuario
 * @author eber.cruz
 *
 */

@Controller
@RequestMapping("/public/api/cliente") 
public class AutentificarController {
	
private static final Logger LOG = LoggerFactory.getLogger(AutentificarController.class);

@Autowired
private HttpServletRequest requestSession;


@Autowired
private CierraSessionService cierraSessionService;
	

@Secured({"ROLE_Administrador", "Admin" , "Usuario"})
@RequestMapping(method = RequestMethod.POST,
        value = "/interno/cerrar/session/bd",
        consumes = "application/json;charset=UTF-8",
        produces = "application/json;charset=UTF-8")
public void cerrarSession(HttpServletResponse httpServletResponse, 
		RequestEntity<String> request, HttpServletRequest httpServletRequest) {
   
	String json = request.getBody();

	String usuarioBack = (String) requestSession.getSession().getAttribute("usuario")!= null ? (String) requestSession.getSession().getAttribute("usuario") : "";

	
    AsistenteController asistenteController = new AsistenteController(httpServletResponse);
    asistenteController.infoIn(request, httpServletRequest, " Cerrando sesion del cliente. ");
    asistenteController.iniciaTiempo();
    RequestJsonDto respuestaCierreSesionFallida = null;
    try {
    	respuestaCierreSesionFallida = new RequestJsonDto();
        asistenteController.mapeaON(json);
        asistenteController.recibeParam(new Parametro("usuario", REQUERIDO, Constantes.TIPO_CADENA, NO_NULO, NO_VACIO));
        
			String ipAddres = requestSession.getLocalAddr();

			if (asistenteController.validaParametrosDeJson()) {

				if (!usuarioBack.equals("")) {

					if (usuarioBack.equals(asistenteController.getJson().get("usuario").asText())) {
						
						asistenteController.devuelvePagina(HttpServletResponse.SC_OK,
								cierraSessionService.cierraSessionParametros(
										asistenteController.getJson().get("usuario").asText()));
						LOG.info("Consumo correcto del servicio AutentificarController/cerrarSession- Tiempo de Ejecucion: "+ asistenteController.finalizaTiempo() + " Ms");

					} else {
						respuestaCierreSesionFallida.setCodE(1);
						respuestaCierreSesionFallida.setMsgE("Se Infringió parametros de session.");
						respuestaCierreSesionFallida.setJsonResultado(null);
						asistenteController.devuelvePagina(HttpServletResponse.SC_EXPECTATION_FAILED);
						LOG.warn(
								"Inconsistencia en servicio AutentificarController/cerrarSession: Se Infringió parametros de session1 - Tiempo de Ejecucion: "
										+ asistenteController.finalizaTiempo() + " Ms");
					}

				} else {

					asistenteController.devuelvePagina(HttpServletResponse.SC_EXPECTATION_FAILED);
					LOG.info(
							"Inconsistencia en servicio AutentificarController/cerrarSession- cerrarSession de Ejecucion2: "
									+ asistenteController.finalizaTiempo() + " Ms");
				}
			} else {
				asistenteController.devuelvePagina(HttpServletResponse.SC_EXPECTATION_FAILED);
				LOG.info("Inconsistencia en servicio AutentificarController/cerrarSession- cerrarSession de Ejecucion: "
						+ asistenteController.finalizaTiempo() + " Ms");
			}
        	 
    } catch (JsonParseException e) {
        asistenteController.devuelveJsonException("JSON mal formado");
    } catch (IOException e) {
        asistenteController.devuelveJsonException("No fue posible realizar el mapeo");
    }
    catch (ParseException e) {
        asistenteController.devuelveJsonException("No fue posible realizar la validación");
    } 
    catch (GeneralSecurityException ex) {
        asistenteController.devuelveJsonException("No fue posible el mapeo por Seguridad");
    }
}



}
