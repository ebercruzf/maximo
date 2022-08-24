package mx.com.mundox.maximo.web.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.web.dto.PeliculaResultadoConsultaGetDTO;
import mx.com.mundox.maximo.web.services.PeliculaBusquedaPorTituloService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class PeliculaBusquedaPorTituloServiceImpl
implements PeliculaBusquedaPorTituloService {
    private static final Logger LOG = LoggerFactory.getLogger((Class)PeliculaBusquedaPorTituloServiceImpl.class);
    private String BASE_URL = "http://www.omdbapi.com/?t=";

    public RequestJsonDto consultaNombrePelicula(Map<String, Object> datos) {
        String nombreBusqueda = datos.get("nombreBusqueda").toString();
        RequestJsonDto respuestaServicio = new RequestJsonDto();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity((Object)null, (MultiValueMap)headers);
        ResponseEntity res = restTemplate.exchange(String.valueOf(this.BASE_URL) + nombreBusqueda + "&apikey=e4fe3d8f", HttpMethod.GET, entity, (Class)String.class, new Object[0]);
        JSONObject jsonObj = new JSONObject((String)res.getBody());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = null;
        PeliculaResultadoConsultaGetDTO resultadoBusqueda = null;
        try {
            rootNode = mapper.readTree(jsonObj.toString());
            resultadoBusqueda = new PeliculaResultadoConsultaGetDTO();
            String tituloPeliculaBuscarWS = "";
            String imdbID = "";
            String poster = "";
            if (!rootNode.isNull()) {
                String respuesta = "";
                respuesta = new String(rootNode.get("Response").toString().toLowerCase());
                boolean respuestaFalseBool = Boolean.valueOf(respuesta = respuesta.replaceAll("\"", ""));
                if (respuestaFalseBool) {
                    tituloPeliculaBuscarWS = rootNode.get("Title").toString();
                    tituloPeliculaBuscarWS = tituloPeliculaBuscarWS.replaceAll("\"", "");
                    imdbID = rootNode.get("imdbID").toString();
                    imdbID = imdbID.replaceAll("\"", "");
                    poster = rootNode.get("Poster").toString();
                    poster = poster.replaceAll("\"", "");
                    resultadoBusqueda.setTituloPeliculaBuscarWS(tituloPeliculaBuscarWS);
                    resultadoBusqueda.setPoster(poster);
                    resultadoBusqueda.setImdbID(imdbID);
                    
                    respuestaServicio.setCodE(0);
                    respuestaServicio.setMsgE("ok");
                    respuestaServicio.setJsonResultado((Object)resultadoBusqueda);
                }
                if (!respuestaFalseBool) {
                    respuestaServicio.setCodE(1);
                    respuestaServicio.setMsgE("Pelicula no encontrada");
                    respuestaServicio.setJsonResultado((Object)null);
                }
            } else {
                respuestaServicio.setCodE(1);
                respuestaServicio.setMsgE("Ha ocurrido un detalle, no se muestra informaci\u00f3n");
                respuestaServicio.setJsonResultado((Object)null);
            }
        }
        catch (IOException e) {
            LOG.info("Ha ocurrido un detalle al parsear json de movimientos:  " + e);
            e.printStackTrace();
        }
        return respuestaServicio;
    }

//    Implementacion para la busqueda por el metodo get, para este caso no se paso un mapa si no un solo valor de tipo string
//    Para la prueba en el postman es la siguiente url cambiando su respectiva ip y puerto
//    http://localhost:7005/maximo/api/cliente/consulta/pelicula/get/{avatar}
	@Override
	public RequestJsonDto getCadenaBuscar(String nombredeBusqueda) {
		 String nombreBusqueda = nombredeBusqueda;
		 String nombreBusquedaIni = "";
		 String nombreBusquedaFin = "";
		 System.out.println("************************************************************************STRING1********************: " + nombreBusqueda);
		 
			int inicioDescripcionLimpia = nombreBusqueda.indexOf("{");
			nombreBusquedaIni= nombreBusqueda.substring(inicioDescripcionLimpia + 1);
			nombreBusquedaFin = nombreBusquedaIni.replace("}", "").trim();
			
			
			nombreBusqueda = nombreBusquedaFin;
		    RequestJsonDto respuestaServicio = new RequestJsonDto();
	        RestTemplate restTemplate = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Accept", "application/json");
	        HttpEntity entity = new HttpEntity((Object)null, (MultiValueMap)headers);
	        ResponseEntity res = restTemplate.exchange(String.valueOf(this.BASE_URL) + nombreBusqueda + "&apikey=e4fe3d8f", HttpMethod.GET, entity, (Class)String.class, new Object[0]);
	        JSONObject jsonObj = new JSONObject((String)res.getBody());
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode rootNode = null;
	        PeliculaResultadoConsultaGetDTO resultadoBusqueda = null;
	        try {
	            rootNode = mapper.readTree(jsonObj.toString());
	            resultadoBusqueda = new PeliculaResultadoConsultaGetDTO();
	            String tituloPeliculaBuscarWS = "";
	            String imdbID = "";
	            String poster = "";
	            if (!rootNode.isNull()) {
	                String respuesta = "";
	                respuesta = new String(rootNode.get("Response").toString().toLowerCase());
	                boolean respuestaFalseBool = Boolean.valueOf(respuesta = respuesta.replaceAll("\"", ""));
	                if (respuestaFalseBool) {
	                    tituloPeliculaBuscarWS = rootNode.get("Title").toString();
	                    tituloPeliculaBuscarWS = tituloPeliculaBuscarWS.replaceAll("\"", "");
	                    imdbID = rootNode.get("imdbID").toString();
	                    imdbID = imdbID.replaceAll("\"", "");
	                    poster = rootNode.get("Poster").toString();
	                    poster = poster.replaceAll("\"", "");
	                    resultadoBusqueda.setTituloPeliculaBuscarWS(tituloPeliculaBuscarWS);
	                    resultadoBusqueda.setPoster(poster);
	                    resultadoBusqueda.setImdbID(imdbID);
	                    respuestaServicio.setCodE(0);
	                    respuestaServicio.setMsgE("ok");
	                    respuestaServicio.setJsonResultado((Object)resultadoBusqueda);
	                }
	                if (!respuestaFalseBool) {
	                    respuestaServicio.setCodE(1);
	                    respuestaServicio.setMsgE("Pelicula no encontrada");
	                    respuestaServicio.setJsonResultado((Object)null);
	                }
	            } else {
	                respuestaServicio.setCodE(1);
	                respuestaServicio.setMsgE("Ha ocurrido un detalle, no se muestra informaci\u00f3n");
	                respuestaServicio.setJsonResultado((Object)null);
	            }
	        }
	        catch (IOException e) {
	            LOG.info("Ha ocurrido un detalle al parsear json de movimientos:  " + e);
	            e.printStackTrace();
	        }
	        return respuestaServicio;
		
	}
}
