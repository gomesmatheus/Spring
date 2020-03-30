package com.udemy.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{ // É passado qual tipo de dados ele acessa, e qual tipo do atributo identifacodr do objeto
	
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
}