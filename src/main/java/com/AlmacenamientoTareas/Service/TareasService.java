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

}