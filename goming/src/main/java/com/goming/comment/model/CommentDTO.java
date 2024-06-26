package com.goming.comment.model;

public class CommentDTO {

	private double lat;
	private double lon;
	
	public CommentDTO(double lat, double lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}
	
	public CommentDTO() {
		
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}
	
	
	
}
