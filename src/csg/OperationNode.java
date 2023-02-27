package csg;

public abstract class OperationNode extends CSGTreeNode{
	
	private CSGTreeNode left;
	private CSGTreeNode right;
	
	public CSGTreeNode getLeft(){
		return this.left;
	}
	
	public CSGTreeNode getRight(){
		return this.right;
	}
	
	public void setLeft(CSGTreeNode node){
		this.left = node;
	}
	public void setRight(CSGTreeNode node){
		this.right =node;
	}
	
	public OperationNode(CSGTreeNode left, CSGTreeNode right){
		this.left = left;
		this.right = right;
	}
}
