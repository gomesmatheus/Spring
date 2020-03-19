package com.udemy.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.Cliente;
import com.udemy.cursomc.repositories.ClienteRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		return clienteRepository.findById(id)
			.orElseThrow(() -> new ObjectNotFoundException("Cliente com id: " + id + " não encontrado!"));
	}

	public List<Cliente> find() {
		return clienteRepository.findAll();
	}
}