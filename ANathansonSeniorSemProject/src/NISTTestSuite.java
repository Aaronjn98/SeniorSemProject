//Holds the NIST Test Suite
public class NISTTestSuite 
{
	public void testOne(int[] algo)
	{
		/*
		 * TestOne: Frequency (Monobit) Test
		 * This test asks the Generator outoput a number of 
		 * 1s and 0s, and determine the frequency of them.
		 * The closer to 50 percent the more seemingly random
		 * the generator is.
		 * 
		 * This function first passes in an int array and modifies 
		 * every entry by 2. this causes all even entries to become 0
		 * and all odd enties to become 1. While this loop is going
		 * a counter adds up every index that equals 1. The counter is
		 * then divided by the size of the array to get the percentage
		 *  
		 * (not implemnted yet)
		 * Then using a simple kai squared formula the program 
		 * generates a p-value. any p-value >= .01 passes.
		 * 
		 */
		int[] array= algo;
		double percentOfOnes = 0;
		for(int i = 0; i < array.length; i++)
		{
			array[i]= array[i] % 2;
			if(array[i] == 1) percentOfOnes++;
			
		}
		System.out.println(percentOfOnes);
	}
	public void testTwo(int[]algo)
	{
		/*
		 * Test 2: Frequency Test within a Block
		 * Test 2 takes an int array and modifies it to output
		 * 1 and 0. Each index of the array is then concatinated into
		 * a string. The string is then divided into blocks of 100.
		 * This is done so seperate blocks can be checked for frequency
		 * in the same way test one is done
		 * 
		 * This is done because it is possible for an output with 
		 * the first half being all 1s and the second half being all 0s
		 * to pass the first test, but would fail this test.
		 * 
		 * A p-value is calculated with a simple kai squared equation.
		 * Any p-value >= .01 passes.
		 */
		int[] array= algo;
		String n = "";
		
		double M = 100;
		for(int i = 0; i < array.length; i++)
		{
			array[i]= array[i] % 2;
			n = n + array[i];
		}
		String[] blocks = new String[10];
		for (int i = 0; i < 10; i++)
		{
			blocks[i] = n.substring(0, 99);
			n = n.substring(99);
		}
		double[] blocksOnes = new double[10];
		
		for (int i = 0; i<10; i++)
		{
			int count = 0;
			for(int j = 0; j<99; j++)
			{
				
				if(blocks[i].charAt(j) == '1') count++;	
				
			}
			
			blocksOnes[i] = count / M;
		}
		double xSquared = 0;
		
		for(int i = 0; i < 10; i++)
		{
			//System.out.println("blockOnes:" + blocksOnes[i]);
			xSquared += ((blocksOnes[i] - .5) * (blocksOnes[i] - .5));
		}
		double pvalue = xSquared * 40;
		System.out.println("p-value = "+ pvalue);
	}
	public void testThree(int[] algo)
	{
		/*
		 * Test 3: Runs Test
		 * As explained in Test.Java test three is satisfied
		 * if test two is passed, thus their is no need to 
		 * program this test at this moment
		 */
	}
	public void testFour(int[] algo)
	{
		/*
		 * Test 4: Test for the Longest Run of Ones in a Block
		 * Test 4 takes an int array of 128. It converts
		 * all indexes to one and zero and divides them into 
		 * blocks of 16 in the same process that test 2 does.
		 * 
		 * A loop then runs through and calculates the number
		 * of runs of 1 i.e. how many consecutive 1s in a row 
		 * before a zero appears in each block. the a counter finds 
		 * the highest run in each block and updates one of the four
		 * counters based off that.
		 *  
		 * Afterwords all counters are thrown into a kai squared
		 * equation to generate a p-value
		 * if p-value >= .01 the the generator passes the test
		 */
		int[] array= algo;
		String n = "";
		for(int i = 0; i < array.length; i++)
		{
			array[i]= array[i] % 2;
			n += array[i];
		}
		String[] blocks = new String[16];
		for (int i = 0; i < 15; i++)
		{
			blocks[i] = n.substring(0, 7);
			n = n.substring(7);
		}
		double runOne = 0;
		double runTwo = 0;
		double runThree = 0;
		double runFour = 0;
		for (int i = 0; i < 10; i++)
		{
			int count = 0;
			for(int j = 0; j< 7; j++)
			{
				
				if(blocks[i].charAt(j) == '1') count++;	
				
			}
			
			if(count <= 1)
			{
				runOne++;
			}
			else if(count == 2)
			{
				runTwo++;
			}
			else if(count == 3)
			{
				runThree++;
			}
			else if(count >= 4)
			{
				runFour++;
			}
		}
		double runOneFinal = ((runOne - 3.4368) * (runOne - 3.4368))/3.4368;
		double runTwoFinal = ((runTwo - 5.8752) * (runTwo - 5.8752))/5.8752;
		double runThreeFinal = ((runThree - 3.688) * (runThree - 3.688))/3.688;
		double runFourFinal = ((runFour - 3) * (runFour - 3))/3;
		double pvalue = runOneFinal + runTwoFinal + runThreeFinal + runFourFinal;
		System.out.println("p-value = " + pvalue);
	}

}