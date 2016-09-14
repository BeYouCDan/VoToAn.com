package hw1;
import org.junit.*;
import static org.junit.Assert.*;
/*
 * Dae Kyung Kim
 * cs12whz
 * A12741934
 */

public class CounterStatTest {

	/* TODO:  Add your name, login, and ID as specified in the instructions */
		private CounterStat stat;

		/* this sets up the test fixture. JUnit invokes this method before
	 	   every testXXX method.  The @Before tag tells JUnit to run this method 
		   before each test */
		@Before
		public void setUp() throws Exception {
			stat = new CounterStat(1,2,3);
		}
		
		/* The @Test tag tells JUnit this is a test to run */
		@Test
		public void testgetTotalGames() {
			System.out.println("Checking getTotalGames");
			assertEquals(6, stat.getTotalGames());
		}

		@Test
		public void testIncrements() {
			System.out.println("Checking Proper Increment");
			stat.incrementComputerWins();
			assertEquals(7, stat.getTotalGames());
			stat.incrementUserWins();
			assertEquals(8, stat.getTotalGames());
			stat.incrementTies();
			assertEquals(9, stat.getTotalGames()); 
		}
	

		@Test
         /** gonna check to see if this is the right thing to do for this
          * programming assignment
          */ 
		public void testReset() {
			System.out.println("Checking Reset");
         assertFalse(0 == stat.getTotalGames());
			
			/* TODO: finish a test the verifies Reset */
		}


      @Test
      public void testAverageGames(){
			System.out.println("Checking AverageGames");
         assertFalse(100 == stat.averageGames(0) + stat.averageGames(1) + stat.averageGames(2));
      }

		/* TODO: write a test that verifies the proper calculation of average for all games  */
		
      
      @Test
		public void testResetWrong(){
			System.out.println("Checking RestWrong");
            fail();
      }
		/* TODO: write a test that verifies the resetWrong method. This test must FAIL */

}
