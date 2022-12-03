public class Day15Try2
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int xLength = input_byLine[0].length() * 5;
		int yLength = input_byLine.length * 5;
		int[][] input = new int[xLength][yLength];
		

		for(int x = 0; x < xLength / 5; ++x)
		{
		   	for(int y = 0; y < yLength / 5; ++y)
		   	{
				int val = Integer.parseInt(input_byLine[y].substring(x, x + 1));
		   		input[x][y] = val > 9 ? val - 9 : val;
		   	}
	    }
		
		for(int x = xLength / 5; x < xLength; ++x)
		{
			for(int y = 0; y < yLength / 5; ++y)
			{
				int val = input[x - xLength / 5][y] + 1;
				input[x][y] = val > 9 ? val - 9 : val;
			}
		}
		
		for(int x = 0; x < xLength; ++x)
		{
			for(int y = yLength/5; y < yLength; ++y)
			{
				int val = input[x][y - yLength / 5] + 1;
				input[x][y] = val > 9 ? val - 9 : val;
			}
		}
		
		// printArray(input);
		
		int[][] distances = new int[xLength][yLength];
		
		for(int x = 0; x < xLength; ++x)
		{
		    for(int y = 0; y < yLength; ++y)
			{
			    distances[x][y] = Integer.MAX_VALUE;
			}
		}
		
		distances[0][0] = 0;
		
		int x = 0;
		int y = 0;
		
		boolean[][] visitedNodes = new boolean[xLength][yLength];
		
		while(true)
		{
			calculateDistances(input, distances, x, y, xLength, yLength);
			visitedNodes[x][y] = true;
			int[] newNode = findNextNodeToVisit(visitedNodes, distances, xLength, yLength);
			x = newNode[0];
			y = newNode[1];
			if(allNodesVisited(visitedNodes, xLength, yLength))
			{
				break;
			}
			
		}
		
		StdOut.println(distances[xLength - 1][yLength - 1]);
	}
	
	public static boolean allNodesVisited(boolean[][] visitedNodes, int xLength, int yLength)
	{
		for(int x = 0; x < xLength; ++x)
		{
			for(int y = 0; y < yLength; ++y)
			{
				if(!visitedNodes[x][y])
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void printArray(int[][] array)
	{
		for(int y = 0; y < array[0].length; ++y)
		{
			for(int x = 0; x < array.length; ++x)
			{
				StdOut.print(array[x][y] + " ");
			}
			StdOut.println("");
		}
	}
	
	public static int[] findNextNodeToVisit(boolean[][] visitedNodes, int[][] distances, int xLength, int yLength)
	{
		int[] output = new int[2];
		int value = Integer.MAX_VALUE;
		
		for(int x = 0; x < xLength; ++x)
		{
			for(int y = 0; y < yLength; ++y)
			{
				if(!visitedNodes[x][y] && distances[x][y] < value)
				{
					value = distances[x][y];
					output[0] = x;
					output[1] = y;
				}
			}
		}
		
		return output;
	}
	
	
	public static void calculateDistances(int[][] input, int[][] distances, int x, int y, int xLength, int yLength)
	{
	    if(x - 1 >= 0)
		{
		    distances[x - 1][y] = Math.min(distances[x - 1][y], distances[x][y] + input[x - 1][y]);
		}
		
		if(x + 1 < xLength)
		{
		    distances[x + 1][y] = Math.min(distances[x + 1][y], distances[x][y] + input[x + 1][y]);
		}
		
		if(y - 1 >= 0)
		{
		    distances[x][y - 1] = Math.min(distances[x][y - 1], distances[x][y] + input[x][y - 1]);
		}
		
		if(y + 1 < yLength)
		{
		    distances[x][y + 1] = Math.min(distances[x][y + 1], distances[x][y] + input[x][y + 1]);
		}
	}
}