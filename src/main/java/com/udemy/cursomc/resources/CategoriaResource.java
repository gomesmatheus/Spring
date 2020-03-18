package com.udemy.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // INDICA QUE É UM CONTROLADOR REST
@RequestMapping(value = "/categorias") // ENDPOINT
public class CategoriaResource {

	@RequestMapping(method = RequestMethod.GET) // INFORMA QUAL MÉTODO DE REQUISIÇÃO
	public String listar() {
		return "REST está funcionando";
	}
}
