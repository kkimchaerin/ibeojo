package com.goming.weather.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class WeatherDTO
{
	private LocalDate fcstDate;
	private Time fcstTime; // 변경된 부분: LocalTime에서 Time으로 타입 변경

	private float temperature;
	private float humidity;
	private String weatherInfo;
	private float wind;
	private String rainy_prob;
	private String precipitation;
	private float lat;
	private float lon;

	public WeatherDTO(String fcstDateStr, String fcstTimeStr, float temperature, float humidity, String weatherInfo,
			float wind, String rainy_prob, String precipitation, float lat, float lon)
	{
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		try {
		    // Parsing fcstDateStr into LocalDate
		    this.fcstDate = LocalDate.parse(fcstDateStr, dateFormatter);

		    // Parsing fcstTimeStr directly into Time
		    this.fcstTime = Time.valueOf(fcstTimeStr);
		} catch (DateTimeParseException | IllegalArgumentException e) {
		    e.printStackTrace();
		    throw new IllegalArgumentException("Invalid date or time format");
		}


		this.temperature = temperature;
		this.humidity = humidity;
		this.weatherInfo = weatherInfo;
		this.wind = wind;
		this.rainy_prob = rainy_prob;
		this.precipitation = precipitation;
		this.lat = lat;
		this.lon = lon;
	}

	public void weatherString()
	{
		System.out.println("Forecast Date: " + fcstDate);
		System.out.println("Forecast Time: " + fcstTime);
		System.out.println("Temperature(TMP): " + temperature);
		System.out.println("Humidity(REH): " + humidity);
		System.out.println("Weather Info(SKY): " + weatherInfo);
		System.out.println("Wind(WSD): " + wind);
		System.out.println("Rain Probability(POP): " + rainy_prob);
		System.out.println("Precipitation(PCP): " + precipitation);
		System.out.println("Latitude: " + lat);
		System.out.println("Longitude: " + lon);
	}

	// Getters and setters
	public LocalDate getFcstDate()
	{
		return fcstDate;
	}

	public void setFcstDate(LocalDate fcstDate)
	{
		this.fcstDate = fcstDate;
	}

	public Time getFcstTime()
	{
		return fcstTime;
	}

	public void setFcstTime(Time fcstTime)
	{
		this.fcstTime = fcstTime;
	}

	public float getTemperature()
	{
		return temperature;
	}

	public void setTemperature(float temperature)
	{
		this.temperature = temperature;
	}

	public float getHumidity()
	{
		return humidity;
	}

	public void setHumidity(float humidity)
	{
		this.humidity = humidity;
	}

	public String getWeatherInfo()
	{
		return weatherInfo;
	}

	public void setWeatherInfo(String weatherInfo)
	{
		this.weatherInfo = weatherInfo;
	}

	public float getWind()
	{
		return wind;
	}

	public void setWind(float wind)
	{
		this.wind = wind;
	}

	public String getRainy_prob()
	{
		return rainy_prob;
	}

	public void setRainy_prob(String rainy_prob)
	{
		this.rainy_prob = rainy_prob;
	}

	public String getPrecipitation()
	{
		return precipitation;
	}

	public void setPrecipitation(String precipitation)
	{
		this.precipitation = precipitation;
	}

	public float getLat()
	{
		return lat;
	}

	public void setLat(float lat)
	{
		this.lat = lat;
	}

	public float getLon()
	{
		return lon;
	}

	public void setLon(float lon)
	{
		this.lon = lon;
	}
}
