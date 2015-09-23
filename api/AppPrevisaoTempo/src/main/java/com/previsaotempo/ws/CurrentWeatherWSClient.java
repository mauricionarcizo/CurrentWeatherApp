package com.previsaotempo.ws;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.net.webservicex.GlobalWeather;
import com.net.webservicex.GlobalWeatherSoap;
import com.previsaotempo.CidadesPrevisaoTempo;
import com.previsaotempo.dao.CidadesPrevisaoTempoDao;

import com.previsaotempo.dto.CidadePorPaisDto;
import com.previsaotempo.dto.CidadePorPaisDto.CidadeDto;
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

		CidadesPrevisaoTempoDao dao = new CidadesPrevisaoTempoDao();
		List<CidadesPrevisaoTempo> list = dao.findByCountry(country);

		CidadePorPaisDto cidades = null;
		if (!list.isEmpty()) {
			cidades = new CidadePorPaisDto();
			cidades.setCidades(new ArrayList<CidadeDto>(list.size()));
			for (CidadesPrevisaoTempo cidade : list) {
				CidadeDto city = new CidadeDto();
				city.setCidade(cidade.getCidade());
				city.setPais(cidade.getPais());
				cidades.getCidades().add(city);

			}
			return cidades;
		}

		cidades = buscarCidadesWS(country, dao);
		return cidades;

	}

	private static CidadePorPaisDto buscarCidadesWS(String country, CidadesPrevisaoTempoDao dao) throws JAXBException, CidadesNotFoundException {
		GlobalWeather global = new GlobalWeather();
		GlobalWeatherSoap weatherSoap = global.getGlobalWeatherSoap();
		String response = weatherSoap.getCitiesByCountry(country);
		JAXBContext jAXBContext = JAXBContext.newInstance(CidadePorPaisDto.class);
		Unmarshaller unmarshaller = jAXBContext.createUnmarshaller();
		CidadePorPaisDto cidades = (CidadePorPaisDto) unmarshaller.unmarshal(new StringReader(response));

		if (!Optional.ofNullable(cidades.getCidades()).isPresent()) {
			throw new CidadesNotFoundException();
		}

		gravarCidades(dao, cidades);
		return cidades;
	}

	private static void gravarCidades(CidadesPrevisaoTempoDao dao, CidadePorPaisDto cidades) {

		for (CidadeDto cidade : cidades.getCidades()) {
			CidadesPrevisaoTempo city = new CidadesPrevisaoTempo();
			city.setCidade(cidade.getCidade());
			city.setPais(cidade.getPais());
			dao.persist(city);
		}
	}

}
