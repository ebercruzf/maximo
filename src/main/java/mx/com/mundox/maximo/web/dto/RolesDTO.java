package mx.com.mundox.maximo.web.dto;

public class RolesDTO {
    private String descripcionRol;
    private String usuario;
    
    public RolesDTO() {
	}
    
	public RolesDTO(String descripcionRol, String usuario) {
		this.descripcionRol = descripcionRol;
		this.usuario = usuario;
	}
	
	public String getDescripcionRol() {
		return descripcionRol;
	}
	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "RolesDTO [descripcionRol=" + descripcionRol + ", usuario=" + usuario + "]";
	}
    
    
}
