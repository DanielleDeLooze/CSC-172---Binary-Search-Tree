/*
 * Author: Danielle DeLooze
 * Student ID: 29493487
 * Date: 3/24/2017
 * Project: Project 3 Point Location
 * 
 * Used https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection#Given_two_points_on_each_line for the equation to find the intersection of two lines given the four points making up the 
 * start and end of each line
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Comparer {
	
	public static void main(String[] args) throws IOException{
		int lineNum;
		Line[] lines;
		
		ArrayList<Point> points = new ArrayList<Point>();
		BT tree = new BT();
		
		
		File infile = new File("test.txt");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(infile);
		
		lineNum = scan.nextInt();
		lines = new Line[lineNum];
		
		for(int i = 0; i < lineNum; i++){
			Point start = new Point(scan.nextDouble(), scan.nextDouble());		
			Point end = new Point(scan.nextDouble(), scan.nextDouble());
			
			lines[i] = new Line(start,end, i);
			
		}
		
		while(scan.hasNextDouble()){
			Point temp = new Point(scan.nextDouble(), scan.nextDouble());
			points.add(temp);
		}
		
		for(int i = 0; i < lineNum; i++){
			tree.insert(lines[i]);
		}
		
		while(!points.isEmpty()){
			if(region(tree.root, points.get(0),points.get(1)) == null){
				System.out.println("Points ("+points.get(0).x+","+points.get(0).y+") and ("+points.get(1).x+","+points.get(1).y+") are in the same region!");
				points.remove(0);
				points.remove(0);
			}
			else{
				System.out.println("Points ("+points.get(0).x+","+points.get(0).y+") and ("+points.get(1).x+","+points.get(1).y+") are not in the same regions" );
				System.out.println("Line "+ region(tree.root,points.get(0), points.get(1)).lineNum+" is one of the lines that seperates the two points" );
				points.remove(0);
				points.remove(0);
			}
		}
		
		System.out.println("External Nodes is equal to: " +tree.externalNodes());
		System.out.println("Average external path length is equal to: "+tree.epl()/tree.externalNodes());
		
		
		
	}//end main
	
	public static int side(Point p0, Point p1, Point p2){
		
		double dx1 = (p1.x - p0.x);
		double dy1 = (p1.y - p0.y);
		double dx2 = (p2.x - p0.x);
		double dy2 = (p2.y - p0.y);
		
		
		if( (dx1 * dy2) > (dx2 * dy1)){
			return 1; //counterclockwise 
		}
		else if( (dx1 * dy2) < (dx2 * dy1)){
			return 0; //clockwise 
		}
		else if( (dx1 * dx2 < 0) || (dy1 * dy2 < 0)){
			return 0; //clockwise
		}
		else if( (dx1 * dx1) + (dy1 * dy1) < (dx2 * dx2) + (dy2 * dy2)){
			return 1; //counterclockwise
		}
		else{
			return 2; //co-linear
		}
	}
	
	public static Point intersection(Line L1, Line L2){
		double slope1 = (L1.end.y - L1.start.y) / (L1.end.x - L1.start.x);
		double slope2 = (L2.end.y - L2.start.y) / (L2.end.x - L2.start.x); //calculation slopes for both of the lines
		
		if(slope1 == slope2){
			return null; //parallel lines will never intersect
		}
		
		if(L1.isPoint()){
			double value = slope2 * (L1.start.x - L2.start.x) + L2.start.y;
			if(value == L1.start.y){
				return L1.start;
			}
		} //if L1 is just a point
		
		if(L2.isPoint()){
			double value = slope1 * (L2.start.x - L1.start.x) + L1.start.y;
			if(value == L2.start.y){
				return L2.start;
			}
		} //if L2 is just a point
		
		double bottom = (L1.start.x - L1.end.x) * (L2.start.y - L2.end.y) - (L1.start.y - L1.end.y) * (L2.start.x -L2.end.x);
		double topx = (((L1.start.x * L1.end.y) - (L1.start.y * L1.end.x)) * (L2.start.x - L2.end.x) ) - ((L1.start.x - L1.end.x) * ( ( L2.start.x * L2.end.y) - (L2.start.y * L2.end.x)));
		double topy = (((L1.start.x * L1.end.y) - (L1.start.y * L1.end.x)) * (L2.start.y - L2.end.y) ) - ((L1.start.y - L1.end.y) * ( ( L2.start.x * L2.end.y) - (L2.start.y * L2.end.x)));		
		
	    Point intersect = new Point(topx/bottom,topy/bottom);
	    
	    if(topx/bottom < 0 || topx/bottom > 1 || topy/bottom < 0 || topy/bottom > 1){
	    	return null; //if the intersection is outside of the unit square, then return null
	    }
	    
		
		return intersect;
	}
	
	public static Line region(Node node, Point p0, Point p1){
		int side1 = side(p0,node.data.start,node.data.end);
		int side2 = side(p1,node.data.start,node.data.end);
		
		
		if(p0.equals(p1)){
			return null;
		}
		else if (side1 != side2){
			return node.data;
		}
		else if(side1 == 1){
			if(node.LeftChild == null){
				return null;
			}
			else{
				return region(node.LeftChild, p0,p1);
			}
		}
		else if(side1 == 0){
			if(node.RightChild == null){
				return null;
			}
			else{
				return region(node.RightChild,p0,p1);
			}
		}
		else{
			return null;
		}
	}
	
}//end class
