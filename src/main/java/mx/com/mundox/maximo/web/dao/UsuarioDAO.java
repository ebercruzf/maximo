package mx.com.mundox.maximo.web.dao;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.com.mundox.maximo.util.ConexionBD;
import mx.com.mundox.maximo.util.exception.ConexionException;
import mx.com.mundox.maximo.web.dto.LoginDTO;
import mx.com.mundox.maximo.web.dto.RolMenusDTO;
import mx.com.mundox.maximo.web.dto.RolesDTO;
import mx.com.mundox.maximo.web.dto.UsuarioDTO;

/**
 * Dao para autenticarse el usuario
 * @author eber.cruz
 *
 */

@Repository
public class UsuarioDAO extends ConexionBD implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -412059149381230916L;

	private final static Logger logger = LoggerFactory.getLogger(UsuarioDAO.class);
	
	@Autowired
	ConexionBD conexionBD;
	

	private final static StringBuilder sqlConsultaUsuario = new StringBuilder()
			.append("SELECT COUNT(*) AS EXISTE FROM USUARIO WHERE CVE_USUARIO = ?");
	

	private final static StringBuilder sqlCargarUsuario = new StringBuilder()
			.append(" SELECT SU.ID_CVE_USUARIO, ")
			.append(" SU.CVE_USUARIO, ")
			.append(" SU.NOMBRE,  ")
			.append(" SU.APELLIDO_PATERNO, ")
			.append(" SU.APELLIDO_MATERNO, ")
			.append(" SU.PASSWORD_U, ")
			.append(" SU.ID_ROL, SU.LOCALIZACION, SU.MAIL, SU.CVE_ESTATUS_USUARIO, ")
			.append(" R.DESCRIPCION AS ROL,    SU.MAIL ")
			.append(" FROM USUARIO SU ")
			.append(" JOIN ROL R ON R.ID_ROL=SU.ID_ROL ") 
			.append(" WHERE SU.CVE_USUARIO  = ? ");
	
	
	private final static StringBuilder sqlCargarListaRolesUsuario = new StringBuilder()
			.append("SELECT ID_CVE_USUARIO, SU.CVE_USUARIO, ")
			   .append("   SU.NOMBRE, ")
			   .append("   SU.APELLIDO_PATERNO, ")
			   .append("   SU.APELLIDO_MATERNO, ")
			   .append("  SU.PASSWORD_U, ")
			   .append("  R.CVEROL, ")
			   .append("  R.DESCRIPCION AS DES_ROL, ")
			   .append("  SU.MAIL  ")
			   .append(" FROM ")
			   .append(" USUARIO SU ")
			   .append(" JOIN ROL R ON R.ID_ROL=SU.ID_ROL ")
			   .append(" WHERE SU.CVE_USUARIO = ? " ); 
	
	private final static StringBuilder sqlCargarListaMenuPorRolesUsuario = new StringBuilder()
			.append("SELECT R.CVEROL AS CVEROL, R.DESCRIPCION, R.ACTIVO  ")
			.append("FROM ROL R ")
			.append("WHERE R.ID_ROL = ? ")
			.append("ORDER BY R.CVEROL ");
	
	
	private final static StringBuilder sqlConsultaContraseana = new StringBuilder() //checa status base nueva
			.append("SELECT PASSWORD_U AS CONTRASENA FROM USUARIO WHERE CVE_USUARIO = ? ");

	

	
	 public LoginDTO getUsuarioLoginInfo(String loginUser) throws ConexionException{ 
		 
		
		 LoginDTO logindto = null; 
		 logger.info("INICIANDO_DAO_DATA_SOURCE_logindto151: " + loginUser);
		 
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = null;
			try {
			    con = getConnectionMsql();
				ps = con.prepareStatement(sqlConsultaUsuario.toString());
				ps.setString(1, loginUser);
				rs = ps.executeQuery();
				if (rs.next()) {
					logindto = new LoginDTO();
					logindto.setExiste(rs.getBoolean("EXISTE"));  
					logindto.setUsuarioBloqueado(0);
				}
				logger.info(
						"**************************************** Query UsuarioDAO.getUsuarioLoginInfo exitoso ****************************************");
			}catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
	            throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error con la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxx");

	        } finally {
	            try {
	                if (rs != null && !rs.isClosed()) {
	                    rs.close();
	                    logger.info("========================= ResultSet UsuarioDAO.getUsuarioLoginInfo cerrado ===========================");
	                }
	                if (ps != null  && !ps.isClosed()) {
	                    ps.close();
	                    logger.info("========================= PreparedStatement UsuarioDAO.getUsuarioLoginInfo cerrado ===========================");
	                }
	                if (con != null) {
	                	con.close();
	                }
	            } catch (SQLException sqlException) {
	            	logger.error(sqlException.getMessage());
	                throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error al intentar cerrar la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxxxx");
	            }

	        } 
			
		return logindto;
	
	    }
	 
	 public LoginDTO getExisteUsuario(String loginUser) throws ConexionException{ 
			
		 LoginDTO logindto = null; 
		 logger.info("INICIANDO_DAO_DATA_SOURCE_getExisteUsuario: " + loginUser);
		 
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = null;
			try {
			    con = getConnectionMsql();
				ps = con.prepareStatement(sqlConsultaUsuario.toString());
				ps.setString(1, loginUser);
				rs = ps.executeQuery();
				if (rs.next()) {
					logindto = new LoginDTO();
					logindto.setEstatusUsuario(rs.getString("EXISTE"));  
				}
				logger.info(
						"**************************************** Query UsuarioDAO.getExisteUsuario exitoso ****************************************");
			}catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
	            throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error con la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxx");

	        } finally {
	            try {
	                if (rs != null && !rs.isClosed()) {
	                    rs.close();
	                    logger.info("========================= ResultSet UsuarioDAO.getExisteUsuario cerrado ===========================");
	                }
	                if (ps != null  && !ps.isClosed()) {
	                    ps.close();
	                    logger.info("========================= PreparedStatement UsuarioDAO.getExisteUsuario cerrado ===========================");
	                }
	                if (con != null) {
	                	con.close();
	                }
	            } catch (SQLException sqlException) {
	            	logger.error(sqlException.getMessage());
	                throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error al intentar cerrar la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxxxx");
	            }

	        } 
			
		return logindto;
	
	    }
	 
