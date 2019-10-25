/*
 * Name: Aaron Nathanson
 * Project: Testing modifications to Random Number Generators
 * Purpose: To determine which if any kind of modification is better than other forms of modification
 */
import java.util.Scanner;
public class Test {
	
	final static int mask = (1 << 31) - 1;
	public static void main(String[] args) 
	{
		int testChoice;
		boolean again = true;
		Scanner scan = new Scanner(System.in);
		while(again == true)
		{
			
			System.out.println("Please select an option");
			System.out.println("1 = basic random set");
			System.out.println("2 = NIST Test Suite");
			System.out.println("3 = TestU01");
			System.out.println("4 = Diehard Test");
			System.out.println("5 = quit");
			testChoice = scan.nextInt();
			RandomGenerator lcg = new RandomGenerator();
			if(testChoice == 1)
			{
				//outputs 10 iterations from each algorithm
				int [] help;
				
				System.out.println("Basic LCG:");
				help = lcg.LCG(10);
				for (int i = 0; i < 10; i++) System.out.println(help[i]);
				System.out.println();
				System.out.println("Compound Random:");
				help = lcg.LCGCR(10);
				for (int i = 0; i < 10; i++) System.out.println(help[i]);
				System.out.println();
				System.out.println("Time Mod:");
				help = lcg.LCGT(10);
				for (int i = 0; i < 10; i++) System.out.println(help[i]);
				System.out.println();
				System.out.println("Simon Mod:");
				help = lcg.LCGS(10);
				for (int i = 0; i < 10; i++) System.out.println(help[i]);
			}
			else if(testChoice == 2)
			{
				NISTTestSuite NIST = new NISTTestSuite();
				int[] control = lcg.LCG(1000);
				int[] cr = lcg.LCGCR(1000);
				int[] time = lcg.LCGT(1000);
				int[] simon = lcg.LCGS(1000);
				//runs each algorithm through the NIST Test Suite
				//<----------------------------------------------------------------------------------->
				//Test 1:Frequency (Monobit) Test
					//Purpose is to determen if their is a 50/50 split if outputs are either 1 or 0
					//converts all indexs of the array to 1 and 0 by %2
				System.out.println("Test 1:Frequency (Monobit) Test");
				NIST.testOne(control);
				NIST.testOne(cr);
				NIST.testOne(time);
				NIST.testOne(simon);
				//TEST 1
				//<------------------------------------------------------------------------------------------------>
				
				//<------------------------------------------------------------------------------------------------>
				//TEST 2: Frequency Test within a Block
					//purpose is to determine the frequency of 1 and 0 within a block
					//a algorith passes if its p value >= 0.01
				System.out.println("Test 2: Frequency Test within a Block");
				NIST.testTwo(control);
				NIST.testTwo(cr);
				NIST.testTwo(time);
				NIST.testTwo(simon);
				//Test 2
				//<------------------------------------------------------------------------------------------------>
				
				//<------------------------------------------------------------------------------------------------>
				//Test 3: Runs Test
					/*
					 * Test 3 will not be run for 2 reasons
					 * 1: From "A Statistical Test Suite for
					 *	  Random and Pseudorandom
					 *	  Number Generators for
					 *	  Cryptographic Applications by AndrewRukhin,JuanSoto,JamesNechvatal,Miles
					 *	  Smid,ElaineBarker,Stefan Leigh,MarkLevenson,Mark
					 *	  Vangel,DavidBanks,AlanHeckert,JamesDray,SanVo" Which these test are based off
					 *	  The document clearly stats that if Test 2 is passed Test 3 does not need to be run
					 *
					 * 2: Due to the LCG the base algorithm, all modifications are guaranteed to fail. 
					 *    This is a limitation of the base algorithm and not reflective of the modifications
					 */
				//Test 3
				//<----------------------------------------------------------------------------------------------->
				
				//<----------------------------------------------------------------------------------------------->
				//Test 4: Test for the Longest Run of Ones in a Block 
				//Purpose of test to detect the longest runs of 1 example 1111110001
				  //and determine if runs are random
				control = lcg.LCG(128);
				cr = lcg.LCGCR(128);
				time = lcg.LCGT(128);
				simon = lcg.LCGS(128);
				
				System.out.println("Test for the Longest Run of Ones in a Block ");
				NIST.testFour(control);
				NIST.testFour(cr);
				NIST.testFour(time);
				NIST.testFour(simon);
				//Test 4
				//<---------------------------------------------------------------------------------------------->
			}
			else if(testChoice == 3)
			{
				//runs each algorithm through TESTU01
				System.out.println("Test not yet implemented");
			}
			else if(testChoice == 4)
			{
				//runs each algorithm through Diehard test
				System.out.println("Test not yet implemented");
			}
			else if(testChoice == 5)
			{
				again = false;
				System.out.println("ending test session");
				scan.close();
			}
			else
			{
				System.out.println("Incorrect input");
			}
			
		}
		
	
		
	}
		
   
}

