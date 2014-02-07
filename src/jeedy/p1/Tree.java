package jeedy.p1;

import java.util.*;
class Tree {
	private int depthOfTree;
	private int leafNodeCount;
	private int nodeCount;
	
	private List<Node> nodes;
	private List<int[]> nodeLevels;
	
	public Tree(int leafNodeCount){
		this.depthOfTree = leafNodeCount;
		this.leafNodeCount = leafNodeCount;
		this.nodeCount = leafNodeCount*2-1;
		
		nodes = new ArrayList<Node>();
		nodeLevels = new ArrayList<int[]>();
		
		createNodes();
		if(createBaseTree()){
			createOtherTree();
//			sortTotalNodeLevels();
		}
	}
	public List<int[]> getNodeLevels(){
		return nodeLevels;
	}
	public int getDepthOfTree() {
		return depthOfTree;
	}

	public int getLeafNodeCount() {
		return leafNodeCount;
	}

	public int getNodeCount() {
		return nodeCount;
	}
	
	private void printLeafNodeLevel(){
		if(nodes != null){
			List<Node> l = nodes;
			Node n = null;
			int[] level = new int[leafNodeCount];
//			System.out.println("Tree.printLeafNodeLevel()-level.length "+level.length);
			
			for(int i=0, j=0; i < l.size() ; i++){
				n = l.get(i);
				
				if(n.isLeafNode()){
//					System.out.println(n.getItem());
//					System.out.println(n.isLeafNode()+", Level = "+n.getLevel());	
					level[j] = n.getLevel();
					j++;
				}
			}
			sortLevel(level);
			printNodeLevel(level);
			
			nodeLevels.add(level);
		}else{
			System.out.println("노드를 설정하지 않으셨군요.");
		}
	}
	
	private void sortTotalNodeLevels() {
		// TODO Auto-generated method stub
		int i, j, m;
		int[] temp;
		for(m = 0; m<leafNodeCount; m++){
			for(i=1; i<nodeLevels.size(); i++){
				for(j=i-1, temp=nodeLevels.get(i);
					j>=0 && nodeLevels.get(j)[m]<temp[m] ; j--){
					nodeLevels.set(j+1, nodeLevels.get(j));
				}
				nodeLevels.set(j+1, temp);
			}
		}
		
		for(i=0; i<nodeLevels.size(); i++){
			temp = nodeLevels.get(i);
			printNodeLevel(temp);
		}
		
	}
	private void sortLevel(int[] level) {
		// TODO Auto-generated method stub
		int temp, i, j;
		
		// 삽입정렬 사용.
		for(i=1;i<level.length;i++) {
			for(j=i-1, temp=level[i] ; j>=0 && level[j]>temp ; j--) {
				level[j+1]=level[j];
			}
			level[j+1]=temp;
		}
	}
	private void printNodeLevel(int[] level){
		for(int z=0; z<level.length; z++){
			if(z > 0){
				System.out.print(",");
			}
			System.out.print(level[z]);
		}
		System.out.println();
	}
	private void createOtherTree() {
		// TODO Auto-generated method stub
		/* 이 로직을 실행하기 위해선 적어도 할아버지 노드(GrandfatherNode)의 형제가 있어야 하기 때문에
		 * 속도증가를 위해 아예 카운터에서 빼버렸습니다.
		*/
		Node m_node = null;					// 옮겨질 노드(myNode)
		Node s_node = null;					// 옮겨질 노드의 형제노드(siblingNode)
		Node p_node = null;					// 옮겨질 노드의 부모노드(parentNode)
		List<Node> g_nodeBrotherly = null;	// 옮겨질 노드이 할아버지 형제노드들
		int g_nodeLevel = 0;
		
		try{
			for(int i=0; i <= (depthOfTree-4) ; i++){
				m_node = nodes.get((depthOfTree-1)-i);
				s_node = m_node.getSiblingNode();
//				System.out.println("m_node item = "+m_node.getItem()+" 여기서 부터는 otherTree");
				
				g_nodeLevel = m_node.getParentNode().getParentNode().getLevel();
				g_nodeBrotherly = getBrotherlyLeafNodes(g_nodeLevel);
//				int z=1;
				while(!(g_nodeBrotherly.isEmpty())){
//					System.out.println("Tree.createOtherTree() count : "+z+", g_nodeBrotherly = "+g_nodeBrotherly );
					for(int j=0; j < g_nodeBrotherly.size() ; j++){
						if(g_nodeBrotherly.get(j).isLeafNode()){
							m_node.getParentNode().makeLeafNode();
							g_nodeBrotherly.get(j).setRightNode(m_node);
							g_nodeBrotherly.get(j).setLeftNode(s_node);
							break;
						}
					}
					
					printLeafNodeLevel();	//LeafNodeLevel 출력 및 저장
					
					g_nodeLevel = m_node.getParentNode().getParentNode().getLevel();
					g_nodeBrotherly = getBrotherlyLeafNodes(g_nodeLevel);
//					z++;
				}
			}
		}catch(OverlappingNodeException e){
			e.printStackTrace();
		}
	}


	private List<Node> getBrotherlyLeafNodes(int level){
		List<Node> b_nodes = new ArrayList<Node>();
		Node node = null;
		
		for(int i=0; i < nodes.size(); i++){
			node = nodes.get(i);
			if(node.getLevel() == level && node.isLeafNode()){
				b_nodes.add(node);
			}
		}
		return b_nodes;
	}

	/*
	 * 기본적인 오른쪽우선 순위로 만들어진 트리입니다.
	 */
	private boolean createBaseTree(){
		// TODO Auto-generated method stub
		Node node = null;
		Node r_node = null;
		Node l_node = null;
		
		try{
			int isNotLeafNode = nodes.size()-leafNodeCount;
			// 오른쪽 노드에 자식노드(childNode) 추가
			for(int i=0; i < isNotLeafNode; i++){
				node = nodes.get(i);
				r_node = nodes.get(i+1);
				node.setRightNode(r_node);
			}
			// 왼쪽 노드에 자식노드(childNode) 추가
			for(int i=0 ; i < (nodes.size()-leafNodeCount) ; i++){
				node = nodes.get(isNotLeafNode -1 - i);		// nodes는 0부터 시작하기 때문에 -1
				l_node = nodes.get(leafNodeCount + i);
				node.setLeftNode(l_node);
			}
			printLeafNodeLevel();
		}catch(OverlappingNodeException e){
			return false;
		}
		return true;
	}

	private void createNodes() {
		// TODO Auto-generated method stub
		Node node = null;
		
		for(int i=0; i<nodeCount; i++){
			node = new Node((char)('A'+i));
			nodes.add(node);
		}
	}
	private boolean getIsLeafNode(List<Node> brotherly) {
		// TODO Auto-generated method stub
		for(int i=0; i < brotherly.size() ; i++){
			if(brotherly.get(i).isLeafNode())
				return true;
		}
		return false;
	}
	
	public static void main(String[] args){
		int input = 10;
		Tree tree = new Tree(input);
	}
}
