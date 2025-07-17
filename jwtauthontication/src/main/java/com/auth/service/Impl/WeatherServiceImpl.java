package com.auth.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.auth.DTO.WeatherResponse;
import com.auth.service.WeatherService;
@Service
public class WeatherServiceImpl implements WeatherService{
   
   @Autowired
   private RestTemplate template;
   
    @Value("${weather.key}")
	private String weatherapikey;
   
	@Override
	public WeatherResponse getWeather(String city) {
		String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+weatherapikey;
		return template.getForObject(url, WeatherResponse.class);

	}

}
