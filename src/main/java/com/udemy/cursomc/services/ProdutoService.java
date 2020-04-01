package com.udemy.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.Categoria;
import com.udemy.cursomc.domain.Produto;
import com.udemy.cursomc.repositories.CategoriaRepository;
import com.udemy.cursomc.repositories.ProdutoRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		return repository.findById(id)
			.orElseThrow(() -> new ObjectNotFoundException("Produto com id: " + id + " não encontrado!"));
	}
	
	public Page<Produto> search(String nome, List<Integer> id ,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(id);
		return repository.search(nome, categorias, pageRequest);
	}
}
