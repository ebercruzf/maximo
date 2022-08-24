package mx.com.mundox.maximo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
	/**
	 * Controlador que manda a llamar a la pagina index
	 * @author eber.cruz
	 *
	 */

@Controller
public class IndexController {
	
	@Autowired
	private HttpServletRequest requestSession;
	
//	@Secured({"ROLE_Coordinador_ClinicoS"})
	@RequestMapping({"/", "/index", "/maximo"})
	public String index(Model model) {

		String banderaLogin = (String) requestSession.getSession().getAttribute("banderaLogin");
		if(banderaLogin=="adelante") {
			return "index";
		}else {
			login();
			return "login";
		}
	}
	
	@RequestMapping({"/login"})
	public String login() {

		return "login";
	}
	

}
