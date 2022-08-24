package mx.com.mundox.maximo.web.dto;

/** 
 * 
 * Datos para las consultas de usuario
 * **/

public class UsuarioDTO{
	
//	EL SIGUIENTE CODIGO DEBE DESCOMENTARSE PARA USARE CON LA NUEVA BASE Y CONECCION DE QA:
	private String cveUsuario;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String password;


	private String idRol;
	private String rol;

	private String mail;


	public UsuarioDTO() {
	}


	public UsuarioDTO(String cveUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String password,
			String idRol, String rol, String mail) {
		
		this.cveUsuario = cveUsuario;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.password = password;
		this.idRol = idRol;
		this.rol = rol;
		this.mail = mail;
	}


	public String getCveUsuario() {
		return cveUsuario;
	}


	public void setCveUsuario(String cveUsuario) {
		this.cveUsuario = cveUsuario;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidoPaterno() {
		return apellidoPaterno;
	}


	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}


	public String getApellidoMaterno() {
		return apellidoMaterno;
	}


	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getIdRol() {
		return idRol;
	}


	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	@Override
	public String toString() {
		return "UsuarioDTO [cveUsuario=" + cveUsuario + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", password=" + password + ", idRol=" + idRol + ", rol="
				+ rol + ", mail=" + mail + "]";
	}

	

	
	
}
