package mx.com.mundox.maximo.web.dto;

public class RolMenusDTO {
	
	private String claveRol;
	private String claveMenu;
	private String decripcionMenu;
	private String rutaUrlMenu;
	private String padre;
	private String orden;
	private Boolean activo;
	
	public RolMenusDTO() {
		
	}
	
	public RolMenusDTO(String claveRol, String claveMenu, String decripcionMenu, String rutaUrlMenu, String padre,
			String orden, Boolean activo) {
		this.claveRol = claveRol;
		this.claveMenu = claveMenu;
		this.decripcionMenu = decripcionMenu;
		this.rutaUrlMenu = rutaUrlMenu;
		this.padre = padre;
		this.orden = orden;
		this.activo = activo;
	}
	public String getClaveRol() {
		return claveRol;
	}
	public void setClaveRol(String claveRol) {
		this.claveRol = claveRol;
	}
	public String getClaveMenu() {
		return claveMenu;
	}
	public void setClaveMenu(String claveMenu) {
		this.claveMenu = claveMenu;
	}
	public String getDecripcionMenu() {
		return decripcionMenu;
	}
	public void setDecripcionMenu(String decripcionMenu) {
		this.decripcionMenu = decripcionMenu;
	}
	public String getRutaUrlMenu() {
		return rutaUrlMenu;
	}
	public void setRutaUrlMenu(String rutaUrlMenu) {
		this.rutaUrlMenu = rutaUrlMenu;
	}
	public String getPadre() {
		return padre;
	}
	public void setPadre(String padre) {
		this.padre = padre;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	@Override
	public String toString() {
		return "RolMenus [claveRol=" + claveRol + ", claveMenu=" + claveMenu + ", decripcionMenu=" + decripcionMenu
				+ ", rutaUrlMenu=" + rutaUrlMenu + ", padre=" + padre + ", orden=" + orden + ", activo=" + activo + "]";
	}
	

}
