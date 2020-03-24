package com.udemy.cursomc.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.udemy.cursomc.domain.Categoria;
import com.udemy.cursomc.services.CategoriaService;

@RestController // INDICA QUE É UM CONTROLADOR REST
@RequestMapping(value = "/categorias") // ENDPOINT
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> find(){
		List<Categoria> obj = service.find();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET) // INFORMA QUAL MÉTODO DE REQUISIÇÃO
	// ResponseEntity encapsula as respostas necessárias para um serviço rest
	public ResponseEntity<Categoria> find(@PathVariable Integer id) { // ANOTAÇÃO @PathVariable indica que a variável recebida no parametro, deve ser a variável passada no path do endpoint
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) { // ANOTAÇÃO REQUESTBODY SERIALIZA AUTOMATICAMENTE UM JSON EM OBJETO JAVA
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri(); 
		/* PEGA A URI DA REQUISIÇÃO CORRENTE (ATUAL) E ACRESCENTA ID,
		 * CONSTRUINDO UMA URI CORRESPONDE À ONDE O NOVO RECURSO INSERIDO SERÁ DISPONIBILIZADO
		 * ESSA URI É DEVOLVIDA NO HEADER DO RESPONSE NO VALOR LOCATION
		 */
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}