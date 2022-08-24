package mx.com.mundox.maximo.auth;

import static mx.com.mundox.maximo.util.Constantes.ESTATUS_INTENTO_FALLIDO_SESION_USUARIO;
import static mx.com.mundox.maximo.util.Constantes.NUMERO_INTENTOS_PERMITIDOS_LOGIN;
import static mx.com.mundox.maximo.util.Constantes.RANGO_MINUTOS_CONTAR_INTENTOS_LOGIN_FALLIDOS;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dao.UsuarioDAO;
import mx.com.mundox.maximo.web.dto.LoginDTO;

/**
 * Componente que implementa la interfaz AuthenticationEventPublisher
 * para tratar los logeos por password incorrectos.
 * @author eber.cruz
 */

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher{
	
	private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Resource
	UsuarioDAO usuarioDao;
	

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		

		
	}

}
