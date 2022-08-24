package mx.com.mundox.maximo.auth.service.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.mundox.maximo.auth.AutentificarController;
import mx.com.mundox.maximo.auth.service.LoginService;
import mx.com.mundox.maximo.util.RequestJsonDto;
import mx.com.mundox.maximo.util.ValidateData;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dao.UsuarioDAO;
import mx.com.mundox.maximo.web.dto.LoginDTO;
import mx.com.mundox.maximo.web.dto.RolesDTO;
import mx.com.mundox.maximo.web.dto.UsuarioDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.owasp.encoder.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("loginService")
public class LoginServiceImpl implements UserDetailsService
{
//	LoginService, 
//	eventAuthenticationFailureHandler
	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Resource
	UsuarioDAO usuarioDao;
	
	@Autowired
    private HttpServletRequest request;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LoginDTO usuarioExiste;
		UsuarioDTO contrasenaUserbd = null;

		try {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
			String ipAddres = request.getLocalAddr();
			String ipAddresAuth = request.getLocalAddr();
			log.error("IP_DE_LOGIN_SERVICES: !", ipAddres);
			log.error("IP_DE_AUTh: !", ipAddresAuth);
//			********************************FIN_IP_************************************getMenuPorClaveRole
			usuarioExiste = usuarioDao.getUsuarioLoginInfo(username);
			System.out.println("JIGANTE_LOBO_ANTES: ");
			LoginDTO usuarios = usuarioDao.getUsuarioCargaListaRoles(username);
			System.out.println("JIGANTE_LOBO: "+ usuarios.toString());

			contrasenaUserbd = usuarioDao.getContrasenaUsuario(username);

			String contraBd = contrasenaUserbd.getPassword();
			String contraCl = usuarios.getUserPassword();
	
			if (!usuarioExiste.getExiste()) {
				
				throw new UsernameNotFoundException(
						"Error en el login:  usuario o contraseaña en el sistema!");
			} 
			else {

					usuarios.setUsuarioHabilitado(true);
					
					List<GrantedAuthority> authorities = usuarios.getRoles().stream()
							.map(role -> new SimpleGrantedAuthority(role.getDescripcionRol()))
							.peek(authority -> log.info("Role-LoginSErviceImpls: " + authority.getAuthority()))
							.collect(Collectors.toList());



						UserDetails user = new User(usuarios.getIdUser(), usuarios.getUserPassword(), usuarios.getUsuarioHabilitado(),
								true, true, true, authorities);
					
						return user;
						

			}
			
		} catch (ConexionException e) {
			// TODO Auto-generated catch block
			System.out.println("|||||||||||||||||||||||||||||||=========================LOG>>>>:" + e);
			e.printStackTrace();
		}
		return null;
		
	
		
	}
	




	


}
