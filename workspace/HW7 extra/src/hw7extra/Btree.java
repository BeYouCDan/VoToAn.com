package hw7extra;

public class Btree {
	Btree rightNode;
	Btree leftNode;
    char data;
	public Btree(Btree right, Btree left, char data){
		rightNode = right;
		leftNode = left;
		this.data = data;
	}
	public void setLeftTree(Btree left){
		leftNode = left;
	}
	public void setRightTree(Btree right){
		rightNode = right;
	}
	public Btree getRTree(){
		return rightNode;
	}
	public Btree getLTree(){
		return leftNode;
	}
	public char getData(){
		return data;
	}

}
