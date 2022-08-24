package mx.com.mundox.maximo.web.services;

import java.util.Map;

import mx.com.mundox.maximo.util.RequestJsonDto;

public interface AutentificarService {
	
	 public RequestJsonDto loginUsuario(Map<String, Object> datos);

}
