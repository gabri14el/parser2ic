package parser;

import java.awt.geom.Point2D;

public class Node extends Point2D{

	int id; 
	double lat; 
	double lon;
	
	String amenity;

	@Override
	public double getX() {
		return lat;
	}

	@Override
	public double getY() {
		return lon;
	}

	@Override
	public void setLocation(double x, double y) {
		lat = x;
		lon = y;
		
	}
	
	@Override
	public String toString() {
		return "amenity: "+amenity+ " lat: "+lat+" lon: "+lon;
	}
	
	@Override
	public boolean equals(Object obj) {
		Node f = (Node) obj; 
		return f.lat == this.lat && f.lon == this.lon;
	}
}
