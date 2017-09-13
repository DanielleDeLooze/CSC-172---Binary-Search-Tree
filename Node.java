/*
 * Author: Danielle DeLooze
 * Student ID: 29493487
 * Date: 3/24/2017
 * Project: Project 3 Point Location
 * 
 * Used https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection#Given_two_points_on_each_line for the equation to find the intersection of two lines given the four points making up the 
 * start and end of each line
 */
public class Node {
	
		
	Line data;
	Node LeftChild;
	Node RightChild;
	Node Parent;
	
		
	Node(Line data, Node Parent){
		this.data = data;
		LeftChild = null;
		RightChild = null;
		this.Parent = Parent;
	}
		
	Node(Line data, Node LeftChild, Node RightChild){
		this.data = data;
		this.LeftChild = LeftChild;
		this.RightChild = RightChild;
	}
		
	public void preorder(){
		System.out.print(data.lineNum+ " ");
		if(LeftChild != null){
			LeftChild.preorder();
		}
		if(RightChild != null){
			RightChild.preorder();
		}
			
	}
		
	public void inorder(){
		if(LeftChild != null){
			LeftChild.inorder();
		}
		System.out.print(data.lineNum+ " ");
		if(RightChild != null){
			RightChild.inorder();
		}
	}
		
	public void postorder(){
		if(LeftChild != null){
			LeftChild.postorder();
		}
		if(RightChild != null){
			RightChild.postorder();
		}
		System.out.print(data.lineNum+ " ");
	}
	
	public int externalNodes(){
		int count = 0;
		if (RightChild == null){
			count++;
		}
		else{
			count = count + RightChild.externalNodes();
		}
		if(LeftChild == null){
			count++;
		}
		else{
			count = count + LeftChild.externalNodes();
		}
		return count;
	}
	
		
	
		
		
	
		
	public void insert(Line item){
		if(Comparer.intersection(this.data, item) != null){ //the case where the lines intersect
			if(LeftChild == null){
				LeftChild = new Node(item,this);
			}
			else{
				LeftChild.insert(item);
			}
			
			if(RightChild == null){
				RightChild = new Node(item,this);
			}
			else{
				RightChild.insert(item);
			}
		}
		else if(Comparer.side( item.start, this.data.start, this.data.end) == 0){ // the case where the line is to the right
			if(RightChild == null){
				RightChild = new Node(item,this);
			}
			else{
				RightChild.insert(item);
			}
		}
		
		else if(Comparer.side(item.start, this.data.start, this.data.end) == 1){
		

			
			if(LeftChild == null){
				LeftChild = new Node(item,this);
			}
			else{
				LeftChild.insert(item);
			}
			
		}
		
		
	}
}
