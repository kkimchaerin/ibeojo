package com.goming.weather.model;

public class CoordinateDTO
{
	public double lat;
	public double lng;
	public double x;
	public double y;

	public String toString()
	{
		return "Coordinate [lat=" + lat + ", lng=" + lng + ", x=" + x + ", y=" + y + "]";
	}
	public void toString2()
	{
		System.out.println( "Coordinate [lat=" + lat + ", lng=" + lng + ", x=" + x + ", y=" + y + "]");
	}
	
	public double getLat()
	{
		return lat;
	}
	public void setLat(double lat)
	{
		this.lat = lat;
	}
	public double getLng()
	{
		return lng;
	}
	public void setLng(double lng)
	{
		this.lng = lng;
	}
	public double getX()
	{
		return x;
	}
	public void setX(double x)
	{
		this.x = x;
	}
	public double getY()
	{
		return y;
	}
	public void setY(double y)
	{
		this.y = y;
	}
	
	
}