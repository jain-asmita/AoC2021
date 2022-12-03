import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class Day22Part2Try2
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
		// StdOut.println("hi " + distinctCubesCopy);
		// StdOut.println("hi1 " + distinctCubes);
		for(int cubeN = 0; cubeN < nDistinctCubes; ++cubeN)
		{
			// StdOut.println(cubeN);
			HashMap<String, Long> outerCube = distinctCubes.get(cubeN);
	        if(intersects(cube, outerCube))
			{
				HashMap<String, Long> innerCube = findOverlap(cube, outerCube);
				// StdOut.println("overlap " + innerCube);
				HashMap<String, Long> cube1 = new HashMap<String, Long>();
				cube1.put("xStart", outerCube.get("xStart"));
				cube1.put("xEnd", innerCube.get("xStart") - 1);
				cube1.put("yStart", innerCube.get("yEnd") + 1);
				cube1.put("yEnd", outerCube.get("yEnd"));
				cube1.put("zStart", innerCube.get("zEnd") + 1);
				cube1.put("zEnd", outerCube.get("zEnd"));
				if(isCubeLegit(cube1))
				{
					distinctCubesCopy.add(cube1);
				}
				
				HashMap<String, Long> cube2 = new HashMap<String, Long>();
				cube2.put("xStart", innerCube.get("xStart"));
				cube2.put("xEnd", innerCube.get("xEnd"));
				cube2.put("yStart", innerCube.get("yEnd") + 1);
				cube2.put("yEnd", outerCube.get("yEnd"));
				cube2.put("zStart", innerCube.get("zEnd") + 1);
				cube2.put("zEnd", outerCube.get("zEnd"));
				if(isCubeLegit(cube2))
				{
					distinctCubesCopy.add(cube2);
				}
				
				HashMap<String, Long> cube3 = new HashMap<String, Long>();
				cube3.put("xStart", innerCube.get("xEnd") + 1);
				cube3.put("xEnd", outerCube.get("xEnd"));
				cube3.put("yStart", innerCube.get("yEnd") + 1);
				cube3.put("yEnd", outerCube.get("yEnd"));
				cube3.put("zStart", innerCube.get("zEnd") + 1);
				cube3.put("zEnd", outerCube.get("zEnd"));
				if(isCubeLegit(cube3))
				{
					distinctCubesCopy.add(cube3);
				}
				
				HashMap<String, Long> cube4 = new HashMap<String, Long>();
				cube4.put("xStart", outerCube.get("xStart"));
				cube4.put("xEnd", innerCube.get("xStart") - 1);
				cube4.put("yStart", innerCube.get("yEnd") + 1);
				cube4.put("yEnd", outerCube.get("yEnd"));
				cube4.put("zStart", innerCube.get("zStart"));
				cube4.put("zEnd", innerCube.get("zEnd"));
				if(isCubeLegit(cube4))
				{
					distinctCubesCopy.add(cube4);
				}
				
				HashMap<String, Long> cube5 = new HashMap<String, Long>();
				cube5.put("xStart", innerCube.get("xStart"));
				cube5.put("xEnd", innerCube.get("xEnd"));
				cube5.put("yStart", innerCube.get("yEnd") + 1);
				cube5.put("yEnd", outerCube.get("yEnd"));
				cube5.put("zStart", innerCube.get("zStart"));
				cube5.put("zEnd", innerCube.get("zEnd"));
				if(isCubeLegit(cube5))
				{
					distinctCubesCopy.add(cube5);
				}
				
				HashMap<String, Long> cube6 = new HashMap<String, Long>();
				cube6.put("xStart", innerCube.get("xEnd") + 1);
				cube6.put("xEnd", outerCube.get("xEnd"));
				cube6.put("yStart", innerCube.get("yEnd") + 1);
				cube6.put("yEnd", outerCube.get("yEnd"));
				cube6.put("zStart", innerCube.get("zStart"));
				cube6.put("zEnd", innerCube.get("zEnd"));
				if(isCubeLegit(cube6))
				{
					distinctCubesCopy.add(cube6);
				}
				
				HashMap<String, Long> cube7 = new HashMap<String, Long>();
				cube7.put("xStart", outerCube.get("xStart"));
				cube7.put("xEnd", innerCube.get("xStart") - 1);
				cube7.put("yStart", innerCube.get("yEnd") + 1);
				cube7.put("yEnd", outerCube.get("yEnd"));
				cube7.put("zStart", outerCube.get("zStart"));
				cube7.put("zEnd", innerCube.get("zStart") - 1);
				if(isCubeLegit(cube7))
				{
					distinctCubesCopy.add(cube7);
				}
				
				HashMap<String, Long> cube8 = new HashMap<String, Long>();
				cube8.put("xStart", innerCube.get("xStart"));
				cube8.put("xEnd", innerCube.get("xEnd"));
				cube8.put("yStart", innerCube.get("yEnd") + 1);
				cube8.put("yEnd", outerCube.get("yEnd"));
				cube8.put("zStart", outerCube.get("zStart"));
				cube8.put("zEnd", innerCube.get("zStart") - 1);
				if(isCubeLegit(cube8))
				{
					distinctCubesCopy.add(cube8);
				}
				
				HashMap<String, Long> cube9 = new HashMap<String, Long>();
				cube9.put("xStart", innerCube.get("xEnd") + 1);
				cube9.put("xEnd", outerCube.get("xEnd"));
				cube9.put("yStart", innerCube.get("yEnd") + 1);
				cube9.put("yEnd", outerCube.get("yEnd"));
				cube9.put("zStart", outerCube.get("zStart"));
				cube9.put("zEnd", innerCube.get("zStart") - 1);
				if(isCubeLegit(cube9))
				{
					distinctCubesCopy.add(cube9);
				}
				
				HashMap<String, Long> cube10 = new HashMap<String, Long>();
				cube10.put("xStart", outerCube.get("xStart"));
				cube10.put("xEnd", innerCube.get("xStart") - 1);
				cube10.put("yStart", innerCube.get("yStart"));
				cube10.put("yEnd", innerCube.get("yEnd"));
				cube10.put("zStart", innerCube.get("zEnd") + 1);
				cube10.put("zEnd", outerCube.get("zEnd"));
				if(isCubeLegit(cube10))
				{
					distinctCubesCopy.add(cube10);
				}
				
				HashMap<String, Long> cube11 = new HashMap<String, Long>();
				cube11.put("xStart", innerCube.get("xStart"));
				cube11.put("xEnd", innerCube.get("xEnd"));
				cube11.put("yStart", innerCube.get("yStart"));
				cube11.put("yEnd", innerCube.get("yEnd"));
				cube11.put("zStart", innerCube.get("zEnd") + 1);
				cube11.put("zEnd", outerCube.get("zEnd"));
				if(isCubeLegit(cube11))
				{
					distinctCubesCopy.add(cube11);
				}
				
				HashMap<String, Long> cube12 = new HashMap<String, Long>();
				cube12.put("xStart", innerCube.get("xEnd") + 1);
				cube12.put("xEnd", outerCube.get("xEnd"));
				cube12.put("yStart", innerCube.get("yStart"));
				cube12.put("yEnd", innerCube.get("yEnd"));
				cube12.put("zStart", innerCube.get("zEnd") + 1);
				cube12.put("zEnd", outerCube.get("zEnd"));
				if(isCubeLegit(cube12))
				{
					distinctCubesCopy.add(cube12);
				}
				
				HashMap<String, Long> cube13 = new HashMap<String, Long>();
				cube13.put("xStart", outerCube.get("xStart"));
				cube13.put("xEnd", innerCube.get("xStart") - 1);
				cube13.put("yStart", innerCube.get("yStart"));
				cube13.put("yEnd", innerCube.get("yEnd"));
				cube13.put("zStart", innerCube.get("zStart"));
				cube13.put("zEnd", innerCube.get("zEnd"));
				if(isCubeLegit(cube13))
				{
					distinctCubesCopy.add(cube13);
				}
				
				HashMap<String, Long> cube14 = new HashMap<String, Long>();
				cube14.put("xStart", innerCube.get("xEnd") + 1);
				cube14.put("xEnd", outerCube.get("xEnd"));
				cube14.put("yStart", innerCube.get("yStart"));
				cube14.put("yEnd", innerCube.get("yEnd"));
				cube14.put("zStart", innerCube.get("zStart"));
				cube14.put("zEnd", innerCube.get("zEnd"));
				if(isCubeLegit(cube14))
				{
					distinctCubesCopy.add(cube14);
				}
				
				HashMap<String, Long> cube15 = new HashMap<String, Long>();
				cube15.put("xStart", outerCube.get("xStart"));
				cube15.put("xEnd", innerCube.get("xStart") - 1);
				cube15.put("yStart", innerCube.get("yStart"));
				cube15.put("yEnd", innerCube.get("yEnd"));
				cube15.put("zStart", outerCube.get("zStart"));
				cube15.put("zEnd", innerCube.get("zStart") - 1);
				if(isCubeLegit(cube15))
				{
					distinctCubesCopy.add(cube15);
				}
				
				HashMap<String, Long> cube16 = new HashMap<String, Long>();
				cube16.put("xStart", innerCube.get("xStart"));
				cube16.put("xEnd", innerCube.get("xEnd"));
				cube16.put("yStart", innerCube.get("yStart"));
				cube16.put("yEnd", innerCube.get("yEnd"));
				cube16.put("zStart", outerCube.get("zStart"));
				cube16.put("zEnd", innerCube.get("zStart") - 1);
				if(isCubeLegit(cube16))
				{
					distinctCubesCopy.add(cube16);
				}
				
				HashMap<String, Long> cube17 = new HashMap<String, Long>();
				cube17.put("xStart", innerCube.get("xEnd") + 1);
				cube17.put("xEnd", outerCube.get("xEnd"));
				cube17.put("yStart", innerCube.get("yStart"));
				cube17.put("yEnd", innerCube.get("yEnd"));
				cube17.put("zStart", outerCube.get("zStart"));
				cube17.put("zEnd", innerCube.get("zStart") - 1);
				if(isCubeLegit(cube17))
				{
					distinctCubesCopy.add(cube17);
				}
				
				HashMap<String, Long> cube18 = new HashMap<String, Long>();
				cube18.put("xStart", outerCube.get("xStart"));
				cube18.put("xEnd", innerCube.get("xStart") - 1);
				cube18.put("yStart", outerCube.get("yStart"));
				cube18.put("yEnd", innerCube.get("yStart") - 1);
				cube18.put("zStart", innerCube.get("zEnd") + 1);
				cube18.put("zEnd", outerCube.get("zEnd"));
				if(isCubeLegit(cube18))
				{
					distinctCubesCopy.add(cube18);
				}
				
				HashMap<String, Long> cube19 = new HashMap<String, Long>();
				cube19.put("xStart", innerCube.get("xStart"));
				cube19.put("xEnd", innerCube.get("xEnd"));
				cube19.put("yStart", outerCube.get("yStart"));
				cube19.put("yEnd", innerCube.get("yStart") - 1);
				cube19.put("zStart", innerCube.get("zEnd") + 1);
				cube19.put("zEnd", outerCube.get("zEnd"));
				if(isCubeLegit(cube19))
				{
					distinctCubesCopy.add(cube19);
				}
				
				HashMap<String, Long> cube20 = new HashMap<String, Long>();
				cube20.put("xStart", innerCube.get("xEnd") + 1);
				cube20.put("xEnd", outerCube.get("xEnd"));
				cube20.put("yStart", outerCube.get("yStart"));
				cube20.put("yEnd", innerCube.get("yStart") - 1);
				cube20.put("zStart", innerCube.get("zEnd") + 1);
				cube20.put("zEnd", outerCube.get("zEnd"));
				if(isCubeLegit(cube20))
				{
					distinctCubesCopy.add(cube20);
				}
				
				HashMap<String, Long> cube21 = new HashMap<String, Long>();
				cube21.put("xStart", outerCube.get("xStart"));
				cube21.put("xEnd", innerCube.get("xStart") - 1);
				cube21.put("yStart", outerCube.get("yStart"));
				cube21.put("yEnd", innerCube.get("yStart") - 1);
				cube21.put("zStart", innerCube.get("zStart"));
				cube21.put("zEnd", innerCube.get("zEnd"));
				if(isCubeLegit(cube21))
				{
					distinctCubesCopy.add(cube21);
				}
				
				HashMap<String, Long> cube22 = new HashMap<String, Long>();
				cube22.put("xStart", innerCube.get("xStart"));
				cube22.put("xEnd", innerCube.get("xEnd"));
				cube22.put("yStart", outerCube.get("yStart"));
				cube22.put("yEnd", innerCube.get("yStart") - 1);
				cube22.put("zStart", innerCube.get("zStart"));
				cube22.put("zEnd", innerCube.get("zEnd"));
				if(isCubeLegit(cube22))
				{
					distinctCubesCopy.add(cube22);
				}
				
				HashMap<String, Long> cube23 = new HashMap<String, Long>();
				cube23.put("xStart", innerCube.get("xEnd") + 1);
				cube23.put("xEnd", outerCube.get("xEnd"));
				cube23.put("yStart", outerCube.get("yStart"));
				cube23.put("yEnd", innerCube.get("yStart") - 1);
				cube23.put("zStart", innerCube.get("zStart"));
				cube23.put("zEnd", innerCube.get("zEnd"));
				if(isCubeLegit(cube23))
				{
					distinctCubesCopy.add(cube23);
				}
				
				HashMap<String, Long> cube24 = new HashMap<String, Long>();
				cube24.put("xStart", outerCube.get("xStart"));
				cube24.put("xEnd", innerCube.get("xStart") - 1);
				cube24.put("yStart", outerCube.get("yStart"));
				cube24.put("yEnd", innerCube.get("yStart") - 1);
				cube24.put("zStart", outerCube.get("zStart"));
				cube24.put("zEnd", innerCube.get("zStart") - 1);
				if(isCubeLegit(cube24))
				{
					distinctCubesCopy.add(cube24);
				}
				
				HashMap<String, Long> cube25 = new HashMap<String, Long>();
				cube25.put("xStart", innerCube.get("xStart"));
				cube25.put("xEnd", innerCube.get("xEnd"));
				cube25.put("yStart", outerCube.get("yStart"));
				cube25.put("yEnd", innerCube.get("yStart") - 1);
				cube25.put("zStart", outerCube.get("zStart"));
				cube25.put("zEnd", innerCube.get("zStart") - 1);
				if(isCubeLegit(cube25))
				{
					distinctCubesCopy.add(cube25);
				}
				
				HashMap<String, Long> cube26 = new HashMap<String, Long>();
				cube26.put("xStart", innerCube.get("xEnd") + 1);
				cube26.put("xEnd", outerCube.get("xEnd"));
				cube26.put("yStart", outerCube.get("yStart"));
				cube26.put("yEnd", innerCube.get("yStart") - 1);
				cube26.put("zStart", outerCube.get("zStart"));
				cube26.put("zEnd", innerCube.get("zStart") - 1);
				if(isCubeLegit(cube26))
				{
					distinctCubesCopy.add(cube26);
				}

				distinctCubesCopy.remove(outerCube);
				
				// StdOut.println("cube1 " + cube1);
				// StdOut.println("cube2 " + cube2);
				// StdOut.println("cube3 " + cube3);
				// StdOut.println("cube4 " + cube4);
				// StdOut.println("cube5 " + cube5);
				// StdOut.println("cube6 " + cube6);
				// StdOut.println(distinctCubesCopy);
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
		// if(((cube1.get("xStart") >= cube2.get("xStart") && cube1.get("xStart") <= cube2.get("xEnd")) || (cube1.get("xEnd") >= cube2.get("xStart") && cube1.get("xEnd") <= cube2.get("xEnd")))
		// 	&& ((cube1.get("yStart") >= cube2.get("yStart") && cube1.get("yStart") <= cube2.get("yEnd")) || (cube1.get("yEnd") >= cube2.get("yStart") && cube1.get("yEnd") <= cube2.get("yEnd")))
		//     && ((cube1.get("zStart") >= cube2.get("zStart") && cube1.get("zStart") <= cube2.get("zEnd")) || (cube1.get("zEnd") >= cube2.get("zStart") && cube1.get("zEnd") <= cube2.get("zEnd"))))
		// 	{
		// 		return true;
		// 	}
		// if(((cube2.get("xStart") >= cube1.get("xStart") && cube2.get("xStart") <= cube1.get("xEnd")) || (cube2.get("xEnd") >= cube1.get("xStart") && cube2.get("xEnd") <= cube1.get("xEnd")))
		// 	&& ((cube2.get("yStart") >= cube1.get("yStart") && cube2.get("yStart") <= cube1.get("yEnd")) || (cube2.get("yEnd") >= cube1.get("yStart") && cube2.get("yEnd") <= cube1.get("yEnd")))
		//     && ((cube2.get("zStart") >= cube1.get("zStart") && cube2.get("zStart") <= cube1.get("zEnd")) || (cube2.get("zEnd") >= cube1.get("zStart") && cube2.get("zEnd") <= cube1.get("zEnd"))))
		// 	{
		// 		return true;
		// 	}
		// return false;
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