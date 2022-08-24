package mx.com.mundox.maximo.web.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.com.mundox.maximo.util.ConexionBD;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dto.UsuarioDTO;

/**
 * DAO que cierra la sesion.
 * @author eber.cruz
 *
 */
@Repository
public class CierraSesionDAO extends ConexionBD implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7923431776832371004L;
	
	private static final Logger logger = LoggerFactory.getLogger(CierraSesionDAO.class);
	
	@Autowired
	ConexionBD conexionBD;
	
	private final static StringBuilder sqlConsultaUsuarioSessionActiva=new StringBuilder()
			.append(" SELECT CVE_USUARIO, IP FROM SIST_SESION_ACTIVA WHERE CVE_USUARIO = ? ");
	
	private final static StringBuilder obtieneClave = new StringBuilder()
			.append(" SELECT CVE FROM SIST_USUARIO ")
			.append(" WHERE CVE_USUARIO = ? ");
	
	private final static StringBuilder sqlBorrarUsuarioSessionActiva=new StringBuilder()
			.append(" DELETE FROM SIST_SESION_ACTIVA WHERE CVE_USUARIO  = ? ");
	
	private final static StringBuilder actualizaStatusUsuarioInicioFinSession = new StringBuilder()
			.append(" UPDATE SIST_USUARIO SET  CVE_ESTATUS_USUARIO =  ?  WHERE CVE_USUARIO = ?");
	
	private final static StringBuilder insertaEnHistoricoUsuario = new StringBuilder()
			.append(" INSERT INTO HISTORICO_USUARIO (CVE_HISTORICO_USUARIO, CVE_USUARIO, CVE_ESTATUS_USUARIO, CVE_PRESUPUESTAL, FECHA, IP )  VALUES ")
			.append(" (SEQ_SIST_HIST_USER.NEXTVAL, ?, ?, ?, SYSDATE, ? ) ");
	
	private final static StringBuilder sqlCargarUsuario = new StringBuilder()
			.append("SELECT SU.CVE_USUARIO, ")
			.append("       SU.NOMBRE, ")
			.append("       SU.APELLIDO_PATERNO, ")
			.append("       SU.APELLIDO_MATERNO, ")
			.append("       SU.CVE_ESTATUS_USUARIO AS ESTATUS ")
			.append("FROM SIST_USUARIO SU ")
			.append("JOIN ROL R ON R.ID_ROL=SU.ID_ROL ")
			.append("JOIN CAT_ESTATUS_USUARIO CEU ON CEU.CVE_ESTATUS_USUARIO=SU.CVE_ESTATUS_USUARIO ")
			.append("WHERE SU.CVE_USUARIO = ? ");
	
	public Boolean getUsuarioCerrarSessionActiva(String usuario) throws ConexionException{
		logger.info("=============Iniciando metodo de cerrado de session==============");
	
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Boolean respuestaExiste = false;
		UsuarioDTO usuarioSesionActiva = null;
		
		try {
//			con = getConnection();
			con = getConnectionMsql();
			
			ps = con.prepareStatement(sqlConsultaUsuarioSessionActiva.toString());
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				usuarioSesionActiva = new UsuarioDTO();
				
				usuarioSesionActiva.setCveUsuario(rs.getString("CVE_USUARIO"));
				
				if(usuario.equals(usuarioSesionActiva.getCveUsuario())){
					respuestaExiste = true;
				}
			  }
			
		}catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
            throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxx CierraSesionDAO.getUsuarioCerrarSessionActiva: Ocurrió un error con la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxx");

        } finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
					logger.info(
							"========================= ResultSet CierraSesionDAO.getUsuarioCerrarSessionActiva cerrado ===========================");
				}
				if (ps != null && !ps.isClosed()) {
					ps.close();
					logger.info(
							"========================= PreparedStatement CierraSesionDAO.getUsuarioCerrarSessionActiva cerrado ===========================");
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
		
	return respuestaExiste;
		
	}
	

	 
	
	public Boolean registraStatusHistoricoUSuarioCerrarSessionActiva (String usuario, String clavePrespuestal, String ip, int status)throws ConexionException{ 
			
		 
		 	Boolean respuesta = false;
		   
		 	PreparedStatement psDelete = null;
		 	PreparedStatement psUpdate = null;
			PreparedStatement psInsert = null;
			
			ResultSet rs = null;
			Connection con = null;
			try {
//					con = getConnection();
				    con = getConnectionMsql();
					con.setAutoCommit(false);

					psDelete = con.prepareStatement(sqlBorrarUsuarioSessionActiva.toString());
					psUpdate = con.prepareStatement(actualizaStatusUsuarioInicioFinSession.toString());
					psInsert = con.prepareStatement(insertaEnHistoricoUsuario.toString());
					
					psDelete.setString(1, usuario);
					
					psUpdate.setInt(1, status);
					psUpdate.setString(2, usuario);
					
					psInsert.setString(1, usuario);
					psInsert.setInt(2, status);
					psInsert.setString(3, clavePrespuestal);
					psInsert.setString(4, ip);
					
					int resultado1 = 0;
					int resultado2 = 0;
					int resultado3 = 0;
					resultado1 = psDelete.executeUpdate();
					resultado2 = psUpdate.executeUpdate();
					resultado3 = psInsert.executeUpdate();
					
				if (resultado1 == 1) {
					if (resultado2 == 1) {
						if (resultado3 == 1) {
							con.commit();
							respuesta = true;
							logger.info("************ Query CierraSesionDAO.registraStatusHistoricoUSuarioCerrarSessionActiva exitoso ***********");
						} else {
							con.rollback();
							throw new SQLException();
						}

					} else if (resultado2 == 0) {
						con.rollback();
						throw new SQLException();
					}
				} else {
					con.rollback();
					throw new SQLException();
				}
				  
				con.setAutoCommit(true);
				
			}catch (SQLException sqlException) {				
				logger.error(sqlException.getMessage());
	            throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error con la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxx");

	        } finally {
				try {
					if (rs != null && !rs.isClosed()) {
						rs.close();
						logger.info(
								"========================= ResultSet CierraSesionDAO.registraStatusHistoricoUSuarioCerrarSessionActiva cerrado ===========================");
					}
					if (psDelete != null && !psDelete.isClosed()) {
						psDelete.close();
						logger.info(
								"========================= PreparedStatement CierraSesionDAO.registraStatusHistoricoUSuarioCerrarSessionActiva psDelete cerrado ===========================");
					}
					if (psUpdate != null && !psUpdate.isClosed()) {
						psUpdate.close();
						logger.info(
								"========================= PreparedStatement CierraSesionDAO.registraStatusHistoricoUSuarioCerrarSessionActiva psUpdate cerrado ===========================");
					}
					if (psInsert != null && !psInsert.isClosed()) {
						psInsert.close();
						logger.info(
								"========================= PreparedStatement CierraSesionDAO.registraStatusHistoricoUSuarioCerrarSessionActiva psInsert cerrado ===========================");
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
