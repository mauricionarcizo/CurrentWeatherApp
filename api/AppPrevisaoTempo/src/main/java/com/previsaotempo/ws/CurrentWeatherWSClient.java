package com.previsaotempo.ws;

import java.io.StringReader;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.net.webservicex.GlobalWeather;
import com.net.webservicex.GlobalWeatherSoap;
import com.previsaotempo.dto.CidadePorPaisDto;
import com.previsaotempo.dto.PrevisaoTempoAtualDto;
import com.previsaotempo.exceptions.CidadePaisNotFoundException;
import com.previsaotempo.exceptions.CidadesNotFoundException;

public class CurrentWeatherWSClient {

	public static PrevisaoTempoAtualDto obterCurrentWeather(String city, String country) throws Exception {
		GlobalWeather global = new GlobalWeather();
		GlobalWeatherSoap weatherSoap = global.getGlobalWeatherSoap();
		String response = weatherSoap.getWeather(city, country);

		if (!"Data Not Found".equalsIgnoreCase(response)) {
			JAXBContext jAXBContext = JAXBContext.newInstance(PrevisaoTempoAtualDto.class);
			Unmarshaller unmarshaller = jAXBContext.createUnmarshaller();
			return (PrevisaoTempoAtualDto) unmarshaller.unmarshal(new StringReader(response));
		}
		throw new CidadePaisNotFoundException();

	}

	public static CidadePorPaisDto obterCidadesPorPais(String country) throws Exception {
		GlobalWeather global = new GlobalWeather();
		GlobalWeatherSoap weatherSoap = global.getGlobalWeatherSoap();
		String response = weatherSoap.getCitiesByCountry(country);
		JAXBContext jAXBContext = JAXBContext.newInstance(CidadePorPaisDto.class);
		Unmarshaller unmarshaller = jAXBContext.createUnmarshaller();
		CidadePorPaisDto cidades = (CidadePorPaisDto) unmarshaller.unmarshal(new StringReader(response));

		if (!Optional.ofNullable(cidades.getCidades()).isPresent()) {
			throw new CidadesNotFoundException();
		}
		return cidades;

	}

}
