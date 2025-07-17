package com.auth.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private WeatherMain main;

	public WeatherMain getMain() {
		return main;
	}

	public void setMain(WeatherMain main) {
		this.main = main;
	}
}
