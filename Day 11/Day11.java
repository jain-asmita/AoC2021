public class Day11
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int xLength = 10;
		int yLength = 10;
		int numIterations = 500;
		
		int[][] input = new int[xLength][yLength];
		
		for(int x = 0; x < xLength; ++x)
		{
		    for(int y = 0; y < yLength; ++y)
			{
			    input[x][y] = Integer.parseInt(input_byLine[y].substring(x, x+1));
			}
		}
		
		long silverAnswer = (long) 0;
		
		loop:
		for(int i = 0; i < numIterations; ++i)
		{
			int nFlashes = 0;
			input = IncrementByOne(input);			
			boolean isGreaterThan9 = IsGreaterThan9(input);
			
			while(isGreaterThan9)
			{
				nFlashes += CountChanges(input);
				// silverAnswer+=nFlashes
				isGreaterThan9 = IsGreaterThan9(input);
			}
			resetMinusOnes(input);
			if(nFlashes == 100)
			{
				StdOut.println(i);
				break loop;
			}
		}
		
		// StdOut.println(silverAnswer);
		
	}
	
	public static void printArray(int[][] input)
	{
	    for(int i = 0; i < 10; ++i)
		{
			for(int j = 0; j < 10; ++j)
			{
				StdOut.print(input[i][j] + " ");
			}
			StdOut.println("");
			StdOut.println("");
		}
	}
	
	
	public static void resetMinusOnes(int[][] input)
	{
		for(int x = 0; x < 10; ++x)
		{
			for(int y = 0; y < 10; ++y)
			{
				if(input[x][y] == -1)
				{
					input[x][y]++;
				}
			}
		}
	}
	
	public static int[][] IncrementByOne(int[][] input)
	{
		int xLength = input[0].length;
		int yLength = input.length;
		int[][] output = new int[xLength][yLength];
		
		for(int x = 0; x < xLength; ++x)
		{
			for(int y = 0; y < yLength; ++y)
			{
				output[x][y] = input[x][y] + 1;
			}
		}
		return output;
	}
	
	public static int CountChanges(int[][] input)
	{
		int xLength = input[0].length;
		int yLength = input.length;
		
		int[][] inputCopy = new int[xLength][yLength];
		for (int x = 0; x < xLength; ++x)
		{
			for(int y = 0; y < yLength; ++y)
			{
			    inputCopy[x][y] = input[x][y];	
			}
		}
		
		int output = 0;
		
		for(int x = 0; x < xLength; ++x)
		{
			for(int y = 0; y < yLength; ++y)
			{
				if(inputCopy[x][y] > 9)
				{
					input[x][y] = -1;
					output++;
				}
				
				int numIncrements = 0;
				
				int xPos = x - 1;
				int yPos = y;
				if(xPos >= 0)
				{
					if(inputCopy[xPos][yPos] > 9)
					{
						++numIncrements;
					}
				}
				
				xPos = x - 1;
                yPos = y - 1;
                if(xPos >= 0 && yPos >= 0)
				{
					if(inputCopy[xPos][yPos] > 9)
					{
						++numIncrements;
					}
				}
				
				xPos = x;
                yPos = y - 1;
                if(yPos >= 0)
				{
					if(inputCopy[xPos][yPos] > 9)
					{
						++numIncrements;
					}
				}
				
				xPos = x + 1;
                yPos = y - 1;
                if(xPos < xLength && yPos >= 0)
				{
					if(inputCopy[xPos][yPos] > 9)
					{
						++numIncrements;
					}
				}
				
				xPos = x + 1;
                yPos = y;
                if(xPos < xLength)
				{
					if(inputCopy[xPos][yPos] > 9)
					{
						++numIncrements;
					}
				}
				
				xPos = x + 1;
                yPos = y + 1;
                if(xPos < xLength && yPos < yLength)
				{
					if(inputCopy[xPos][yPos] > 9)
					{
						++numIncrements;
					}
				}
				
				xPos = x;
                yPos = y + 1;
                if(yPos < yLength)
				{
					if(inputCopy[xPos][yPos] > 9)
					{
						++numIncrements;
					}
				}
				
				xPos = x - 1;
                yPos = y + 1;
                if(xPos >= 0 && yPos < yLength)
				{
					if(inputCopy[xPos][yPos] > 9)
					{
						++numIncrements;
					}
				}
				
				if(input[x][y] != -1)
				{
				    input[x][y] += numIncrements;
				}
			}
		}
		
		return output;
	}
	
	public static boolean IsGreaterThan9(int[][] input)
	{
		int xLength = input[0].length;
		int yLength = input.length;
		
		for(int x = 0; x < xLength; ++x)
		{
			for(int y = 0; y < yLength; ++y)
			{
				if(input[x][y] > 9)
				{
					return true;
				}
			}
		}
		
		return false;
	}
}