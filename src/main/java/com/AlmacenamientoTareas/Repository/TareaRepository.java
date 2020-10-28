package com.AlmacenamientoTareas.Repository;

import java.sql.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import com.AlmacenamientoTareas.model.Tarea;
import com.AlmacenamientoTareas.model.Conexion;
import org.springframework.stereotype.Repository;

@Repository
public class TareaRepository {

	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public List<Tarea> obtenerTareas() {
		Connection conectar = null;
		ArrayList<Tarea> tareas = new ArrayList<>();
		try {
			conectar = Conexion.getInstance().getConnection();
			if (conectar != null) {
				String query = "SELECT * FROM tarea";
				preparedStatement = conectar.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Tarea tarea = new Tarea();
					tarea.setId(resultSet.getInt("Id"));
					tarea.setNombre(resultSet.getString("Nombre"));
					tarea.setPrioridad(resultSet.getInt("Prioridad"));
					tarea.setCompleta(resultSet.getBoolean("Completada"));
					tarea.setFechaCreacion(resultSet.getDate("Fecha"));
					tareas.add(tarea);
				}
			}
			return tareas;
		} catch (Exception e) {
			String log = "Error en la ejecucion del query, con excepcion en " + e.getMessage();
			System.out.println(log);
			return null;
		} finally {
			try {
				Conexion.getInstance().closeConnection(conectar);
			} catch (Exception e) {
				String log = "Error al cerrar la conexion, con excepcion en " + e.getMessage();
				System.out.println(log);
			}
		}
	}

	public boolean guardarTarea(Tarea tarea) {
		boolean guardo = false;
		Connection conectar = null;
		try {
			conectar = Conexion.getInstance().getConnection();
			if (conectar != null) {
				String query = "INSERT INTO tarea (Nombre, Prioridad, Completada, Fecha) VALUES(?,?,?,?)";
				preparedStatement = conectar.prepareStatement(query);
				preparedStatement.setString(1, tarea.getNombre());
				preparedStatement.setInt(2, tarea.getPrioridad());
				preparedStatement.setBoolean(3, tarea.isCompleta());
				preparedStatement.setDate(4, obtenerFecha());
				preparedStatement.executeUpdate();
				guardo = true;
			}
			return guardo;
		} catch (Exception e) {
			String log = "Error en la ejecucion del query, con excepcion en " + e.getMessage();
			System.out.println(log);
			return false;
		} finally {
			try {
				Conexion.getInstance().closeConnection(conectar);
			} catch (Exception e) {
				String log = "Error al cerrar la conexion, con excepcion en " + e.getMessage();
				System.out.println(log);
			}
		}
	}

	public boolean actualiizarTarea(Tarea tarea) {
		boolean actualizo = false;
		Connection conectar = null;
		try {
			conectar = Conexion.getInstance().getConnection();
			if (conectar != null) {
				String query = "";
				if(tarea.getNombre() == null && tarea.getPrioridad()!=0) {
					query = "UPDATE tarea SET Prioridad=? WHERE Id=?";
					preparedStatement = conectar.prepareStatement(query);
					preparedStatement.setInt(1, tarea.getPrioridad());
					preparedStatement.setInt(2, tarea.getId());
				}else if(tarea.getNombre() != null && tarea.getPrioridad()==0){
					query = "UPDATE tarea SET Nombre=? WHERE Id=?";
					preparedStatement = conectar.prepareStatement(query);
					preparedStatement.setString(1, tarea.getNombre());
					preparedStatement.setInt(2, tarea.getId());
				}else {
					query = "UPDATE tarea SET Nombre=?, Prioridad=? WHERE Id=?";
					preparedStatement = conectar.prepareStatement(query);
					preparedStatement.setString(1, tarea.getNombre());
					preparedStatement.setInt(2, tarea.getPrioridad());
					preparedStatement.setInt(3, tarea.getId());
				}
				preparedStatement.executeUpdate();
				actualizo = true;
			}
			return actualizo;
		} catch (Exception e) {
			String log = "Error en la ejecucion del query, con excepcion en " + e.getMessage();
			System.out.println(log);
			return false;
		} finally {
			try {
				Conexion.getInstance().closeConnection(conectar);
			} catch (Exception e) {
				String log = "Error al cerrar la conexion, con excepcion en " + e.getMessage();
				System.out.println(log);
			}
		}
	}

	public boolean actualizarTareaEstado(int id) {
		boolean actualizo = false;
		Connection conectar = null;
		try {
			conectar = Conexion.getInstance().getConnection();
			if (conectar != null) {
				String query = "UPDATE tarea SET Completada=? WHERE Id=?";
				preparedStatement = conectar.prepareStatement(query);
				preparedStatement.setBoolean(1, true);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
				actualizo = true;
			}
			return actualizo;
		} catch (Exception e) {
			String log = "Error en la ejecucion del query, con excepcion en " + e.getMessage();
			System.out.println(log);
			return false;
		} finally {
			try {
				Conexion.getInstance().closeConnection(conectar);
			} catch (Exception e) {
				String log = "Error al cerrar la conexion, con excepcion en " + e.getMessage();
				System.out.println(log);
			}
		}
	}

	public boolean borrarTarea(int id) {
		boolean borro = false;
		Connection conectar = null;
		try {
			conectar = Conexion.getInstance().getConnection();
			if (conectar != null) {
				String query = "DELETE FROM tarea WHERE Id=?";
				preparedStatement = conectar.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();
				borro = true;
			}
			return borro;
		} catch (Exception e) {
			String log = "Error en la ejecucion del query, con excepcion en " + e.getMessage();
			System.out.println(log);
			return false;
		} finally {
			try {
				Conexion.getInstance().closeConnection(conectar);
			} catch (Exception e) {
				String log = "Error al cerrar la conexion, con excepcion en " + e.getMessage();
				System.out.println(log);
			}
		}
	}

	private Date obtenerFecha() {
		java.util.Date d = new java.util.Date();
		java.sql.Date date2 = new java.sql.Date(d.getTime());
		return date2;
	}

	public List<Tarea> obtenerTarea(int id) {
		Connection conectar = null;
		ArrayList<Tarea> tareas = new ArrayList<>();
		try {
			conectar = Conexion.getInstance().getConnection();
			if (conectar != null) {
				String query = "SELECT * FROM tarea WHERE Id=?";
				preparedStatement = conectar.prepareStatement(query);
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Tarea tarea = new Tarea();
					tarea.setId(resultSet.getInt("Id"));
					tarea.setNombre(resultSet.getString("Nombre"));
					tarea.setPrioridad(resultSet.getInt("Prioridad"));
					tarea.setCompleta(resultSet.getBoolean("Completada"));
					tarea.setFechaCreacion(resultSet.getDate("Fecha"));
					tareas.add(tarea);
				}
			}
			return tareas;
		} catch (Exception e) {
			String log = "Error en la ejecucion del query, con excepcion en " + e.getMessage();
			System.out.println(log);
			return null;
		} finally {
			try {
				Conexion.getInstance().closeConnection(conectar);
			} catch (Exception e) {
				String log = "Error al cerrar la conexion, con excepcion en " + e.getMessage();
				System.out.println(log);
			}
		}
	}

}