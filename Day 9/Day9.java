import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

public class Day9
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int xLength = input_byLine[0].length();
		int yLength = input_byLine.length;
		int[][] input = new int[xLength][yLength];
		HashSet<HashMap<String, Integer>> basinCoords = new HashSet<HashMap<String, Integer>>();
		
		
		for(int x = 0; x < xLength; ++x)
		{
			for(int y = 0; y < yLength; ++y)
			{
				input[x][y] = Integer.parseInt(input_byLine[y].substring(x, x + 1));
			}
		}
		
		int silverAnswer = 0;
		
		for(int x = 0; x < xLength; ++x)
		{
			for(int y = 0; y < yLength; ++y)
			{
				// StdOut.println(x + " " + y);
				if (check(x, y, input))
				{
					HashMap<String, Integer> coords = new HashMap<String, Integer>();
					coords.put("x", x);
					coords.put("y", y);
					basinCoords.add(coords);
					silverAnswer = silverAnswer + input[x][y] + 1;
					// StdOut.println("me");
				}
			}
		}
		
		int[] basinLengths = new int[basinCoords.size()];
		int counter = 0;
		
		for(HashMap<String, Integer> coord : basinCoords)
		{
			int[] basinLength = new int[1];
			findBasinSize(input, new boolean[xLength][yLength], coord.get("x"), coord.get("y"), xLength, yLength, basinLength);
			basinLengths[counter] = basinLength[0];
			counter++;
		}
		
		sortArray(basinLengths);
		StdOut.println(basinLengths[basinCoords.size() - 1] * basinLengths[basinCoords.size() - 2] * basinLengths[basinCoords.size() - 3]);
		
		
	}
	
	public static boolean check(int x, int y, int[][] input)
	{
		int xLength = input.length - 1;
		int yLength = input[0].length - 1;
		
		if (x == 0 && y == 0)
		{
			return (input[x][y] < input[x + 1][y] && input[x][y] < input[x][y + 1]);		
		}
		else if (x == xLength && y == 0)
		{
			return (input[x][y] < input[x - 1][y] && input[x][y] < input[x][y + 1]);
		}
		else if (x == 0 && y == yLength)
		{
			return (input[x][y] < input[x][y - 1] && input[x][y] < input[x + 1][y]);
		}
		else if (x == xLength && y == yLength)
		{
			return (input[x][y] < input[x][y - 1] && input[x][y] < input[x - 1][y]);
		}
		else if (x == 0)
		{
			return (input[x][y] < input[x][y - 1] && input[x][y] < input[x][y + 1] && input[x][y] < input[x + 1][y]);
		}
		else if (y == 0)
		{
			return (input[x][y] < input[x - 1][y] && input[x][y] < input[x][y + 1] && input[x][y] < input[x + 1][y]);
		}
		else if (x == xLength)
		{
			return (input[x][y] < input[x - 1][y] && input[x][y] < input[x][y + 1] && input[x][y] < input[x][y - 1]);
		}
		else if (y == yLength)
		{
			return (input[x][y] < input[x][y - 1] && input[x][y] < input[x + 1][y] && input[x][y] < input[x - 1][y]);
		}
		else
		{
			return (input[x][y] < input[x][y - 1] && input[x][y] < input[x + 1][y] && input[x][y] < input[x - 1][y] && input[x][y] < input[x][y + 1]);
		}
	}
	
	public static void findBasinSize(int[][] input, boolean[][] haveChecked, int xCoord, int yCoord, int xLength, int yLength, int[] size)
	{
		if(xCoord >= xLength) return;
		if(yCoord >= yLength) return;
		if(xCoord < 0) return;
		if(yCoord < 0) return;
		if(haveChecked[xCoord][yCoord]) return;
		haveChecked[xCoord][yCoord] = true;
		
		if(input[xCoord][yCoord] == 9)
		{
			return;
		}
		else
		{
			size[0] += 1;
		}
		findBasinSize(input, haveChecked, xCoord + 1, yCoord, xLength, yLength, size);
		findBasinSize(input, haveChecked, xCoord - 1, yCoord, xLength, yLength, size);
		findBasinSize(input, haveChecked, xCoord, yCoord + 1, xLength, yLength, size);
		findBasinSize(input, haveChecked, xCoord, yCoord - 1, xLength, yLength, size);
		return;
	}
	
	public static void sortArray(int[] array)
	{
	    int temp;
        for (int i = 1; i < array.length; i++)
	    {
            for (int j = i; j > 0; j--)
		    {
                if (array[j] < array [j - 1])
			    {
                   temp = array[j];
                   array[j] = array[j - 1];
                   array[j - 1] = temp;
                }
	        }
		}
	}
}