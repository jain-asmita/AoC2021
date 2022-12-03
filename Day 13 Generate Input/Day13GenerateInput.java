import java.util.HashMap;
import java.util.HashSet;
import java.lang.Math;

public class Day13GenerateInput
{
	public static void main(String[] args)
	{
		String allInput = StdIn.readAll();
	    String[] instructionsInput = allInput.split("\r\n\r\n");
	    String[] instructions = instructionsInput[0].split("\r\n");
	    String[] inputByLine = instructionsInput[1].split("\r\n");
	    int xGridLength = inputByLine[0].length();
	    int yGridLength = inputByLine.length;
	    
	    HashSet<HashMap<String, Integer>> initialGrid = new HashSet<HashMap<String, Integer>>();
	    
	    for(int x = 0; x < xGridLength; ++x)
	    {
	        for(int y = 0; y < yGridLength; ++y)
	    	{
	    	    if(inputByLine[y].substring(x, x + 1).equals("#"))
	    		{
	    		    HashMap<String, Integer> map = new HashMap<String, Integer>();
	    			map.put("x", x);
	    			map.put("y", y);
	    			initialGrid.add(map);
	    		}
	    	}
	    }
		
		int[] foldCoordinate = new int[instructions.length];
	    
	    for (int line = 0; line < instructions.length; ++line)
		{
			String notFoldAxis = instructions[line].equals("x") ? "y" : "x";
			HashSet<HashMap<String, Integer>> newGrid = fold(initialGrid, instructions[line], notFoldAxis);
			initialGrid = newGrid;
			foldCoordinate[line] = maxValue(newGrid, instructions[line]) / 2;
		}
		
		for(HashMap<String, Integer> map : initialGrid)
		{
			StdOut.println(map.get("x") + "," + map.get("y"));
		}
		
		for(int i = instructions.length - 1; i >= 0; --i)
		{
			StdOut.println("fold along " + instructions[i] + "=" + foldCoordinate[i]);
		}
		
	}
	
	public static HashSet<HashMap<String, Integer>> fold(HashSet<HashMap<String, Integer>> grid, String foldAxis, String notFoldAxis)
	{
		int foldAxisMax = maxValue(grid, foldAxis);
		//StdOut.println(foldAxisMax);
		int foldAxisMin = minValue(grid, foldAxis);
		//StdOut.println(foldAxisMin);
		int notFoldAxisMax = maxValue(grid, notFoldAxis);
		//StdOut.println(notFoldAxisMax);
		int notFoldAxisMin = minValue(grid, notFoldAxis);
		//StdOut.println(notFoldAxisMin);
		
		HashSet<HashMap<String, Integer>> newGrid = new HashSet<HashMap<String, Integer>>();
		
		for(HashMap<String, Integer> map : grid)
		{
			boolean originalKeepsData = rand();
			boolean bothKeepData = false;
			boolean isExtremity = map.get(foldAxis) == foldAxisMax || map.get(foldAxis) == foldAxisMin || map.get(notFoldAxis) == notFoldAxisMax || map.get(notFoldAxis) == notFoldAxisMin;
			
			if(bothKeepData || originalKeepsData || isExtremity)
			{
				HashMap<String, Integer> newPoint = new HashMap<String, Integer>();
				newPoint.put(notFoldAxis, map.get(notFoldAxis));
				newPoint.put(foldAxis, map.get(foldAxis));
				newGrid.add(newPoint);
			}
			
			if(bothKeepData || !originalKeepsData || map.get(foldAxis).equals(foldAxisMax) || isExtremity)
			{
				HashMap<String, Integer> newPoint = new HashMap<String, Integer>();
				newPoint.put(notFoldAxis, map.get(notFoldAxis));
				newPoint.put(foldAxis, (foldAxisMax*2) + 2 - map.get(foldAxis));
				newGrid.add(newPoint);
			}
		}
		return newGrid;
	}
	
	public static int maxValue(HashSet<HashMap<String, Integer>> grid, String key)
	{
		int max = 0;
		
		for(HashMap<String, Integer> map : grid)
		{
			if(map.get(key) > max)
			{
				max = map.get(key);
			}
		}
		
		return max;
	}
	
	public static int minValue(HashSet<HashMap<String, Integer>> grid, String key)
	{
		int min = Integer.MAX_VALUE;
		
		for(HashMap<String, Integer> map : grid)
		{
			if(map.get(key) < min)
			{
				min = map.get(key);
			}
		}
		
		return min;
	}
	
	public static boolean rand()
	{
		return Math.random() < 0.5;
	}
}