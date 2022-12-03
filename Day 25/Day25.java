public class Day25
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int yLength = input_byLine.length;
		int xLength = input_byLine[0].length();
		String[][] input = new String[xLength][yLength];
		
		for(int y = 0; y < yLength; ++y)
		{
		    for(int x = 0; x < xLength; ++x)
			{
			    input[x][y] = input_byLine[y].substring(x, x + 1);
			}
		}
		
		int countSteps = 0;
		while(true)
		{
			//printArray(input, xLength, yLength);
			//StdOut.println("");
		    ++countSteps;
			//StdOut.println(countSteps);
			int stepsCount = simulateStep(input, xLength, yLength);
			if(stepsCount == 0)
			{
			    break;
			}
		}
		StdOut.println(countSteps);
	}
	
	public static int simulateStep(String[][] input, int xLength, int yLength)
	{
	    int countMoves = 0;
	    String[][] inputCopy = new String[xLength][yLength];
		for(int x = 0; x < xLength; ++x)
		{
		    for(int y = 0; y < yLength; ++y)
			{
			    inputCopy[x][y] = input[x][y];
			}
		}
		
	    for(int x = 0; x < xLength; x++)
		{
		    for(int y = 0; y < yLength; ++y)
			{
			    if(inputCopy[x][y].equals(">"))
				{
				    if(inputCopy[(x + 1) % xLength][y].equals("."))
					{
					    input[x][y] = ".";
						input[(x + 1) % xLength][y] = ">";
						countMoves++;
					}
				}
			}
		}
		
		for(int x = 0; x < xLength; ++x)
		{
		    for(int y = 0; y < yLength; ++y)
			{
			    inputCopy[x][y] = input[x][y];
			}
		}
		
		for(int x = 0; x < xLength; x++)
		{
		    for(int y = 0; y < yLength; ++y)
			{
			    if(inputCopy[x][y].equals("v"))
				{
				    if(inputCopy[x][(y+1)%yLength].equals("."))
					{
					    input[x][y] = ".";
						input[x][(y+1)%yLength] = "v";
						countMoves++;
					}
				}
			}
		}
		
		return countMoves;
	}
	
	public static void printArray(String[][] input, int xLength, int yLength)
	{
		for(int y = 0; y < yLength; ++y)
		{
			for(int x = 0; x < xLength; ++x)
			{
				StdOut.print(input[x][y]);
			}
			StdOut.println("");
		}
	}
	
}