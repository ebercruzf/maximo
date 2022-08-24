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
import mx.com.mundox.maximo.web.dao.ActualizarAltaPeliculaDAOService;
import mx.com.mundox.maximo.web.dao.PeliculaEliminiarRegistroBaseDatosDAOService;
import mx.com.mundox.maximo.web.dto.PeliculaRegistrarEnBaseDTO;

@Repository
public class PeliculaEliminiarRegistroBaseDatosDAOServiceImpl extends ConexionBD implements Serializable, PeliculaEliminiarRegistroBaseDatosDAOService{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = LoggerFactory.getLogger(PeliculaEliminiarRegistroBaseDatosDAOServiceImpl.class);

	private final static StringBuilder eliminarPelicula = new StringBuilder() 
			.append("DELETE FROM PELICUAS ")
			.append("  WHERE IMDBID = ?   AND CVE_USUARIO=? ");


@Override
public int eliminarPeliculaenBase(PeliculaRegistrarEnBaseDTO registro) throws ConexionException {
	PreparedStatement psUpdate = null;
	int respuesta = 0;
	ResultSet rs = null;
	Connection con = null;
	try {
		con = getConnectionMsql();
		con.setAutoCommit(false);

		psUpdate = con.prepareStatement(eliminarPelicula.toString());
		
		
		psUpdate.setString(1, registro.getImdbID());
		psUpdate.setString(2, registro.getUsuario());
		
		int resultado = 0;
		
		resultado = psUpdate.executeUpdate();
	    
	if (resultado>=1) {
		    respuesta=1;
			con.commit();
			
			logger.info("************ Query PeliculaEliminiarRegistroBaseDatosDAOServiceImpl.eliminarPeliculaenBase exitoso ***********");
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
						"========================= ResultSet PeliculaEliminiarRegistroBaseDatosDAOServiceImpl.eliminarPeliculaenBase cerrado ===========================");
			}
		
			if (psUpdate != null && !psUpdate.isClosed()) {
				psUpdate.close();
				logger.info(
						"========================= PreparedStatement PeliculaEliminiarRegistroBaseDatosDAOServiceImpl.eliminarPeliculaenBase cerrado ===========================");
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
