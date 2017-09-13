/*
 * Author: Danielle DeLooze
 * Student ID: 29493487
 * Date: 3/24/2017
 * Project: Project 3 Point Location
 * 
 * Used https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection#Given_two_points_on_each_line for the equation to find the intersection of two lines given the four points making up the 
 * start and end of each line
 */
public class Point {
	
	double x;
	double y;
	
	Point(double xval, double yval){
		this.x = xval;
		this.y = yval;
	}
	
	public boolean equals(Point p){
		if (this.x == p.x && this.y == p.y){
			return true;
		}
		else{
			return false;
		}
	}
	
}
	

