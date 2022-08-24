package mx.com.mundox.maximo.auth.service;

import java.util.Map;

import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.web.dto.LoginDTO;

public interface LoginService {
	
	public RequestJsonDto validarUsuario(Map <String, Object> datos);
	public LoginDTO findByUsername(String username);
	

}

