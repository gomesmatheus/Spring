package com.udemy.cursomc.services.validation.utils;

import java.util.Random;

/**
 * Classe que cont�m os m�todos �teis relacionados ao CPF e CNPJ
 * (Validacao e geracao)
 * 
 * @author gomesmatheus
 */
public class CPFCNPJUtils {

	/**
	 * @return
	 */
	public static String gerarCPFAleatorio(boolean comPontuacao) {
		String cpf = "";
		for(int i = 0; i < 9; i++) {
			cpf+= (new Random().nextInt(10));
		}
		System.out.println("CPF GERADO: " + cpf);
		cpf+= retornaPrimeiroDigitoValidadorCPF(cpf);
		cpf+= retornaSegundoDigitoValidadorCPF(cpf);
		
		return cpf;
	}
	
	public static String gerarCNPJAleatorio() {
		String cnpj = "";
		for(int i = 0; i < 12; i++) {
			cnpj+= (new Random().nextInt(10));
		}
		System.out.println("CNPJ GERADO: " + cnpj);
		cnpj+= retornaPrimeiroDigitoValidadorCNPJ(cnpj);
		return cnpj+= retornaSegundoDigitoValidadorCNPJ(cnpj);
	}
	
	public static boolean isCPFValido(String cpf) {
		if (cpf == null || cpf.length() == 0 || cpf.equals("")) {
			return false;
		}
		
		cpf = retornaSomenteDigitos(cpf);
		if(cpf.length() != 11)
			return false;
		
		// Testa os CPFS que s�o v�lidos, por�m devem ser barrados
		if (!(cpf.length() == 11) || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333")
				|| cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666")
				|| cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999")) {
			return false;
		}

		return isPrimeiroDigitoCPFValido(cpf) && isSegundoDigitoCPFValido(cpf);
	}

	private static boolean isPrimeiroDigitoCPFValido(String cpf) {
		return Character.getNumericValue(cpf.charAt(9)) == retornaPrimeiroDigitoValidadorCPF(cpf);
	}

	private static boolean isSegundoDigitoCPFValido(String cpf) {
		return Character.getNumericValue(cpf.charAt(10)) == retornaSegundoDigitoValidadorCPF(cpf);
	}
	
	private static int retornaPrimeiroDigitoValidadorCPF(String cpf) {
		int soma = 0;
		for (int i = 0, multiplicador = 10; i < 9; i++, multiplicador--) {
			int numero = Character.getNumericValue(cpf.charAt(i));
			soma += numero * multiplicador;
		}

		int restoDadivisao = (soma * 10) % 11;
		int digitoValidador = restoDadivisao == 10 ? 0 : restoDadivisao;
		return digitoValidador;
	}
	
	private static int retornaSegundoDigitoValidadorCPF(String cpf) {
		int soma = 0;
		for (int i = 0, multiplicador = 11; i < 10; i++, multiplicador--) {
			int numero = Character.getNumericValue(cpf.charAt(i));
			soma += numero * multiplicador;
		}

		int restoDadivisao = (soma * 10) % 11;
		int digitoValidador = restoDadivisao == 10 ? 0 : restoDadivisao;
		return digitoValidador;
	}

	public static boolean isCNPJValido(String cnpj) {
		if (cnpj == null || cnpj.length() == 0 || cnpj.equals("")) {
			return false;
		}
		
		cnpj = retornaSomenteDigitos(cnpj);
		if(cnpj.length() != 14)
			return false;

		return isPrimeiroDigitoCNPJValido(cnpj) && isSegundoDigitoCNPJValido(cnpj);
	}
	
	private static boolean isPrimeiroDigitoCNPJValido(String cnpj) {
		return Character.getNumericValue(cnpj.charAt(12)) == retornaPrimeiroDigitoValidadorCNPJ(cnpj);
	}

	private static boolean isSegundoDigitoCNPJValido(String cnpj) {
		return Character.getNumericValue(cnpj.charAt(13)) == retornaSegundoDigitoValidadorCNPJ(cnpj);
	}
	
	private static int retornaPrimeiroDigitoValidadorCNPJ(String cnpj) {
		int pesosMultiplicacao[] = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int soma = 0;
		for (int i = 0; i < 12; i++) {
			int numero = Character.getNumericValue(cnpj.charAt(i));
			soma += numero * pesosMultiplicacao[i];
		}

		int restoDadivisao = soma % 11;
		int digitoValidador = restoDadivisao < 2 ? 0 : (11 - restoDadivisao);
		return digitoValidador;
	}
	
	private static int retornaSegundoDigitoValidadorCNPJ(String cnpj) {
		int pesosMultiplicacao[] = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int soma = 0;
		for (int i = 0; i < 13; i++) {
			int numero = Character.getNumericValue(cnpj.charAt(i));
			soma += numero * pesosMultiplicacao[i];
		}

		int restoDadivisao = soma % 11;
		int digitoValidador = restoDadivisao < 2 ? 0 : (11 - restoDadivisao);
		return digitoValidador;
	}
	
	private static String retornaSomenteDigitos(String str) {
		return str.replaceAll("[^\\d]", "");
	}
}