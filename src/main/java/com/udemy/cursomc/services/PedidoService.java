package com.udemy.cursomc.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.ItemPedido;
import com.udemy.cursomc.domain.PagamentoComBoleto;
import com.udemy.cursomc.domain.PagamentoComCartao;
import com.udemy.cursomc.domain.Pedido;
import com.udemy.cursomc.domain.enums.EstadoPagamento;
import com.udemy.cursomc.repositories.ItemPedidoRepository;
import com.udemy.cursomc.repositories.PagamentoRepository;
import com.udemy.cursomc.repositories.PedidoRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private ProdutoService produtoService;
	
	
	public Pedido find(Integer id) {
		return pedidoRepository.findById(id)
			.orElseThrow(() -> new ObjectNotFoundException("Pedido com id: " + id + " não encontrado!"));
	}

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(LocalDateTime.now());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagamentoComBoleto, obj.getInstante());
		}
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0D);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;	
	}
}