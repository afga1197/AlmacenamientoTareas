package com.AlmacenamientoTareas.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import com.AlmacenamientoTareas.model.Tarea;
import org.springframework.http.ResponseEntity;
import com.AlmacenamientoTareas.Service.TareasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/tareas")
public class TareasController {
	
	@Autowired
	private TareasService tareasService;
	
	@GetMapping("/obtenerTareas")
	public ResponseEntity<?> obtener(){
		List<Tarea> tareas = tareasService.obtenerTareas();
		return new ResponseEntity(tareas, HttpStatus.OK);
	}
		
}