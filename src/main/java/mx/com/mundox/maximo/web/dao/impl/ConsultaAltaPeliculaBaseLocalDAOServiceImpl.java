package mx.com.mundox.maximo.web.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import mx.com.mundox.maximo.util.ConexionBD;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dao.ConsultaAltaPeliculaBaseLocalDAOService;
import mx.com.mundox.maximo.web.dao.UsuarioDAO;
import mx.com.mundox.maximo.web.dto.LoginDTO;
import mx.com.mundox.maximo.web.dto.PeliculaRegistrarEnBaseDTO;

@Repository
public class ConsultaAltaPeliculaBaseLocalDAOServiceImpl extends ConexionBD implements Serializable, ConsultaAltaPeliculaBaseLocalDAOService{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = LoggerFactory.getLogger(UsuarioDAO.class);
	
	private final static StringBuilder consultaPeliculaPorUsuairo = new StringBuilder()
			.append("SELECT imdbid,cve_usuario,  titulo, comentarios, puntuacionrating, poster, fecha_cuando_lavi ")
			.append("FROM PELICUAS PELI WHERE PELI.titulo = ? AND PELI.cve_usuario = ? ");
	

	@Override
	public PeliculaRegistrarEnBaseDTO consultaPeliculaenBaseLocal(PeliculaRegistrarEnBaseDTO registro) throws ConexionException {
//		int respuesta = 0;
		PeliculaRegistrarEnBaseDTO respuesta = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		Connection con = null;
		
		try {
			con = getConnectionMsql();
			con.setAutoCommit(false);

			ps = con.prepareStatement(consultaPeliculaPorUsuairo.toString());

			ps.setString(1, registro.getNombreBusqueda());
			ps.setString(2, registro.getUsuario());
			
			
			int resultado = 0;
			rs = ps.executeQuery();
			
				if (rs.next()) {
					respuesta = new PeliculaRegistrarEnBaseDTO();
					respuesta.setImdbID(rs.getString("imdbid"));  
					respuesta.setNombreBusqueda(rs.getString("titulo"));  
					respuesta.setPuntuacionRating(rs.getString("puntuacionrating"));
					respuesta.setFechaCuandoLaVi(rs.getString("fecha_cuando_lavi").toString());
					respuesta.setComentarios(rs.getString("comentarios"));
					respuesta.setPoster(rs.getString("poster"));
					respuesta.setUsuario(rs.getString("cve_usuario"));
					
					
				}
			
		    
		
				logger.info("************ Query UsuarioDAO.registraStatusHistoricoUSuario exitoso ***********");
	
		
//			
		}catch (SQLException sqlException) {				logger.error(sqlException.getMessage());
            throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error con la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxx");

        } finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
					logger.info(
							"========================= ResultSet ConsultaDictamenRiesgoTrabajoDAO.registraStatusHistoricoUSuario cerrado ===========================");
				}
			
				if (ps != null && !ps.isClosed()) {
					ps.close();
					logger.info(
							"========================= PreparedStatement ConsultaDictamenRiesgoTrabajoDAO.registraStatusHistoricoUSuario cerrado ===========================");
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new ConexionException(
						"xxxxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error al intentar cerrar la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxxxx");
			}

		}
		
	return respuesta;
	}

}
