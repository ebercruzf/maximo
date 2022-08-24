package mx.com.mundox.maximo.auth.service;

import mx.com.mundox.maximo.util.RequestJsonDto;

public interface CierraSessionService {

	public RequestJsonDto cierraSessionParametros (String usuario);
	
}
