package com.goming.ml.model;

import java.sql.Timestamp;

public class ml_DTO {

	private int ml_idx;
	private String user_email;
	private String ml_result;
	private Timestamp created_at;
	private int weather_idx;

	public int getMl_idx() {
		return ml_idx;
	}

	public void setMl_idx(int ml_idx) {
		this.ml_idx = ml_idx;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getMl_result() {
		return ml_result;
	}

	public void setMl_result(String ml_result) {
		this.ml_result = ml_result;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public int getWeather_idx() {
		return weather_idx;
	}

	public void setWeather_idx(int weather_idx) {
		this.weather_idx = weather_idx;
	}

	public ml_DTO(int ml_idx, String user_email, String ml_result, Timestamp created_at, int weather_idx) {
		super();
		this.ml_idx = ml_idx;
		this.user_email = user_email;
		this.ml_result = ml_result;
		this.created_at = created_at;
		this.weather_idx = weather_idx;
	}

}
