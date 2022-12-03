import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class Day22
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int nLines = input_byLine.length;
		ArrayList<Boolean> instruction = new ArrayList<Boolean>();
		ArrayList<HashMap<String, Integer>> coordinates = new ArrayList<HashMap<String, Integer>>();
		
		for(int i = 0; i < nLines; ++i)
		{
		    String[] firstSplit = input_byLine[i].split(" ");
			instruction.add((firstSplit[0].equals("on")) ? true : false);
			String[] secondSplit = firstSplit[1].split("=");
			int xStart = Integer.parseInt(secondSplit[1].split("\\.\\.")[0]);
			int xEnd = Integer.parseInt(secondSplit[1].split("\\.\\.")[1].split(",")[0]);
			int yStart = Integer.parseInt(secondSplit[2].split("\\.\\.")[0]);
			int yEnd = Integer.parseInt(secondSplit[2].split("\\.\\.")[1].split(",")[0]);
			int zStart = Integer.parseInt(secondSplit[3].split("\\.\\.")[0]);
			int zEnd = Integer.parseInt(secondSplit[3].split("\\.\\.")[1].split(",")[0]);
			HashMap<String, Integer> coordinate = new HashMap<String, Integer>();
			coordinate.put("xStart", xStart);
			coordinate.put("xEnd", xEnd);
			coordinate.put("yStart", yStart);
			coordinate.put("yEnd", yEnd);
			coordinate.put("zStart", zStart);
			coordinate.put("zEnd", zEnd);
			coordinates.add(coordinate);
		}
		
        HashSet<HashMap<String, Integer>> coords = new HashSet<HashMap<String, Integer>>();
		for(int i = 0; i < nLines; ++i)
		{
			for(int x = coordinates.get(i).get("xStart"); x <= coordinates.get(i).get("xEnd"); ++x)
			{
				for(int y = coordinates.get(i).get("yStart"); y <= coordinates.get(i).get("yEnd"); ++y)
			    {
			        for(int z = coordinates.get(i).get("zStart"); z <= coordinates.get(i).get("zEnd"); ++z)
			        {
						HashMap<String, Integer> map = new HashMap<String, Integer>();
						map.put("x", x);
						map.put("y", y);
						map.put("z", z);
						
			        	if(instruction.get(i))
						{
							if(!coords.contains(map))
							{
								coords.add(map);
							}
						}
						else
						{
							if(coords.contains(map))
							{
								coords.remove(map);
							}
						}
			        }
			    }
			}
			StdOut.println(coords.size());
			//StdOut.println(i);
		}
		
		StdOut.println(coords.size());
		
	}
}