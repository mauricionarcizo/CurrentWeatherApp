package com.previsaotempo.exceptions;

public class CidadesNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public CidadesNotFoundException(){
		super("Não foram encontrados dados para esta país!");
	}
}
