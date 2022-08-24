package mx.com.mundox.maximo.web.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import mx.com.mundox.maximo.util.ConexionBD;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dao.RegistrarAltaPeliculaDAOService;
import mx.com.mundox.maximo.web.dto.PeliculaRegistrarEnBaseDTO;

@Repository
public class RegistrarAltaPeliculaDAOServiceImpl extends ConexionBD implements Serializable, RegistrarAltaPeliculaDAOService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger logger = LoggerFactory.getLogger(RegistrarAltaPeliculaDAOServiceImpl.class);
	
	private final static StringBuilder insertPelicula = new StringBuilder()
			.append("INSERT INTO PELICUAS (ID, IMDBID, TITULO, COMENTARIOS, PUNTUACIONRATING, POSTER, FECHA_CUANDO_LAVI, CVE_USUARIO) ")
			.append("VALUES (null, ?,?,?,?,?,?,?)");
	
	@Override
	public int registrarPeliculaenBase(PeliculaRegistrarEnBaseDTO registro) throws ConexionException {
		int respuesta = 0;
		   

		
		PreparedStatement psInsert = null;
		
		ResultSet rs = null;
		Connection con = null;
		
		try {
			con = getConnectionMsql();
			con.setAutoCommit(false);

			psInsert = con.prepareStatement(insertPelicula.toString());
			
;
			psInsert.setString(1, registro.getImdbID());
			psInsert.setString(2, registro.getNombreBusqueda());
			psInsert.setString(3, registro.getComentarios());
			psInsert.setString(4, registro.getPuntuacionRating());
			psInsert.setString(5, registro.getPoster());
			
			
//			java.util.Date utilDate =  new SimpleDateFormat("dd/MM/yyyy").parse(registro.getFechaCuandoLaVi());
//			java.sql.Date fechaFormato = new java.sql.Date(utilDate.getTime()); 
		
			
			psInsert.setString(6, registro.getFechaCuandoLaVi());
			psInsert.setString(7, registro.getUsuario());
			
			int resultado = 0;
			resultado = psInsert.executeUpdate();
			
		    
		if (resultado==1) {
				con.commit();
				respuesta = 1;
				logger.info("************ Query UsuarioDAO.registraStatusHistoricoUSuario exitoso ***********");
		}else if (resultado == 0){
			throw new SQLException();
		}
		con.setAutoCommit(true);
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
			
				if (psInsert != null && !psInsert.isClosed()) {
					psInsert.close();
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

	
	
	
//	 **************************************************
//	 registraStatusHistoricoUSuarioINICION SESSION
//	 **************************************************
//	 public Boolean registraStatusHistoricoUSuarioInicioSessionExitoso (String usuario, String clavePrespuestal, String ip, int status)throws ConexionException{ 
		
		 
		 
}
