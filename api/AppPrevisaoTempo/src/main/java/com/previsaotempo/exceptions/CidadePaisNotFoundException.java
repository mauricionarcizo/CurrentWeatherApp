package com.previsaotempo.exceptions;

public class CidadePaisNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CidadePaisNotFoundException(){
		super("Não foram encontrados dados para esta cidade e país!");
	}
}
