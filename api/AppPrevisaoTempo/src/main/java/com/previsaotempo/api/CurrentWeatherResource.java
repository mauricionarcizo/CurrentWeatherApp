package com.previsaotempo.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.previsaotempo.dto.CidadePorPaisDto;
import com.previsaotempo.dto.PrevisaoTempoAtualDto;
import com.previsaotempo.ws.CurrentWeatherWSClient;

@Path("/previsaoTempo")
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class CurrentWeatherResource {
	
	private static final String COUNTRY = "Brazil";

	@GET
	@Path("byCity/{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPrevisaoTempoByCity(@PathParam("city")String city) throws Exception {
		PrevisaoTempoAtualDto current = CurrentWeatherWSClient.obterCurrentWeather(city, COUNTRY);
		Gson gson = new Gson();
		String json =gson.toJson(current);
		System.out.println(json);
		return Response.status(200).entity(json).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Path("cidades")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCidades() throws Exception {
		
		CidadePorPaisDto cidades = CurrentWeatherWSClient.obterCidadesPorPais(COUNTRY);
		Gson gson = new Gson();
		String json = gson.toJson(cidades.getCidades());
		return Response.status(200).entity(json).header("Access-Control-Allow-Origin", "*").build();
	}
}
