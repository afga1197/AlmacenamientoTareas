package com.AlmacenamientoTareas.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import com.AlmacenamientoTareas.model.Tarea;
import org.springframework.http.ResponseEntity;
import com.AlmacenamientoTareas.Service.TareasService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/crearTarea")
	public ResponseEntity<?> guardarTarea(@RequestBody Tarea tarea){
		if(tarea.getNombre() != null && tarea.getPrioridad()!=0) {
			if(tareasService.guardarTarea(tarea)) {
				return new ResponseEntity(true, HttpStatus.OK);
			}else {
				return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
			}	
		}else {
			String respuesta = "Datos incompletos. Por favor ingrese todos los datos.";
			return new ResponseEntity(respuesta, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/actualizarTarea")
	public ResponseEntity<?> actualizarTarea(@RequestBody Tarea tarea){
		if(tarea.getId()!=0 && (tarea.getNombre() != null || tarea.getPrioridad()!=0)) {
			if(tareasService.actualizarTarea(tarea)) {
				return new ResponseEntity(true, HttpStatus.OK);
			}else {
				return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
			}	
		}else {
			String respuesta = "Datos incompletos. Por favor ingrese todos los datos.";
			return new ResponseEntity(respuesta, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/actualizarTareaEstado/{id}")
	public ResponseEntity<?> actualizarTareaEstado(@PathVariable("id") int id){
		if(id!=0) {
			if(tareasService.actualizarTareaEstado(id)) {
				return new ResponseEntity(true, HttpStatus.OK);
			}else {
				return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
			}
		} else {
			String respuesta = "Datos incompletos. Por favor ingrese todos los datos.";
			return new ResponseEntity(respuesta, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/eliminarTarea/{id}")
	public ResponseEntity<?> borrarTarea(@PathVariable("id") int id){
		if (tareasService.borrarTarea(id)) {
			return new ResponseEntity(true, HttpStatus.OK);
		}else {
			return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
		}
	}
	
}