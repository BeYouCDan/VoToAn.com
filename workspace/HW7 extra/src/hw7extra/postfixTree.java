package hw7extra;
import java.io.*;
import java.util.StringTokenizer;
public class postfixTree {
	static String[] List = new String[10];
	static int i = 0;
	public static void reculsive(Btree theFinal){
		if(theFinal != null){
			reculsive(theFinal.getLTree());
			List[i] = Character.toString(theFinal.getData());
			i++;
			reculsive(theFinal.getRTree());
		}
		
		
	}

	 public static void main(String[] args) throws NumberFormatException, IOException {
	BufferedReader inputStrem;
	String line, theLine;
	String finalAns = " ";
    StringTokenizer st;
    char[] chars;
    if(args.length != 1)
    {
       System.err.println("Incorrect number of arguments: "+args.length);
       System.exit(1); 
    }
    File file = new File(args[0]);
    inputStrem = new BufferedReader(new FileReader(file));
    Btree theTree;
    MyStack<Btree> theStack = new MyStack<Btree>();
    while ((line = inputStrem.readLine()) != null) {
        st = new StringTokenizer(line);
        theLine = st.nextToken();
        chars = theLine.toCharArray();
        for(int i = 0; i < chars.length; i++){
        	if(chars[i] == 'a' || chars[i] == 'b' || chars[i] == 'c'){
        	
        		theStack.addElement(new Btree(null, null, chars[i]));
        	}
        	if(chars[i] == '/' || chars[i] == '*' || chars[i] == '+' || chars[i] == '-'){
        		theTree = new Btree(theStack.removeElement(), theStack.removeElement(), chars[i]);
        		theStack.addElement(theTree);
        		
        }
        	
        
    
	 }
       Btree finalTree = theStack.removeElement();
       reculsive(finalTree);
       for(int i = 0; i < List.length; i++){
    	   finalAns = finalAns + List[i];
       }
       System.out.println(finalAns);
       
	 }
}
}