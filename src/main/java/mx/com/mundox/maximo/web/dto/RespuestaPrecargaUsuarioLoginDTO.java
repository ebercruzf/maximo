package mx.com.mundox.maximo.web.dto;

import java.util.List;

/**
 * Objeto para cargar los datos de usuario al loguearse.
 * @author eber.cruz
 *
 */
public class RespuestaPrecargaUsuarioLoginDTO {

	private UsuarioDTO usuario;
	private List<RolMenusDTO> listaRolesMenus;
	private String ip;
	
	private  List<CredencialUsuarioDTO> listaCredencialUsuarioFinerioDTO;
	
	public RespuestaPrecargaUsuarioLoginDTO() {
	}
	
	public RespuestaPrecargaUsuarioLoginDTO(UsuarioDTO usuario, List<RolMenusDTO> listaRolesMenus, List<CredencialUsuarioDTO> credencialUsuarioDTO) {
		this.usuario = usuario;
		this.listaRolesMenus = listaRolesMenus;
		this.listaCredencialUsuarioFinerioDTO = credencialUsuarioDTO;
	}
	
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public List<RolMenusDTO> getListaRolesMenus() {
		return listaRolesMenus;
	}
	public void setListaRolesMenus(List<RolMenusDTO> listaRolesMenus) {
		this.listaRolesMenus = listaRolesMenus;
	}
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	


	public List<CredencialUsuarioDTO> getListaCredencialUsuarioFinerioDTO() {
		return listaCredencialUsuarioFinerioDTO;
	}

	public void setListaCredencialUsuarioFinerioDTO(List<CredencialUsuarioDTO> listaCredencialUsuarioDTO) {
		this.listaCredencialUsuarioFinerioDTO = listaCredencialUsuarioDTO;
	}

	@Override
	public String toString() {
		return "RespuestaPrecargaUsuarioLoginDTO [usuario=" + usuario + ", listaRolesMenus=" + listaRolesMenus + ", ip="
				+ ip + ", listaCredencialUsuarioFinerioDTO=" + listaCredencialUsuarioFinerioDTO + "]";
	}

	

	
	
}
