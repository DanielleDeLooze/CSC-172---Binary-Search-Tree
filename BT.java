/*
 * Author: Danielle DeLooze
 * Student ID: 29493487
 * Date: 3/24/2017
 * Project: Project 3 Point Location
 * 
 * Used https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection#Given_two_points_on_each_line for the equation to find the intersection of two lines given the four points making up the 
 * start and end of each line
 */
public class BT {
	
	Node root;
	int size;
	int count = 0;
	
	
	BT(){
		root = null;
		size = 0;
	}
	
	public void insert(Line item){
		if(root == null){
			root = new Node(item,root);
			size++;
		}
		else{
			root.insert(item);
			size++;
		}
		//maybe put code here for tree building?
	}
	
	public int externalNodes(){
		if(root == null){
			return 0;
		}
		else{
			count = root.externalNodes();
		}
		return count;
	}
	
	public int epl() {
		 return epl(root,0);
	}

	private static int epl(Node r, int level) {
		 if (r==null){
			 return level;
		 }else{
			 return epl(r.LeftChild, level+1) + epl(r.RightChild, level+1);
		 }
	}

	
	public void preorder(){
		root.preorder();
	}
	
	public void inorder(){
		root.inorder();
	}
	
	public void postorder(){
		root.postorder();
	}
}
