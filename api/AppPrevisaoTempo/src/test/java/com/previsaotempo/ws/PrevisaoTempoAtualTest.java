package com.previsaotempo.ws;

import org.junit.Assert;
import org.junit.Test;

import com.previsaotempo.dto.PrevisaoTempoAtualDto;
import com.previsaotempo.exceptions.CidadePaisNotFoundException;
import com.previsaotempo.exceptions.CidadesNotFoundException;

public class PrevisaoTempoAtualTest {
	
	@Test
	public void deveObterPrevisaoTempoByCity() throws Exception{
		PrevisaoTempoAtualDto currentWeather = CurrentWeatherWSClient.obterCurrentWeather("Florianopolis", "Brazil");
		Assert.assertNotNull(currentWeather);
		Assert.assertEquals(currentWeather.getStatus(), "Success");
	}
	
	@Test(expected=CidadePaisNotFoundException.class)
	public void deveLancarExceptionCidadePaisNaoEncontrada() throws Exception{
		CurrentWeatherWSClient.obterCurrentWeather("Florianópolis", "Espanha");
	}
	
	@Test
	public void deveObterCidadesPorPais() throws Exception{
		
		Assert.assertNotNull(CurrentWeatherWSClient.obterCidadesPorPais("Brazil"));
		Assert.assertTrue(!CurrentWeatherWSClient.obterCidadesPorPais("Brazil").getCidades().isEmpty());
	}
	
	@Test(expected=CidadesNotFoundException.class)
	public void deveLancarExceptionCidadesNotFound() throws Exception{
		
		CurrentWeatherWSClient.obterCidadesPorPais("Brazilandia");
	}

}
