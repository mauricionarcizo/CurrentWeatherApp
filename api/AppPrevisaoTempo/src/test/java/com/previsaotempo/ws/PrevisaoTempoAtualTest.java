package com.previsaotempo.ws;

import org.junit.Assert;
import org.junit.Test;

import com.previsaotempo.dto.CidadePorPaisDto;
import com.previsaotempo.dto.PrevisaoTempoAtualDto;
import com.previsaotempo.exceptions.CidadePaisNotFoundException;
import com.previsaotempo.exceptions.CidadesNotFoundException;

public class PrevisaoTempoAtualTest {

	@Test
	public void deveObterPrevisaoTempoByCity() throws Exception {
		PrevisaoTempoAtualDto currentWeather = CurrentWeatherWSClient.obterCurrentWeather("Florianopolis Aeroporto", "Brazil");
		Assert.assertNotNull(currentWeather);
		Assert.assertEquals(currentWeather.getStatus(), "Success");
	}

	@Test(expected = CidadePaisNotFoundException.class)
	public void deveLancarExceptionCidadePaisNaoEncontrada() throws Exception {
		CurrentWeatherWSClient.obterCurrentWeather("Florianopolis Aeroporto", "Espanha");
	}

	@Test
	public void deveObterCidadesPorPais() throws Exception {

		CidadePorPaisDto cidadesPorPais = CurrentWeatherWSClient.obterCidadesPorPais("Brazil");
		Assert.assertNotNull(cidadesPorPais);
		Assert.assertTrue(!cidadesPorPais.getCidades().isEmpty());
	}

	@Test(expected = CidadesNotFoundException.class)
	public void deveLancarExceptionCidadesNotFound() throws Exception {

		CurrentWeatherWSClient.obterCidadesPorPais("Brazilandia");
	}

}
