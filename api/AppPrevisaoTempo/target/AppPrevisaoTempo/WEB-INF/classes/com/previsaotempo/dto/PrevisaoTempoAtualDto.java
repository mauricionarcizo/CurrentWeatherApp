package com.previsaotempo.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "CurrentWeather")
public class PrevisaoTempoAtualDto {
	private String localizacao;
	private String horaAtual;
	private String vento;
	private String visibilidade;
	private String temperatura;
	private String dewPoint;
	private String humidadeRelativa;
	private String pressao;
	private String status;

	@XmlElement(name = "Location")
	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	@XmlElement(name = "Time")
	public String getHoraAtual() {
		return horaAtual;
	}

	public void setHoraAtual(String horaAtual) {
		this.horaAtual = horaAtual;
	}

	@XmlElement(name = "Wind")
	public String getVento() {
		return vento;
	}

	public void setVento(String vento) {
		this.vento = vento;
	}

	@XmlElement(name = "Visibility")
	public String getVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(String visibilidade) {
		this.visibilidade = visibilidade;
	}

	@XmlElement(name = "Temperature")
	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	@XmlElement(name = "DewPoint")
	public String getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(String dewPoint) {
		this.dewPoint = dewPoint;
	}

	@XmlElement(name = "RelativeHumidity")
	public String getHumidadeRelativa() {
		return humidadeRelativa;
	}

	public void setHumidadeRelativa(String humidadeRelativa) {
		this.humidadeRelativa = humidadeRelativa;
	}

	@XmlElement(name = "Pressure")
	public String getPressao() {
		return pressao;
	}

	public void setPressao(String pressao) {
		this.pressao = pressao;
	}

	@XmlElement(name = "Status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
