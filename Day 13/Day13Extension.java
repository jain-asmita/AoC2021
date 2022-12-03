import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class Day13Extension
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		long startTime = System.nanoTime();
		String[] input_byLine = allInput.split("\r\n");
		int nLines = input_byLine.length;
		
		HashSet<HashMap<String, Long>> points = new HashSet<HashMap<String, Long>>();
		ArrayList<String> directionList = new ArrayList<String>();
		ArrayList<Long> foldCoordinateList = new ArrayList<Long>();
		
		for(int line = 0; line < nLines; ++line)
		{
		    if(input_byLine[line].contains("fold"))
			{
			    String direction = input_byLine[line].substring(11, 12);
				long coordinate = Long.parseLong(input_byLine[line].split("=")[1]);
				directionList.add(direction);
                foldCoordinateList.add(coordinate);
			}
			else
			{
			    String[] lineSplit = input_byLine[line].split(",");
				long x = Long.parseLong(lineSplit[0]);
				long y = Long.parseLong(lineSplit[1]);
				
				HashMap<String, Long> point = new HashMap<String, Long>();
				point.put("x", x);
				point.put("y", y);
				points.add(point);
			}
		}
		
		int nInstructions = directionList.size();
		for(int i = 0; i < nInstructions; ++i)
		{				
			if(directionList.get(i).equals("x"))
			{
				HashSet<HashMap<String, Long>> newPoints = createNewMapXFold(points, foldCoordinateList.get(i));
				points = newPoints;
			}
			else
			{
				HashSet<HashMap<String, Long>> newPoints = createNewMapYFold(points, foldCoordinateList.get(i));
				points = newPoints;
			}
			
		}
		
		int xMax = findMax(points, "x");
		int yMax = findMax(points, "y");
		
		long[][] grid = new long[xMax + 1][yMax + 1];
		
		for(HashMap<String, Long> point: points)
		{
			grid[Math.toIntExact(point.get("x"))][Math.toIntExact(point.get("y"))] = 1;
		}
		
		printArray(grid);
		
		long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime * Math.pow(10, -9));

	}
	
	public static void printArray(long[][] array)
	{
		for(int y = 0; y < array[0].length; ++y)
		{
			for(int x = 0; x < array.length; ++x)
			{
				StdOut.print(array[x][y] > 0 ? "#" : " ");
			}
			StdOut.println("");
		}
	}

	
	public static HashSet<HashMap<String, Long>> createNewMapXFold(HashSet<HashMap<String, Long>> points, long foldCoordinate)
	{
		HashSet<HashMap<String, Long>> newPoints = new HashSet<HashMap<String, Long>>();

		for(HashMap<String, Long> map : points)
		{
            HashMap<String, Long> newPoint = new HashMap<String, Long>();
			if(map.get("x") < foldCoordinate)
			{
				newPoints.add(map);
			}
			else
			{
				newPoint.put("x", foldCoordinate - (map.get("x") - foldCoordinate));
			    newPoint.put("y", map.get("y"));
                newPoints.add(newPoint);
			}
			
		}
		
		return newPoints;
	}
	
	public static HashSet<HashMap<String, Long>> createNewMapYFold(HashSet<HashMap<String, Long>> points, long foldCoordinate)
	{
		HashSet<HashMap<String, Long>> newPoints = new HashSet<HashMap<String, Long>>();

		for(HashMap<String, Long> map : points)
		{
            HashMap<String, Long> newPoint = new HashMap<String, Long>();
			if(map.get("y") < foldCoordinate)
			{
				newPoints.add(map);
			}
			else
			{
				newPoint.put("y", foldCoordinate - (map.get("y") - foldCoordinate));
			    newPoint.put("x", map.get("x"));
                newPoints.add(newPoint);
			}
			
		}
		
		return newPoints;
	}
	
	
	public static int findMax(HashSet<HashMap<String, Long>> points, String direction)
	{
		int maxValue = 0;
		for(HashMap<String, Long> point: points)
		{
			if(point.get(direction) > maxValue)
			{
				maxValue = Math.toIntExact(point.get(direction));
			}
		}
		return maxValue;
	}
}