package uc.us_security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uc.us_security.entity.Rol;
import uc.us_security.serviceimpl.RolServiceimpl;



@RestController
@RequestMapping("/api/rol")
public class RolController {
@Autowired
private RolServiceimpl rolService;
@GetMapping("/all")
public ResponseEntity<List<Rol>> getRol(){
	try {
		List<Rol> list = new ArrayList<>();
		list = rolService.readAll();
		if(list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	} catch (Exception e) {
		// TODO: handle exception
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@PostMapping("/create")
public ResponseEntity<Rol> save(@RequestBody Rol rol){
	try {
		Rol lb = rolService.create(new Rol(rol.getId(),rol.getNombre(),rol.getUsuarios()));
		return new ResponseEntity<>(lb, HttpStatus.CREATED);
	} catch (Exception e) {
		// TODO: handle exception
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@GetMapping("/{id}")
public ResponseEntity<Rol> getRol(@PathVariable("id") int id){
	Rol rol = rolService.read(id);
		if(rol.getId()>0) {
			return new ResponseEntity<>(rol, HttpStatus.OK);
		}else {
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
}
@DeleteMapping("/delete/{id}")
public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
	try {
		rolService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@PutMapping("/update/{id}")
public ResponseEntity<Rol> update(@RequestBody Rol l, @PathVariable("id") int id) {
    try {
        Rol li = rolService.read(id);
        if (li.getId() > 0) {
            li.setNombre(l.getNombre());
            li.setUsuarios(l.getUsuarios());
            return new ResponseEntity<>(rolService.create(li), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}
