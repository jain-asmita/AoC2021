import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class Day22Part2
{
    public static void main(String[] args)
	{
		long startTime = System.nanoTime();
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int nLines = input_byLine.length;
		ArrayList<Boolean> instruction = new ArrayList<Boolean>();
		ArrayList<HashMap<String, Long>> coordinates = new ArrayList<HashMap<String, Long>>();
		
		for(int i = 0; i < nLines; ++i)
		{
		    String[] firstSplit = input_byLine[i].split(" ");
			instruction.add((firstSplit[0].equals("on")) ? true : false);
			String[] secondSplit = firstSplit[1].split("=");
			long xStart = Long.parseLong(secondSplit[1].split("\\.\\.")[0]);
			long xEnd = Long.parseLong(secondSplit[1].split("\\.\\.")[1].split(",")[0]);
			long yStart = Long.parseLong(secondSplit[2].split("\\.\\.")[0]);
			long yEnd = Long.parseLong(secondSplit[2].split("\\.\\.")[1].split(",")[0]);
			long zStart = Long.parseLong(secondSplit[3].split("\\.\\.")[0]);
			long zEnd = Long.parseLong(secondSplit[3].split("\\.\\.")[1].split(",")[0]);
			HashMap<String, Long> coordinate = new HashMap<String, Long>();
			coordinate.put("xStart", xStart);
			coordinate.put("xEnd", xEnd);
			coordinate.put("yStart", yStart);
			coordinate.put("yEnd", yEnd);
			coordinate.put("zStart", zStart);
			coordinate.put("zEnd", zEnd);
			coordinates.add(coordinate);
		}
		
		ArrayList<HashMap<String, Long>> distinctCubes = new ArrayList<HashMap<String, Long>>();
		
		for(int i = 0; i < nLines; ++i)
		{
			HashMap<String, Long> currentCube = coordinates.get(i);
			boolean currentInstruction = instruction.get(i);
			distinctCubes = findIntersections(distinctCubes, currentCube, currentInstruction);
			//StdOut.println(distinctCubes);
		}
		StdOut.println(calculateTotalCubes(distinctCubes));
		long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime * Math.pow(10, -9));
		
	}
	
	public static long calculateTotalCubes(ArrayList<HashMap<String, Long>> cubes)
	{
		long answer = (long) 0;
		int nCubes = cubes.size();
		for(int i = 0; i < nCubes; ++i)
		{
			HashMap<String, Long> cube = cubes.get(i);
			answer += Math.max(((cube.get("xEnd") - cube.get("xStart") + 1) * (cube.get("yEnd") - cube.get("yStart") + 1) * (cube.get("zEnd") - cube.get("zStart") + 1)), 0);
		}
		return answer;
	}
	
	public static ArrayList<HashMap<String, Long>> findIntersections(ArrayList<HashMap<String, Long>> distinctCubes, HashMap<String, Long> cube, boolean isOn)
	{
		int nDistinctCubes = distinctCubes.size();
		ArrayList<HashMap<String, Long>> distinctCubesCopy = new ArrayList<HashMap<String, Long>>();
		distinctCubesCopy.addAll(distinctCubes);
		for(int cubeN = 0; cubeN < nDistinctCubes; ++cubeN)
		{
			HashMap<String, Long> outerCube = distinctCubes.get(cubeN);
	        if(intersects(cube, outerCube))
			{
				HashMap<String, Long> innerCube = findOverlap(cube, outerCube);
				HashMap<String, Long> cube1 = new HashMap<String, Long>();
				cube1.put("xStart", outerCube.get("xStart"));
				cube1.put("xEnd", outerCube.get("xEnd"));
				cube1.put("yStart", outerCube.get("yStart"));
				cube1.put("yEnd", innerCube.get("yStart") - 1);
				cube1.put("zStart", outerCube.get("zStart"));
				cube1.put("zEnd", outerCube.get("zEnd"));
				if(isCubeLegit(cube1))
				{
					distinctCubesCopy.add(cube1);
				}
				
				HashMap<String, Long> cube2 = new HashMap<String, Long>();
				cube2.put("xStart", outerCube.get("xStart"));
				cube2.put("xEnd", outerCube.get("xEnd"));
				cube2.put("yStart", innerCube.get("yEnd") + 1);
				cube2.put("yEnd", outerCube.get("yEnd"));
				cube2.put("zStart", outerCube.get("zStart"));
				cube2.put("zEnd", outerCube.get("zEnd"));
				if(isCubeLegit(cube2))
				{
					distinctCubesCopy.add(cube2);
				}
				
				HashMap<String, Long> cube3 = new HashMap<String, Long>();
				cube3.put("xStart", outerCube.get("xStart"));
				cube3.put("xEnd", outerCube.get("xEnd"));
				cube3.put("yStart", innerCube.get("yStart"));
				cube3.put("yEnd", innerCube.get("yEnd"));
				cube3.put("zStart", outerCube.get("zStart"));
				cube3.put("zEnd", innerCube.get("zStart") - 1);
				if(isCubeLegit(cube3))
				{
					distinctCubesCopy.add(cube3);
				}
				
				HashMap<String, Long> cube4 = new HashMap<String, Long>();
				cube4.put("xStart", outerCube.get("xStart"));
				cube4.put("xEnd", outerCube.get("xEnd"));
				cube4.put("yStart", innerCube.get("yStart"));
				cube4.put("yEnd", innerCube.get("yEnd"));
				cube4.put("zStart", innerCube.get("zEnd") + 1);
				cube4.put("zEnd", outerCube.get("zEnd"));
				if(isCubeLegit(cube4))
				{
					distinctCubesCopy.add(cube4);
				}
				
				HashMap<String, Long> cube5 = new HashMap<String, Long>();
				cube5.put("xStart", innerCube.get("xEnd") + 1);
				cube5.put("xEnd", outerCube.get("xEnd"));
				cube5.put("yStart", innerCube.get("yStart"));
				cube5.put("yEnd", innerCube.get("yEnd"));
				cube5.put("zStart", innerCube.get("zStart"));
				cube5.put("zEnd", innerCube.get("zEnd"));
				if(isCubeLegit(cube5))
				{
					distinctCubesCopy.add(cube5);
				}
				
				HashMap<String, Long> cube6 = new HashMap<String, Long>();
				cube6.put("xStart", outerCube.get("xStart"));
				cube6.put("xEnd", innerCube.get("xStart") - 1);
				cube6.put("yStart", innerCube.get("yStart"));
				cube6.put("yEnd", innerCube.get("yEnd"));
				cube6.put("zStart", innerCube.get("zStart"));
				cube6.put("zEnd", innerCube.get("zEnd"));
				if(isCubeLegit(cube6))
				{
					distinctCubesCopy.add(cube6);
				}

				distinctCubesCopy.remove(outerCube);
			}
		}
		if(isOn)
		{
			distinctCubesCopy.add(cube);
		}
		
		return distinctCubesCopy;
	}
	
	public static HashMap<String, Long> findOverlap(HashMap<String, Long> cube1, HashMap<String, Long> cube2)
	{
		long xStart = Math.max(cube1.get("xStart"), cube2.get("xStart"));
		long xEnd = Math.min(cube1.get("xEnd"), cube2.get("xEnd"));
		long yStart = Math.max(cube1.get("yStart"), cube2.get("yStart"));
		long yEnd = Math.min(cube1.get("yEnd"), cube2.get("yEnd"));
		long zStart = Math.max(cube1.get("zStart"), cube2.get("zStart"));
		long zEnd = Math.min(cube1.get("zEnd"), cube2.get("zEnd"));
		
		HashMap<String, Long> overlap = new HashMap<String, Long>();
		overlap.put("xStart", xStart);
		overlap.put("xEnd", xEnd);
		overlap.put("yStart", yStart);
		overlap.put("yEnd", yEnd);
		overlap.put("zStart", zStart);
		overlap.put("zEnd", zEnd);
		
		return overlap;
	}
	
	public static boolean intersects(HashMap<String, Long> cube1, HashMap<String, Long> cube2)
	{
		if(!(cube1.get("xEnd") < cube2.get("xStart"))
			&& !(cube2.get("xEnd") < cube1.get("xStart"))
		    && !(cube1.get("zEnd") < cube2.get("zStart"))
			&& !(cube2.get("zEnd") < cube1.get("zStart"))
			&& !(cube1.get("yEnd") < cube2.get("yStart"))
			&& !(cube2.get("yEnd") < cube1.get("yStart")))
			{
				return true;
			}
		return false;
	}
	
	public static boolean isCubeLegit(HashMap<String, Long> cube)
	{
		if(cube.get("xEnd") < cube.get("xStart"))
		{
			return false;
		}
		if(cube.get("yEnd") < cube.get("yStart"))
		{
			return false;
		}
		if(cube.get("zEnd") < cube.get("zStart"))
		{
			return false;
		}
		
		return true;
	}
}