//	 **************************************************
//	 Obtiene el estatus 2, 3, y 4
//	 **************************************************
	 public LoginDTO getEstatusUsuario(String loginUser) throws ConexionException{ 
			
		 LoginDTO logindto = null; 
		 logger.info("INICIANDO_DAO_DATA_SOURCE_logindto249: " + loginUser);
		 
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = null;
			try {
			    con = getConnectionMsql();
				ps = con.prepareStatement(sqlConsultaUsuario.toString());
				ps.setString(1, loginUser);
				rs = ps.executeQuery();
				if (rs.next()) {
					logindto = new LoginDTO();
					logindto.setEstatusUsuario(rs.getString("EXISTE"));  
				}
				logger.info(
						"**************************************** Query UsuarioDAO.getEstatusUsuario exitoso ****************************************");
			}catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
	            throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error con la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxx");

	        } finally {
	            try {
	                if (rs != null && !rs.isClosed()) {
	                    rs.close();
	                    logger.info("========================= ResultSet UsuarioDAO.getEstatusUsuario cerrado ===========================");
	                }
	                if (ps != null  && !ps.isClosed()) {
	                    ps.close();
	                    logger.info("========================= PreparedStatement UsuarioDAO.getEstatusUsuario cerrado ===========================");
	                }
	                if (con != null) {
	                	con.close();
	                }
	            } catch (SQLException sqlException) {
	            	logger.error(sqlException.getMessage());
	                throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error al intentar cerrar la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxxxx");
	            }

	        } 
			
		return logindto;
	
	    }
	 

