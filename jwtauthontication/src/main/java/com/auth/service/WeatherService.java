package com.auth.service;

import com.auth.DTO.WeatherResponse;

public interface WeatherService {
   public WeatherResponse getWeather(String city);
}
