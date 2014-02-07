package jeedy.p1;

import java.io.*;
public class Project1Main {
	BufferedReader br;
	
	public Project1Main(){
		br = new BufferedReader(new InputStreamReader( System.in));
	}
	
	int consoleInput(int maxValue){
		String s;
		int i = 0;
		try{
			System.out.println( "#숫자를 입력하세요# ( 1 ~ "+maxValue+" 사이의 값 입력.)");
			s = br.readLine();
			i = Integer.parseInt(s);
			
			if( 1 > i || i > maxValue){
				throw new Exception("최대 입력할 수 있는 값은 "+maxValue+" 입니다. 다시 입력하세요");
			}
		}catch(IOException io){
			io.printStackTrace();
		}catch(NumberFormatException nfe){
			System.out.println("숫자만 입력하세요");
			i = consoleInput( maxValue);
		}catch(Exception e){
			System.out.println(e.toString());
			i = consoleInput( maxValue);
		}
		return i;
	}
	
	public void run(){
		int leafnode = consoleInput(25);
		
		System.out.println("leafnode : "+leafnode);
		
		Tree tree = new Tree(leafnode);
		
		finish();
	}
	private void finish(){
		try{
//			System.out.println("IO stream 객체 닫기");
			br.close();
		}catch(Exception e){}
	}
	
	public static void main(String[] args)
	{
		new Project1Main().run();		
	}
}
