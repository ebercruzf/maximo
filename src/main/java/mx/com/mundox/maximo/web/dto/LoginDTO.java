package mx.com.mundox.maximo.web.dto;

import java.util.List;

/**
 * clase para autenticar el usuario;
 * @author lenovo
 *
 */
public class LoginDTO {

	private String idUser;
	private String nombre;
	private String primerApellido; //Apellido Paterno
	private String segundoApellido; //Apellido Matenro
	private String nombreCompleto;
	private String userPassword;

	private String mail;
	private String fechaBaja;
	private Integer usuarioBloqueado;
	private Boolean existe;
	private String sessionIniciadaMismoIP;
	
	private List<RolesDTO> roles ;
	private Boolean usuarioHabilitado;
	private String estatusUsuario;
	
	private Integer intentos;
	
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public Integer getUsuarioBloqueado() {
		return usuarioBloqueado;
	}
	public void setUsuarioBloqueado(Integer usuarioBloqueado) {
		this.usuarioBloqueado = usuarioBloqueado;
	}
	public Boolean getExiste() {
		return existe;
	}
	public void setExiste(Boolean existe) {
		this.existe = existe;
	}
	public List<RolesDTO> getRoles() {
		return roles;
	}
	public void setRoles(List<RolesDTO> roles) {
		this.roles = roles;
	}
	public Boolean getUsuarioHabilitado() {
		return usuarioHabilitado;
	}
	public void setUsuarioHabilitado(Boolean usuarioHabilitado) {
		this.usuarioHabilitado = usuarioHabilitado;
	}
	public String getEstatusUsuario() {
		return estatusUsuario;
	}
	public void setEstatusUsuario(String estatusUsuario) {
		this.estatusUsuario = estatusUsuario;
	}
	public String getSessionIniciadaMismoIP() {
		return sessionIniciadaMismoIP;
	}
	public void setSessionIniciadaMismoIP(String sessionIniciadaMismoIP) {
		this.sessionIniciadaMismoIP = sessionIniciadaMismoIP;
	}
	public Integer getIntentos() {
		return intentos;
	}
	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}
	@Override
	public String toString() {
		return "LoginDTO [idUser=" + idUser + ", nombre=" + nombre + ", primerApellido=" + primerApellido
				+ ", segundoApellido=" + segundoApellido + ", nombreCompleto=" + nombreCompleto + ", userPassword="
				+ userPassword + ", mail=" + mail + ", fechaBaja=" + fechaBaja + ", usuarioBloqueado="
				+ usuarioBloqueado + ", existe=" + existe + ", sessionIniciadaMismoIP=" + sessionIniciadaMismoIP
				+ ", roles=" + roles + ", usuarioHabilitado=" + usuarioHabilitado + ", estatusUsuario=" + estatusUsuario
				+ ", intentos=" + intentos + "]";
	}
	
	
	

	
}
