package com.udemy.cursomc.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.cursomc.domain.Categoria;
import com.udemy.cursomc.domain.Cidade;
import com.udemy.cursomc.domain.Cliente;
import com.udemy.cursomc.domain.Endereco;
import com.udemy.cursomc.domain.Estado;
import com.udemy.cursomc.domain.ItemPedido;
import com.udemy.cursomc.domain.Pagamento;
import com.udemy.cursomc.domain.PagamentoComBoleto;
import com.udemy.cursomc.domain.PagamentoComCartao;
import com.udemy.cursomc.domain.Pedido;
import com.udemy.cursomc.domain.Produto;
import com.udemy.cursomc.domain.enums.EstadoPagamento;
import com.udemy.cursomc.domain.enums.TipoCliente;
import com.udemy.cursomc.repositories.CategoriaRepository;
import com.udemy.cursomc.repositories.CidadeRepository;
import com.udemy.cursomc.repositories.ClienteRepository;
import com.udemy.cursomc.repositories.EnderecoRepository;
import com.udemy.cursomc.repositories.EstadoRepository;
import com.udemy.cursomc.repositories.ItemPedidoRepository;
import com.udemy.cursomc.repositories.PagamentoRepository;
import com.udemy.cursomc.repositories.PedidoRepository;
import com.udemy.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProdutoRepository produtoRepository; 
	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	PagamentoRepository pagamentoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateTestDatabase(){
		Categoria cat1 = new Categoria(null, "Informática"); 
		Categoria cat2 = new Categoria(null, "Escritório"); 
		Categoria cat3 = new Categoria(null, "Cama mesa e banho"); 
		Categoria cat4 = new Categoria(null, "Eletrônicos"); 
		Categoria cat5 = new Categoria(null, "Jardinagem"); 
		Categoria cat6 = new Categoria(null, "Decoração"); 
		Categoria cat7 = new Categoria(null, "Perfumaria"); 

		Produto p1  = new Produto(null, "Computador", 2000D); 
		Produto p2  = new Produto(null, "Impressora", 800D); 
		Produto p3  = new Produto(null, "Mouse", 80D); 
		Produto p4  = new Produto(null, "Mesa de Escritório", 300D); 
		Produto p5  = new Produto(null, "Toalha", 50D); 
		Produto p6  = new Produto(null, "Colcha", 200D); 
		Produto p7  = new Produto(null, "TV true color", 1200D); 
		Produto p8  = new Produto(null, "Roçadeira", 800D); 
		Produto p9  = new Produto(null, "Abajour", 100D); 
		Produto p10 = new Produto(null, "Pendente", 80D); 
		Produto p11 = new Produto(null, "Shampoo", 90D); 
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		
		String t1 = "27363323";
		String t2 = "93838393";
		cli1.getTelefones().addAll(Arrays.asList(t1, t2));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		Pedido ped1 = new Pedido(null, LocalDateTime.of(2017, 9, 30, 10, 32), cli1, e1);
		Pedido ped2 = new Pedido(null, LocalDateTime.of(2017, 10, 10, 19, 35), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, LocalDate.of(2017, 10, 20), null);
		
		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0D, 1, 2000D);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0D, 2, 80D);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100D, 1, 800D);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
	
}
