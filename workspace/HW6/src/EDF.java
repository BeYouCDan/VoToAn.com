
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class EDF {
 
   public static void main(String[] args) throws NumberFormatException, IOException {
      String line, process;
      BufferedReader inputStrem;
      StringTokenizer st;
      long deadline, amountTime,fakeTime, totalTime;
      if(args.length != 1)
      {
         System.err.println("Incorrect number of arguments: "+args.length);
         System.err.println("java hw6.EDF <input-file>");
         System.exit(1); 
      }
      File file = new File(args[0]);
      MyPriorityQueue<Record> queue = new MyPriorityQueue<Record>(10);
      long current_time=0;
      try{
         inputStrem = new BufferedReader(new FileReader(file));
         while ((line = inputStrem.readLine()) != null) {
        	st = new StringTokenizer(line);
            process = st.nextToken();
            deadline = Long.valueOf(st.nextToken()).longValue();
            amountTime = Long.valueOf(st.nextToken()).longValue();
            Record current = new Record(process, deadline, amountTime);
            if(process.equals("schedule")){
               queue.add(current);
               System.out.println(current_time + ": " + " adding " + current.toString());
            }
            else if(process.equals("run")){
               while(true){
               Record notCurrent = queue.poll();
               if(notCurrent == null){
                  break;
               }
               totalTime = deadline;
               if(current_time <= totalTime){
                  fakeTime = current_time;;
                  System.out.println(current_time + ": " + " busy " + notCurrent.toString());
                  current_time = current_time + notCurrent.GetDuration();
                  if(current_time <= totalTime ){
                     System.out.println(current_time + ": " + " done " + notCurrent.toString(current_time));
                     
                  }
               }
                  
               else{
                 long leftOver =  current_time - totalTime;
                 notCurrent = new Record(notCurrent, leftOver);
                 queue.add(notCurrent);
               System.out.println(current_time + ": " + " adding " + notCurrent.toString());
                break; 

               }
               }
            }


         }



      }
      catch(FileNotFoundException e)
      {
         System.err.println("Failed to open "+file);
         System.exit(1);
      }
      System.exit(0);

   }

}

