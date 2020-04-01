package com.udemy.cursomc.services;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto, LocalDateTime instante) {
		pagamentoComBoleto.setDataVencimento(LocalDate.now().plusDays(7));
	}
}