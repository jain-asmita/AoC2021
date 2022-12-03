public class Day15
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int xLength = input_byLine[0].length();
		int yLength = input_byLine.length;
		int[][] input = new int[xLength][yLength];
		
		StdOut.println(xLength);
		StdOut.println(yLength);
		
		for(int x = 0; x < xLength; ++x)
		{
			for(int y = 0; y < yLength; ++y)
			{
				input[x][y] = Integer.parseInt(input_byLine[y].substring(x, x + 1));
			}
		}
		
		int[][] lowestPath = new int[xLength][yLength];
		for(int x = 0; x < xLength; ++x)
		{
			for(int y = 0; y < yLength; ++y)
			{
				lowestPath[x][y] = findLowestPath(input, lowestPath, x, y, xLength, yLength);
			}
		}
		
		// int[][] secondPass = new int[xLength][yLength];
		// for(int x = 0; x < xLength; ++x)
		// {
		// 	for(int y = 0; y < yLength; ++y)
		// 	{
		// 		secondPass[x][y] = findLowestPathSecondPass(input, lowestPath, x, y, xLength, yLength);
		// 	}
		// }
		
		StdOut.println(lowestPath[xLength - 1][yLength - 1]);
		
		// printArray(lowestPath);
		
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
	
	public static int findLowestPath(int[][] input, int[][] lowestPath, int x, int y, int xLength, int yLength)
	{
		int minPath = Integer.MAX_VALUE;
		if(x == 0 && y == 0)
		{
			return 0;
		}
		
		if(x - 1 >= 0)
		{
			minPath = Math.min(minPath, lowestPath[x - 1][y] + input[x][y]);
		}
		
		if(y - 1 >= 0)
		{
			minPath = Math.min(minPath, lowestPath[x][y - 1] + input[x][y]);
		}
		
		return minPath;
	}
}