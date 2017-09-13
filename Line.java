/*
 * Author: Danielle DeLooze
 * Student ID: 29493487
 * Date: 3/24/2017
 * Project: Project 3 Point Location
 * 
 * Used https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection#Given_two_points_on_each_line for the equation to find the intersection of two lines given the four points making up the 
 * start and end of each line
 */
public class Line {
	Point start;
	Point end;
	int lineNum;
	Point midpoint;
	
	
	Line(Point start, Point end, int lineNum){
		this.start = start;
		this.end = end;
		this.lineNum = lineNum;
		midpoint = new Point((start.x + end.x)/2,(start.y+end.y)/2);
	   
	}
	
	public boolean isPoint(){
		if(start.x == end.x && start.y == end.y){
			return true;
		}
		else{
			return false;
		}
	}
	
	
}
