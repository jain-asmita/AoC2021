import java.util.ArrayList;
public class Day20
{
    public static void main(String[] args)
    {
        String allInput = StdIn.readAll();
		long startTime = System.nanoTime();
		String[] input_byLine = allInput.split("\r\n\r\n");
		String algorithmStr = input_byLine[0];
		String[] inputImage_byLine = input_byLine[1].split("\r\n");
		int initialInputXLength = inputImage_byLine[0].length();
		int initialInputYLength = inputImage_byLine.length;
		
		int nIterations = 50;

		int[][] initialPaddedInput = new int[initialInputXLength][initialInputYLength];
		
		for(int x = 0; x < initialInputXLength; ++x)
		{
			for(int y = 0; y < initialInputYLength; ++y)
			{
				initialPaddedInput[x][y] = (inputImage_byLine[y].substring(x, x + 1)).equals(".") ? 0 : 1;
			}
		}
		
		int[] algorithm = new int[512];
		for(int i = 0; i < 512; ++i)
		{
			algorithm[i] = (algorithmStr.substring(i, i+1)).equals("#") ? 1 : 0;
		}
		//printArray(initialPaddedInput, initialInputXLength + paddedLines, initialInputYLength + paddedLines);
    
	    for(int iter = 0; iter < nIterations; ++iter)
	    {
	    	int xLength = initialPaddedInput[0].length + 4;
	    	int yLength = initialPaddedInput.length + 4;
			int padding = (iter) % 2;
	    	
	    	int[][] newInputArray = new int[xLength][yLength];
			
			for(int x = 0; x < xLength; ++x)
			{
				for(int y = 0; y < yLength; ++y)
				{
					if(x < 2 || x > xLength - 3 || y < 2 || y > yLength - 3)
					{
						newInputArray[x][y] = padding;
					}
					else
					{
						newInputArray[x][y] = initialPaddedInput[x - 2][y - 2];
					}
				}
			}
			
			//printArray(newInputArray, xLength, yLength);
			//StdOut.println("");
			
			int[][] outputArray = new int[xLength - 2][yLength - 2];
	    	
	    	for(int x = 1; x < xLength - 1; ++x)
	    	{
	    		for(int y = 1; y < yLength - 1; ++y)
	    		{
	    			ArrayList<Integer> algLookup = new ArrayList<Integer>();
	    			// for(int yLoc = y - 1; yLoc < y + 2; ++yLoc)
	    			// {
	    			// 	for(int xLoc = x - 1; xLoc < x + 2; ++xLoc)
	    			// 	{
	    			// 		algLookup.add(newInputArray[xLoc][yLoc]);
	    			// 	}
	    			// }
					algLookup.add(newInputArray[x - 1][y - 1]);
					algLookup.add(newInputArray[x][y - 1]);
					algLookup.add(newInputArray[x + 1][y - 1]);
					algLookup.add(newInputArray[x - 1][y]);
					algLookup.add(newInputArray[x][y]);
					algLookup.add(newInputArray[x + 1][y]);
					algLookup.add(newInputArray[x - 1][y + 1]);
					algLookup.add(newInputArray[x][y + 1]);
					algLookup.add(newInputArray[x + 1][y + 1]);
					
	    			outputArray[x - 1][y - 1] = lookup(algLookup, algorithm);
	    		}
	    	}
	    	initialPaddedInput = outputArray;
			//printArray(initialPaddedInput, xLength - 2, yLength - 2);
			//StdOut.println("");
	    }
		
		StdOut.println(countDarkPixels(initialPaddedInput));
		long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime * Math.pow(10, -9));
		//printArray(initialPaddedInput, initialPaddedInput[0].length, initialPaddedInput.length);
	}
	
	public static int countDarkPixels(int[][] array)
	{
		int darkPixels = 0;
		for(int x = 0; x < array[0].length; ++x)
		{
			for(int y = 0; y < array.length; ++y)
			{
				darkPixels += array[x][y];
			}
		}
		return darkPixels;
	}
	
	public static int lookup(ArrayList<Integer> algLookup, int[] algorithm)
	{
		int lookupVal = 0;
		for(int i = 0; i < 9; ++i)
		{
			lookupVal += Math.pow(2, 8 - i) * algLookup.get(i);
		}
		return algorithm[lookupVal];
	}
	
	// public static void printArray(int[][] array, int xLength, int yLength)
	// {
	// 	for(int x = 0; x < xLength; ++x)
	// 	{
	// 		for(int y = 0; y < yLength; ++y)
	// 		{
	// 			StdOut.print(array[y][x] == 0 ? "." : "#");
	// 		}
	// 		StdOut.println("");
	// 	}
	// }
}

