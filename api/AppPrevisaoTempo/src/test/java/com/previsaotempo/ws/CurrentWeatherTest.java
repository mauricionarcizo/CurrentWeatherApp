package com.previsaotempo.ws;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;

import com.net.webservicex.GlobalWeather;
import com.net.webservicex.GlobalWeatherSoap;

public class CurrentWeatherTest {

	@Test
	public void test() throws JAXBException {
		GlobalWeather global = new GlobalWeather();
		GlobalWeatherSoap weatherSoap = global.getGlobalWeatherSoap();
		String response = weatherSoap.getWeather("Porto alegre", "Brazil");

		JAXBContext jAXBContext = JAXBContext.newInstance(CurrentWeatherDto.class);
		Unmarshaller unmarshaller = jAXBContext.createUnmarshaller();
		CurrentWeatherDto currentWeather = (CurrentWeatherDto) unmarshaller.unmarshal(new StringReader(response));

		Assert.assertNotNull(currentWeather);
		Assert.assertEquals(currentWeather.getStatus(), "Success");
	}

}
