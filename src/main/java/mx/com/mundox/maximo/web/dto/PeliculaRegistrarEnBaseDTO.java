package mx.com.mundox.maximo.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PeliculaRegistrarEnBaseDTO {

	private int id;
	private String imdbID;
	private String nombreBusqueda;
	private String comentarios;
	private String puntuacionRating;
	private String poster;
	private String fechaCuandoLaVi;
	private String usuario;
	
	
	
}
