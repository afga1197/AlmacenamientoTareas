package com.AlmacenamientoTareas.Service;

import java.util.List;
import com.AlmacenamientoTareas.model.Tarea;
import org.springframework.stereotype.Service;
import com.AlmacenamientoTareas.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TareasService {
	
	@Autowired
	private TareaRepository tareaRepository;
	
	public List<Tarea> obtenerTareas() {
		return tareaRepository.obtenerTareas();
	}

	public boolean guardarTarea(Tarea tarea) {
		return tareaRepository.guardarTarea(tarea);
	}

	public boolean actualizarTarea(Tarea tarea) {
		return tareaRepository.actualiizarTarea(tarea);
	}

	public boolean actualizarTareaEstado(int id) {
		return tareaRepository.actualizarTareaEstado(id);
	}

	public boolean borrarTarea(int id) {
		return tareaRepository.borrarTarea(id);
	}

	public List<Tarea> obtenerTarea(int id) {
		return tareaRepository.obtenerTarea(id);
	}
	
}