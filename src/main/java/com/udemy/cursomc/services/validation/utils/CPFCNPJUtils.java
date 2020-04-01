package com.udemy.cursomc.services.validation.utils;

import java.util.Random;

/**
 * Classe que cont�m os m�todos �teis relacionados ao CPF e CNPJ
 * (Validação e geração)
 * 
 * @author Matheus Gomes (github.com/gomesmatheus)
 */
public class CPFCNPJUtils {
	
	/**
	 * @param comPontuacao booleano que diz se o CPF retornado conter� as pontua��o no formato xxx.xxx.xxx-xx
	 * @return um CPF v�lido gerado aleat�riamente
	 */
	public static String gerarCPFAleatorio(boolean comPontuacao) {
		String cpf = "";
		for(int i = 0; i < 9; i++) {
			cpf+= (new Random().nextInt(10));
		}
		cpf+= retornaPrimeiroDigitoValidadorCPF(cpf);
		cpf+= retornaSegundoDigitoValidadorCPF(cpf);
		
		if (comPontuacao)
			return new StringBuffer(cpf).insert(3, ".").insert(7, ".").insert(11, "-").toString();
		else
			return cpf;
	}
	
	/**
	 * @param comPontuacao booleano que diz se o CNPJ retornado conter� as pontua��o no formato xx.xxx.xxx/xxxx-xx
	 * @return um CNPJ v�lido gerado aleat�riamente
	 */
	public static String gerarCNPJAleatorio(boolean comPontuacao) {
		String cnpj = "";
		for(int i = 0; i < 12; i++) {
			cnpj+= (new Random().nextInt(10));
		}
		cnpj+= retornaPrimeiroDigitoValidadorCNPJ(cnpj);
		cnpj+= retornaSegundoDigitoValidadorCNPJ(cnpj);
		
		if(comPontuacao)
			return new StringBuffer(cnpj).insert(2, ".").insert(6, ".").insert(10, "/").insert(15, "-").toString();
		else
			return cnpj;
	}
	
	/**
	 * @param cpf
	 * @return boolean que indica se o CPF � v�lido
	 */
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
	
	/**
	 * @param cnpj
	 * @return boolean que indica se o CNPJ � v�lido
	 */
	public static boolean isCNPJValido(String cnpj) {
		if (cnpj == null || cnpj.length() == 0 || cnpj.equals("")) {
			return false;
		}
		
		cnpj = retornaSomenteDigitos(cnpj);
		if(cnpj.length() != 14)
			return false;

		return isPrimeiroDigitoCNPJValido(cnpj) && isSegundoDigitoCNPJValido(cnpj);
	}
	
	/**
	 * @param cpf
	 * @return boolean que indica se o primeiro d�gito do CPF � v�lido
	 */
	private static boolean isPrimeiroDigitoCPFValido(String cpf) {
		return Character.getNumericValue(cpf.charAt(9)) == retornaPrimeiroDigitoValidadorCPF(cpf);
	}

	/**
	 * @param cpf
	 * @return boolean que indica se o segundo d�gito do CPF � v�lido
	 */
	private static boolean isSegundoDigitoCPFValido(String cpf) {
		return Character.getNumericValue(cpf.charAt(10)) == retornaSegundoDigitoValidadorCPF(cpf);
	}
	
	/**
	 * @param cpf, podendo ser apenas os primeiros 9 d�gitos do CPF, sem os validadores
	 * @return primeiro d�gito validador do cpf baseasdo nos 9 d�gitos
	 */
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
	
	/**
	 * @param cpf, podendo ser apenas os primeiros 10 d�gitos do CPF, apenas com o primeiro d�gito validador, sem o segundo
	 * @return segundo d�gito validador do cpf baseasdo nos 10 d�gitos
	 */
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
	
	/**
	 * @param cnpj
	 * @return boolean que indica se o primeiro d�gito do CNPJ � v�lido
	 */
	private static boolean isPrimeiroDigitoCNPJValido(String cnpj) {
		return Character.getNumericValue(cnpj.charAt(12)) == retornaPrimeiroDigitoValidadorCNPJ(cnpj);
	}

	/**
	 * @param cnpj
	 * @return boolean que indica se o segundo d�gito do CNPJ � v�lido
	 */
	private static boolean isSegundoDigitoCNPJValido(String cnpj) {
		return Character.getNumericValue(cnpj.charAt(13)) == retornaSegundoDigitoValidadorCNPJ(cnpj);
	}
	
	/**
	 * @param cnpj, podendo ser apenas os primeiros 12 d�gitos do CNPJ, sem os validadores
	 * @return primeiro d�gito validador do cpf baseasdo nos 12 d�gitos
	 */
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
	
	/**
	 * @param cnpj, podendo ser apenas os primeiros 12 d�gitos do CNPJ, mais o primeiro digito validador, sem o ultimo
	 * @return primeiro d�gito validador do cpf baseasdo nos 13 d�gitos
	 */
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
	
	/**
	 * @param str
	 * @return a mesma string, por�m apenas com os d�gitos
	 */
	private static String retornaSomenteDigitos(String str) {
		return str.replaceAll("[^\\d]", "");
	}
}