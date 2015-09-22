package com.previsaotempo.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.previsaotempo.ws.CurrentWeatherDto;
import com.previsaotempo.ws.CurrentWeatherWSClient;

@Path("/previsaoTempo")
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class CurrentWeatherResource {
	
	private static final String COUNTRY = "Brazil";

	@GET
	@Path("{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCurrentWeather(@PathParam("city")String city) throws Exception {
		CurrentWeatherDto current = CurrentWeatherWSClient.obterCurrentWeather(city, COUNTRY);
		return Response.status(200).entity(current).build();
	}
}
