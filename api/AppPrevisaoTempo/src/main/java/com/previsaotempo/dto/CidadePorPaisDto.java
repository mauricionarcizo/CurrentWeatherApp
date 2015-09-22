package com.previsaotempo.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NewDataSet")
public class CidadePorPaisDto {

	private List<CidadeDto> cidades;

	@XmlRootElement(name = "Table")
	public static class CidadeDto {
		private String pais;

		@XmlElement(name = "Country")
		public String getPais() {
			return pais;
		}

		public void setPais(String pais) {
			this.pais = pais;
		}

		@XmlElement(name = "City")
		public String getCidade() {
			return cidade;
		}

		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		private String cidade;
	}

	@XmlElement(name = "Table")
	public List<CidadeDto> getCidades() {
		return cidades;
	}

	public void setCidades(List<CidadeDto> cidades) {
		this.cidades = cidades;
	}

}
