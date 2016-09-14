import java.util.*;
import java.lang.*;
public class sorting {
	   static int[] numbers = new int[400000];
	   static int[] numbers2 = new int[400000];
	   static int[] numbers3 = new int[400000];
	   static  int theDefaultSize = 400000;
	   static Random rand = new Random();
	   static int rNum = rand.nextInt(400000) + 1;
	   static int start = 1;
	   static int end = 400000;
	   static int rNum2 = rand.nextInt(4000000) + 320000;
	   
	   
	   public static void reArranging(){
	   for(int i = 0; i < numbers.length; i++){
		   numbers[i] = start;//random
		   numbers2[i] = start;
		   numbers3[i] = start;
		   start++;
		   
	   }
	   }
	
	//copied from the zybook
	   public static void insertionSort(int[] arr) {
		      int i, j, newValue;
		      for (i = 1; i < arr.length; i++) {
		            newValue = arr[i];
		            j = i;
		            while (j > 0 && arr[j - 1] > newValue) {
		                  arr[j] = arr[j - 1];
		                  j--;
		            }
		            arr[j] = newValue;
		      }
		}
	//copied from zybook
	public static void merge(int numbers [], int i, int j, int k) {
	      int mergedSize = k - i + 1;       // Size of merged partition
	      int mergedNumbers [] = new int[mergedSize]; // Temporary array for merged numbers
	      int mergePos = 0;                 // Position to insert merged number
	      int leftPos = 0;                  // Position of elements in left partition
	      int rightPos = 0;                 // Position of elements in right partition

	      leftPos = i;                      // Initialize left partition position
	      rightPos = j + 1;                 // Initialize right partition position

	      // Add smallest element from left or right partition to merged numbers
	      while (leftPos <= j && rightPos <= k) {
	         if (numbers[leftPos] < numbers[rightPos]) {
	            mergedNumbers[mergePos] = numbers[leftPos];
	            ++leftPos;
	         } 
	         else {
	            mergedNumbers[mergePos] = numbers[rightPos];
	            ++rightPos;
	         }
	         ++mergePos;
	      }

	      // If left partition is not empty, add remaining elements to merged numbers
	      while (leftPos <= j) {
	         mergedNumbers[mergePos] = numbers[leftPos];
	         ++leftPos;
	         ++mergePos;
	      }

	      // If right partition is not empty, add remaining elements to merged numbers
	      while (rightPos <= k) {
	         mergedNumbers[mergePos] = numbers[rightPos];
	         ++rightPos;
	         ++mergePos;
	      }

	      // Copy merge number back to numbers
	      for (mergePos = 0; mergePos < mergedSize; ++mergePos) {
	         numbers[i + mergePos] = mergedNumbers[mergePos];
	      }
	   }
	//copied from zybook
	public static void mergeSort(int numbers [], int i, int k) {
	      int j = 0;

	      if (i < k) {
	         j = (i + k) / 2;  // Find the midpoint in the partition

	         // Recursively sort left and right partitions
	         mergeSort(numbers, i, j);
	         mergeSort(numbers, j + 1, k);

	         // Merge left and right partition in sorted order
	         merge(numbers, i, j, k);
	      }
	   }
	//copied frmo zybook
		   public static int partition(int numbers [], int i, int k) {
		      int l = 0;
		      int h = 0;
		      int midpoint = 0;
		      int pivot = 0;
		      int temp = 0;
		      boolean done = false;

		      /* Pick middle element as pivot */
		      midpoint = i + (k - i) / 2;
		      pivot = numbers[midpoint];

		      l = i;
		      h = k;

		      while (!done) {

		         /* Increment l while numbers[l] < pivot */
		         while (numbers[l] < pivot) {
		            ++l;
		         }

		         /* Decrement h while pivot < numbers[h] */
		         while (pivot < numbers[h]) {
		            --h;
		         }

		         /* If there are zero or one items remaining,
		            all numbers are partitioned. Return h */
		         if (l >= h) {
		            done = true;
		         } else {
		            /* Swap numbers[l] and numbers[h],
		               update l and h */
		            temp = numbers[l];
		            numbers[l] = numbers[h];
		            numbers[h] = temp;

		            ++l;
		            --h;
		         }
		      }

		      return h;
		   }

		   public static void quicksort(int numbers[], int i, int k) {
		      int j = 0;

		      /* Base case: If there are 1 or zero entries to sort,
		       partition is already sorted */
		      if (i >= k) {
		         return;
		      }

		      /* Partition the data within the array. Value j returned
		         from partitioning is location of last item in low partition. */
		      j = partition(numbers, i, k);

		      /* Recursively sort low partition (i to j) and
		         high partition (j + 1 to k) */
		      quicksort(numbers, i, j);
		      quicksort(numbers, j + 1, k);

		      return;
		   }
		   
		   public static void main(String [] args) {
			   long start, end;
			   long total = 0;
			   long total2 = 0;
			   long total3 = 0;
			   
				   
			   /*for(int i = 0; i < numbers2.length; i++){
				   numbers[i] = end;
				   end--;
				   
			   }for(int i = 0; i < numbers3.length; i++){
				   if(i < (numbers3.length) - 200){
				   numbers[i] = start;
				   start++;}
				   else{
					   numbers[i] = rNum2;
					   
				   }
				   
			   }*/
			   reArranging();
			   for(int i = 0; i < 100; i++){
				   start = System.nanoTime();
				   insertionSort(numbers);
				   end = System.nanoTime();
				   total = total + end - start;
				   reArranging();
			   }
				   for(int i = 0; i < 100; i++){
				   
				   start = System.nanoTime();
				   mergeSort(numbers2, 0, theDefaultSize -1);
				   end = System.nanoTime();
				   total2 = total2 + end - start;
				   reArranging();
				   }
				   
				   for(int i = 0; i < 100; i++){
				   
				   start = System.nanoTime();
				   quicksort(numbers3, 0, theDefaultSize -1);
				   end = System.nanoTime();
				   total3 = total3 + end - start;
				   
				   reArranging();
			   } 
			   
			   System.out.println("this is the insertion for random " + total/100);
			   System.out.println("this is the mergesort " + total2/100);
			   System.out.println("this is the quicksort " + total3/100);
			   /*for(int i = 0; i < 100; i++){
				   mergeSort(numbers, 0, theDefaultSize -1);
				   
			   }
			   for(int i = 0; i < 100; i++){
				   insertionSort(numbers, theDefaultSize -1);
				   
			   }*/
			   
			   
			   
			   
			   
		   }

	
	
	
}
