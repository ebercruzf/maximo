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
public class CredencialUsuarioDTO {
	
	private String idCveUsuario;
	private String cveUsuario;
	private String mail;

	private String idCredencial;

	

}
