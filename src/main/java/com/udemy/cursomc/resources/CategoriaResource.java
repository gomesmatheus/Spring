package com.udemy.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.cursomc.domain.Categoria;
import com.udemy.cursomc.services.CategoriaService;

@RestController // INDICA QUE É UM CONTROLADOR REST
@RequestMapping(value = "/categorias") // ENDPOINT
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> find(){
		List<Categoria> obj = service.buscar();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET) // INFORMA QUAL MÉTODO DE REQUISIÇÃO
	// ResponseEntity encapsula as respostas necessárias para um serviço rest
	public ResponseEntity<?> find(@PathVariable Integer id) { // ANOTAÇÃO @PathVariable indica que a variável recebida no parametro, deve ser a variável passada no path do endpoint
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}