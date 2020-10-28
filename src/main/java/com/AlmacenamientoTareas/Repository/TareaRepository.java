package com.AlmacenamientoTareas.Repository;

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

}