package com.udemy.cursomc.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.cursomc.domain.Categoria;

@RestController // INDICA QUE É UM CONTROLADOR REST
@RequestMapping(value = "/categorias") // ENDPOINT
public class CategoriaResource {

	@RequestMapping(method = RequestMethod.GET) // INFORMA QUAL MÉTODO DE REQUISIÇÃO
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		List<Categoria> lista = Arrays.asList(cat1, cat2);
		
		return lista;
	}
}
