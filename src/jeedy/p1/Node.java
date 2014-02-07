package jeedy.p1;

/*
 * TODO 노드
 */
public class Node {
	private char item;			// 노드번호
	private int level = 1;		// 레벨
	
	private Node p_node;		// 부모 노드
	private Node l_node;		// 왼쪽 자식노드
	private Node r_node;		// 오른쪽 자식노드
	
	public Node(char item){
		this.item = item;
	}
	public boolean isLeafNode(){
		if( l_node == null && r_node == null)
			return true;
		else
			return false;
	}
	
	public boolean makeLeafNode(){
		r_node=null;
		l_node=null;
		return false;
	}
	
	public Node getSiblingNode(){
		if(this.getItem() != p_node.getLeftNode().getItem()){
			return p_node.getLeftNode();
		}else {
			return p_node.getRightNode();
		}
	}
	
	public char getItem(){
		return item;
	}
	public int getLevel(){
		return level;
	}
	public Node getParentNode() {
		return p_node;
	}
	public Node getLeftNode(){
		return this.l_node;
	}
	public Node getRightNode(){
		return this.r_node;
	}
	
	public void setLevel( int level){
		this.level = level;
		if(r_node != null){
			r_node.setLevel(level+1);
		}
		if(l_node != null){
			l_node.setLevel(level+1);
		}
	}
	public void setLeftNode(Node node) throws OverlappingNodeException{
		if(node != null){
			if(r_node != null && r_node.item == node.item){
				throw new OverlappingNodeException("이미 RightNode에 같은 노드가 있습니다.");
			}else{
				node.p_node = this;
				node.setLevel(this.level+1);
				this.l_node = node;
			}
		}else{
			this.l_node=null;
		}
	}
	public void setRightNode(Node node) throws OverlappingNodeException{
		if(node != null){
			if(l_node != null && l_node.item == node.item){
				throw new OverlappingNodeException("이미 leftNode에 같은 노드가 있습니다.");
			}else{
				node.p_node = this;
				node.setLevel(this.level+1);
				this.r_node = node;	
			}
		}else{
			this.r_node=null;
		}
	}
}

class OverlappingNodeException extends Exception
{
	public OverlappingNodeException(){		
	
	}
	public OverlappingNodeException(String s){
		super(s);		
	}
}