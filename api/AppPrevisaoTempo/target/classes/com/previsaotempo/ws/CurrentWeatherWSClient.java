package com.previsaotempo.ws;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.net.webservicex.GlobalWeather;
import com.net.webservicex.GlobalWeatherSoap;

public class CurrentWeatherWSClient {
	public static CurrentWeatherDto obterCurrentWeather(String city, String country) throws Exception {
		GlobalWeather global = new GlobalWeather();
		GlobalWeatherSoap weatherSoap = global.getGlobalWeatherSoap();
		String response = weatherSoap.getWeather(city, country);

		if (!"Data Not Found".equalsIgnoreCase(response)) {
			JAXBContext jAXBContext = JAXBContext.newInstance(CurrentWeatherDto.class);
			Unmarshaller unmarshaller = jAXBContext.createUnmarshaller();
			return (CurrentWeatherDto) unmarshaller.unmarshal(new StringReader(response));
		}
		return null;

	}
}