//	 **************************************************
//	 CargaRoles
//	 **************************************************
	
	 public LoginDTO getUsuarioCargaListaRoles(String loginUser) throws ConexionException{ 
		 
			
		 LoginDTO logindto = null;
		 RolesDTO rol = null;
		 List<RolesDTO> listaRoles = new ArrayList<RolesDTO>();
		 logger.info("INICIANDO_DAO_DATA_SOURCE_logindto364: " + loginUser);
		 
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			Connection con = null;
			logger.info("INICIANDO_DAO_77777777888888881: " + loginUser);
			try {
			    con = getConnectionMsql();
				ps = con.prepareStatement(sqlCargarListaRolesUsuario.toString());
				ps.setString(1, loginUser);
				rs = ps.executeQuery();
			
				 logger.info("INICIANDO_DAO_777777778888888810: "+ sqlCargarListaRolesUsuario.toString());
				 logger.info("INICIANDO_DAO_777777778888888811: " + rs.next());
			
						 logger.info("INICIANDO_DAO_DATA_SOURCE_logindtifo364rs.next(): " );

					rol = new RolesDTO();
					logindto = new LoginDTO();
		
					rol.setDescripcionRol(rs.getString("DES_ROL"));  
					rol.setUsuario(rs.getString("CVE_USUARIO"));
					
					logger.info("INICIANDO_DAO_777777778888888812: " + rol.toString());
					logger.info("INICIANDO_despuesSET_logindtifo364rs.next(): " +rs.next());
					
					logindto.setUsuarioBloqueado(0);
					logger.info("INICIANDO_DAO_777777778888888813: " + logindto.toString());
					
					logindto.setIdUser(rs.getString("CVE_USUARIO"));
					logindto.setUserPassword(rs.getString("PASSWORD_U"));

					logger.info("INICIANDO_DAO_777777778888888814: " + logindto.toString());
					
					logger.info("LO_QUE_LLEVALISTArol: " + rol);
					
					listaRoles.add(rol);
					logindto.setRoles(listaRoles);

//				}
				logger.info("LO_QUE_LLEVALISTA87: " + logindto);
				logger.info(
						"**************************************** Query UsuarioDAO.getUsuarioCargaListaRoles exitoso ****************************************");
			}catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
	            throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error con la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxx");

	        } finally {
	            try {
	                if (rs != null && !rs.isClosed()) {
	                    rs.close();
	                    logger.info("========================= ResultSet UsuarioDAO.getUsuarioCargaListaRoles cerrado ===========================");
	                }
	                if (ps != null  && !ps.isClosed()) {
	                    ps.close();
	                    logger.info("========================= PreparedStatement UsuarioDAO.getUsuarioCargaListaRoles cerrado ===========================");
	                }
	                if (con != null) {
	                	con.close();
	                }
	            } catch (SQLException sqlException) {
	            	logger.error(sqlException.getMessage());
	                throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error al intentar cerrar la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxxxx");
	            }

	        } 
			
		return logindto;
	
	    }
	 
//	 **************************************************
//	 PrecargaRolesMenus
//	 **************************************************
	
	 public List<RolMenusDTO> getMenuPorClaveRole(String loginUser) throws ConexionException{ 
		 
		 RolMenusDTO rolMenu = null;
		 List<RolMenusDTO> listaRolMenu = new ArrayList<RolMenusDTO>();
		 logger.info("INICIANDO_DAO_DATA_SOURCE_logindto435: " + loginUser);
		 
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = null;
			try {
			    con = getConnectionMsql();
				ps = con.prepareStatement(sqlCargarListaMenuPorRolesUsuario.toString());
				ps.setString(1, loginUser);
				rs = ps.executeQuery();
				
				while (rs.next()) {
//					if(cont <1) {
					rolMenu = new RolMenusDTO();
						rolMenu.setClaveRol(rs.getString("CVEROL"));
//						rolMenu.setClaveMenu(rs.getString("CVE_MENU"));
						rolMenu.setDecripcionMenu(rs.getString("DESCRIPCION"));
//						rolMenu.setRutaUrlMenu(rs.getString("RUTA"));
//						rolMenu.setPadre(rs.getString("PARENT"));
//						rolMenu.setOrden(rs.getString("ORDEN"));
						rolMenu.setActivo(rs.getBoolean("ACTIVO"));
					
						
					
					logger.info("LO_QUE_LLEVALISTArol: " + rolMenu);
					
					listaRolMenu.add(rolMenu);

				}
				logger.info("LO_QUE_LLEVALISTA87_getMenuPorClaveRole: " + listaRolMenu);
				logger.info(
						"**************************************** Query UsuarioDAO.getMenuPorClaveRole exitoso ****************************************");
			}catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
	            throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error con la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxx");

	        } finally {
	            try {
	                if (rs != null && !rs.isClosed()) {
	                    rs.close();
	                    logger.info("========================= ResultSet UsuarioDAO.getMenuPorClaveRole cerrado ===========================");
	                }
	                if (ps != null  && !ps.isClosed()) {
	                    ps.close();
	                    logger.info("========================= PreparedStatement UsuarioDAO.getMenuPorClaveRole cerrado ===========================");
	                }
	                if (con != null) {
	                	con.close();
	                }
	            } catch (SQLException sqlException) {
	            	logger.error(sqlException.getMessage());
	                throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error al intentar cerrar la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxxxx");
	            }

	        } 
			
		return listaRolMenu;
	
	    }
	 
