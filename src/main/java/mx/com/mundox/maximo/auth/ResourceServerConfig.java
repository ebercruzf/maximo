package mx.com.mundox.maximo.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * en esta clase se establecen la configuracion de serguridad del servidor de recursos.
 * @author eber.cruz
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/resources/publico/img/**",
				"/api/cliente/consulta/pelicula/get/**",
				"/resources/static/publico/librerias/bootstrap/js/**",
				"/resources/static/publico/librerias/bootstrap/css/**",
				"/resources/static/publico/librerias/angular/**",
				"/resources/static/publico/librerias/angular/angular.js",
				"/resources/static/publico/librerias/jquery/**",
				"/index",
				"/resources/static/publico/css/cargando.css",
				"/resources/static/js/**",
				"/resources/static/privado/controlador/**",
				"/resources/static/img/**",
				"/resources/static/views/publico/**",
				"/resources/static/css/mundox/**",
				"/resources/static/css/**",
				"/resources/static/publicos/css/**",
				"/resources/static/webfonts/**",
				"/resources/static/publicos/css/**",
				"/resources/static/views/publico/cargando.html",
				"/resources/static/publico/pages/cargando.html",
				"/resources/static/publico/librerias/otras/**",
				"/resources/static/publico/controlador/**",
				"/resources/static/publico/librerias/bootstrap/gh-pages/**",
				"/resources/static/publico/librerias/css/**",
				"/resources/static/views/modal/modalContent.html",
//				*************************************************************
				"/resources/static/publico/controlador/ctrLoginGood.js",
				"/resources/static/publico/controlador/service/autenticacionServiceLog.js",
				"/resources/static/publico/controlador/service/serviceClientLogin.js",
				"/resources/static/privado/controlador/homepage.js",
				"/resources/static/privado/controlador/serviciosValidar.js",

//				*********************comienza ruta buena MAXIMO***************************
				"/resources/static/views/consulta/informacion/_bandejaprincipal.html",

				"/resources/static/templates/login.html",		
				
				"/resources/static/views/consulta/otras/acerca.html",		

				"/maximo/resources/static/views/publico/cargando.html",
//				*****************************************
				"/resources/static/views/consulta/informacion/_consultaBusquedaPeliculaPorNombre.html",
				"/busquedapeliculapornombre",
				"/resources/static/views/administracion/usuario/_cambioPassword.html",
				
				"http://localhost:7005/maximo/oauth/token",
				
				"/oauth/authorize", "/oauth/confirm_access","/oauth/token",
				"/api/clientes/page/**", "/api/uploads/img/**").permitAll()
		.antMatchers("/favicon.ico", "/publico/img/**").permitAll()
		.antMatchers("/login.html","/login", "/cargando.html", "/logout.do").permitAll()
		
		.antMatchers(HttpMethod.POST,"/api/cliente/consulta/pelicula").permitAll()
		.antMatchers(HttpMethod.POST,"/api/cliente/agrega/pelicula").permitAll()
		.antMatchers(HttpMethod.POST,"/api/cliente/consulta/pelicula/bdlocal").permitAll()
		.antMatchers(HttpMethod.POST,"/api/cliente/editar/pelicula/bdlocal").permitAll()
		.antMatchers(HttpMethod.POST,"/api/cliente/eliminar/pelicula/bdlocal").permitAll()
		
		
		.antMatchers(HttpMethod.POST,"/results-asynch").permitAll()
		
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
		
		
		

	}


	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:7005")); //8097 - 8091- 172.30.4.55
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
