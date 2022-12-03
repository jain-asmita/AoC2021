import java.util.HashMap;
import java.util.HashSet;

public class Day5
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int nLines = input_byLine.length;
		HashSet<HashMap<String, Integer>> input = new HashSet<HashMap<String, Integer>>();
		int gridLength = 1000;
		
		for(int line = 0; line < nLines; ++line)
		{
			HashMap<String, Integer> lineData = new HashMap<String, Integer>();
			int x1 = Integer.parseInt(input_byLine[line].split(" -> ")[0].split(",")[0]);
			int y1 = Integer.parseInt(input_byLine[line].split(" -> ")[0].split(",")[1]);
			int x2 = Integer.parseInt(input_byLine[line].split(" -> ")[1].split(",")[0]);
			int y2 = Integer.parseInt(input_byLine[line].split(" -> ")[1].split(",")[1]);
			lineData.put("x1", x1);
			lineData.put("x2", x2);
			lineData.put("y1", y1);
			lineData.put("y2", y2);
			input.add(lineData);
		}
		
		int[][] grid = new int[gridLength][gridLength];
		for (HashMap<String, Integer> line : input)
		{
			String xStart = (line.get("x2") >= line.get("x1")) ? "x1" : "x2";
			String xEnd = (xStart.equals("x1")) ? "x2" : "x1";
			String yStart = (line.get("y2") >= line.get("y1")) ? "y1" : "y2";
			String yEnd = (yStart.equals("y1")) ? "y2" : "y1";
			
			if(line.get("x2") - line.get("x1")  == 0 || line.get("y1") - line.get("y2") == 0)
			{
				for(int x = line.get(xStart); x <= line.get(xEnd); ++x)
			    {
			    	for(int y = line.get(yStart); y <= line.get(yEnd); ++y)
			    	{
			    		grid[x][y] += 1;
			    	}
			    }
			}
			else
			{
				if(line.get("x1") - line.get("x2") >= 0 && line.get("y1") - line.get("y2") <= 0)
				{
					for(int x = line.get("x1"), y = line.get("y1"); x >= line.get("x2") && y <= line.get("y2"); --x, ++y)
					{
						grid[x][y] += 1;
					}
				}
                else if(line.get("x1") - line.get("x2") <= 0 && line.get("y1") - line.get("y2") <= 0)
				{
					for(int x = line.get("x1"), y = line.get("y1"); x <= line.get("x2") && y <= line.get("y2"); ++x, ++y)
					{
						grid[x][y] += 1;
					}
                }
                else if(line.get("x1") - line.get("x2") >= 0 && line.get("y1") - line.get("y2") >= 0)
                {
					for(int x = line.get("x1"), y = line.get("y1"); x >= line.get("x2") && y >= line.get("y2"); --x, --y)
					{
						grid[x][y] += 1;
					}
                }
                else
				{
					for(int x = line.get("x1"), y = line.get("y1"); x <= line.get("x2") && y >= line.get("y2"); ++x, --y)
					{
						grid[x][y] += 1;
					}
                }
			}
		}
		
		StdOut.println(countOverlaps(grid, gridLength));		
	}
	
	public static int countOverlaps(int[][] grid, int gridLength)
	{
		int overlaps = 0;
		for(int x = 0; x < gridLength; ++x)
		{
			for(int y = 0; y < gridLength; ++y)
			{
				if(grid[x][y] > 1)
				{
					++overlaps;
				}
			}
		}
		return overlaps;
	}
	
	public static void print2DIntArray(int[][] array, int gridLength)
	{
		for(int x = 0; x < gridLength; ++x)
		{
			for(int y = 0; y < gridLength; ++y)
			{
				StdOut.print(array[y][x] + " ");
			}
			StdOut.println("");
		}
		StdOut.println("");
	}
}