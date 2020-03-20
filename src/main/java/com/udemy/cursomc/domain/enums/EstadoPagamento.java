package com.udemy.cursomc.domain.enums;

import java.util.EnumSet;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer id;
	private String descricao;
	
	private EstadoPagamento(Integer cod, String descricao) {
		this.id = cod;
		this.descricao = descricao;
	}
	
	public Integer getCod() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		return EnumSet.allOf(EstadoPagamento.class)
			.stream()
			.filter(estadoPagamento -> estadoPagamento.getCod() == cod)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Id inválido: " + cod));
	}
}
