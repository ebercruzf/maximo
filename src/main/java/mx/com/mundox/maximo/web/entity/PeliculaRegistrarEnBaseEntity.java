//package mx.com.mundox.maximo.web.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//@Entity
//@Table(name="peliculas")
//public class PeliculaRegistrarEnBaseEntity implements Serializable{
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private String imdbID;
//	private String nombreBusqueda;
//	private String puntuacionRating;
//	private String poster;
//	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getImdbID() {
//		return imdbID;
//	}
//
//	public void setImdbID(String imdbID) {
//		this.imdbID = imdbID;
//	}
//
//	public String getNombreBusqueda() {
//		return nombreBusqueda;
//	}
//
//	public void setNombreBusqueda(String nombreBusqueda) {
//		this.nombreBusqueda = nombreBusqueda;
//	}
//
//	public String getPuntuacionRating() {
//		return puntuacionRating;
//	}
//
//	public void setPuntuacionRating(String puntuacionRating) {
//		this.puntuacionRating = puntuacionRating;
//	}
//
//	public String getPoster() {
//		return poster;
//	}
//
//	public void setPoster(String poster) {
//		this.poster = poster;
//	}
//
//	public Date getFechaCuandoLaVi() {
//		return fechaCuandoLaVi;
//	}
//
//	public void setFechaCuandoLaVi(Date fechaCuandoLaVi) {
//		this.fechaCuandoLaVi = fechaCuandoLaVi;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	@Column(name="fechalavi")
//	@Temporal(TemporalType.DATE)
//	private Date fechaCuandoLaVi;
//	
//}
