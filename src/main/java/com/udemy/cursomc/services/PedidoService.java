package com.udemy.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.Pedido;
import com.udemy.cursomc.repositories.PedidoRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) {
		return pedidoRepository.findById(id)
			.orElseThrow(() -> new ObjectNotFoundException("Pedido com id: " + id + " não encontrado!"));
	}

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
}