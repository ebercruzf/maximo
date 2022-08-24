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
public class PeliculaResultadoConsultaGetDTO {
	
	private String tituloPeliculaBuscarWS;
	private String imdbID;
	private String poster;
	
	

}
