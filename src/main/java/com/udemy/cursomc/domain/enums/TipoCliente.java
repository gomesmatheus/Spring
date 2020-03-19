package com.udemy.cursomc.domain.enums;

import java.util.EnumSet;

public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private Integer cod;
	private String descricao;
	
	private TipoCliente(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public Integer getCod() {
		return this.cod;
	}
	public String getDescricao() {
		return this.descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		return EnumSet.allOf(TipoCliente.class)
			.stream()
			.filter(tipo -> tipo.getCod() == cod)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Id inválido: " + cod));
	}
}