//	 **************************************************
//	 Carga información del usuario
//	 **************************************************
	 public UsuarioDTO getCargaDatosUsuario(String loginUser) throws ConexionException{ 
			
		 UsuarioDTO cargaUsuariodto = null; 
		 logger.info("INICIANDO_DAO_DATA_SOURCE_logindto501: " + loginUser);
		 
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = null;
			try {
			    con = getConnectionMsql();
				ps = con.prepareStatement(sqlCargarUsuario.toString());
				ps.setString(1, loginUser);
				rs = ps.executeQuery();
				if (rs.next()) {
					cargaUsuariodto = new UsuarioDTO();
					cargaUsuariodto.setCveUsuario(rs.getString("CVE_USUARIO"));
					cargaUsuariodto.setNombre(rs.getString("NOMBRE"));
					cargaUsuariodto.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
					cargaUsuariodto.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
					cargaUsuariodto.setIdRol(rs.getString("ID_ROL"));
					cargaUsuariodto.setRol(rs.getString("ROL"));
					cargaUsuariodto.setMail(rs.getString("MAIL"));
				}
				logger.info(
						"**************************************** Query UsuarioDAO.getCargaInfodeUsuario exitoso ****************************************");
			}catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
	            throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error con la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxx");

	        } finally {
	            try {
	                if (rs != null && !rs.isClosed()) {
	                    rs.close();
	                    logger.info("========================= ResultSet UsuarioDAO.getCargaInfodeUsuario cerrado ===========================");
	                }
	                if (ps != null  && !ps.isClosed()) {
	                    ps.close();
	                    logger.info("========================= PreparedStatement UsuarioDAO.getCargaInfodeUsuario cerrado ===========================");
	                }
	                if (con != null) {
	                	con.close();
	                }
	            } catch (SQLException sqlException) {
	            	logger.error(sqlException.getMessage());
	                throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error al intentar cerrar la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxxxx");
	            }

	        } 
			
		return cargaUsuariodto;
	
	    }

	 
//	 **************************************************
//	 Obtiene contraseña
//	 **************************************************
	 public UsuarioDTO getContrasenaUsuario(String contrasena) throws ConexionException{ 
		 
			
		 UsuarioDTO cargaUsuariodto = null; 
		 logger.info("INICIANDO_DAO_DATA_SOURCE_logindto921: " + contrasena);
		 
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = null;
			try {
			    con = getConnectionMsql();
				ps = con.prepareStatement(sqlConsultaContraseana.toString());
				ps.setString(1, contrasena);
				rs = ps.executeQuery();
				System.out.println("CONTRASENA_oBTENIDA_rs: "+ rs);
				if (rs.next()) {
					cargaUsuariodto = new UsuarioDTO();
					cargaUsuariodto.setPassword(rs.getString("CONTRASENA"));
				}
				
				logger.info("INICIANDO_DAO_DATA_SOURCE_logindto921cargaUsuariodto: " + cargaUsuariodto.toString());
				System.out.println("CONTRASENA_oBTENIDA: "+ cargaUsuariodto.getPassword());
				logger.info(
						"**************************************** Query UsuarioDAO.getContrasenaUsuario exitoso ****************************************");
			}catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
	            throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error con la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxx");

	        } finally {
	            try {
	                if (rs != null && !rs.isClosed()) {
	                    rs.close();
	                    logger.info("========================= ResultSet UsuarioDAO.getContrasenaUsuario cerrado ===========================");
	                }
	                if (ps != null  && !ps.isClosed()) {
	                    ps.close();
	                    logger.info("========================= PreparedStatement UsuarioDAO.getContrasenaUsuario cerrado ===========================");
	                }
	                if (con != null) {
	                	con.close();
	                }
	            } catch (SQLException sqlException) {
	            	logger.error(sqlException.getMessage());
	                throw new ConexionException("xxxxxxxxxxxxxxxxxxxxxxxxx Ocurrió un error al intentar cerrar la conexión a la base de datos. xxxxxxxxxxxxxxxxxxxxxxxxx");
	            }

	        } 
			
		return cargaUsuariodto;
	
	    }
 
	 
}
