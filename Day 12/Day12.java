import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

public class Day12
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		HashMap<String, HashSet<String>> input = new HashMap<String, HashSet<String>>();
		HashSet<String> lowerCaseCaves = new HashSet<String>();
		HashSet<String> upperCaseCaves = new HashSet<String>();
		
		for(int line = 0; line < input_byLine.length; ++line)
		{
			String[] caves = input_byLine[line].split("-");
			for(int i = 0; i < 2; i++)
			{
				if(!caves[0].equals("start") && !caves[1].equals("end") || caves[0].equals("start") && caves[i].equals("start") || caves[1].equals("end") && !caves[i].equals("end"))
				{
					if(input.containsKey(caves[i]))
				    {
				    	HashSet<String> a = input.get(caves[i]);
				    	a.add(caves[(i+1)%2]);
				    	input.put(caves[i], a);
				    }
				    else
				    {
				    	HashSet<String> a = new HashSet<String>();
				    	a.add(caves[(i+1)%2]);
				    	input.put(caves[i], a);
				    }
				    
				    if(caves[i].toUpperCase().equals(caves[i]) && !caves[i].equals("start") && !caves[i].equals("end"))
				    {
				    	upperCaseCaves.add(caves[i]);
				    }
				    else
				    {
				    	lowerCaseCaves.add(caves[i]);
				    }
				}
			}
		}
		
		HashSet<ArrayList<String>> paths = new HashSet<ArrayList<String>>();
		ArrayList<String> path = new ArrayList<String>();
		path.add("start");
		paths.add(path);
		
		while(true)
		{
			HashSet<ArrayList<String>> newPaths = new HashSet<ArrayList<String>>();
			for(ArrayList<String> list: paths)
			{
				String lastElement = list.get(list.size() - 1);

				if(lastElement.equals("end"))
				{
					newPaths.add(list);
				}
				else
				{
					HashSet<String> possibilities = input.get(lastElement);
					for(String nextCave: possibilities)
					{
						ArrayList<String> listCopy = new ArrayList<String>();
				        listCopy.addAll(list);
						listCopy.add(nextCave);
						
						if(!nextCave.equals("start") && (upperCaseCaves.contains(nextCave) || !containsDuplicateLowerCase(listCopy, lowerCaseCaves) || nextCave.equals("end"))) // !listCopy.contains(nextCave) || )
						{
							newPaths.add(listCopy);
						}
					}
				}
			}
			paths = newPaths;
			if(haveAllPathsEnded(paths))
			{
				break;
			}
			//StdOut.println(paths);
		}
		
		StdOut.println(paths.size());
	}
	
	public static boolean containsDuplicateLowerCase(ArrayList<String> list, HashSet<String> lowerCaseCaves)
	{
		// StdOut.println(list);
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		for(String cave: list)
		{
			if(lowerCaseCaves.contains(cave) && !counts.containsKey(cave))
			{
				counts.put(cave, 1);
			}
			else if(lowerCaseCaves.contains(cave))
			{
				counts.put(cave, counts.get(cave) + 1);
			}
		}
		// StdOut.println(counts);
		int countDuplicates = 0;
		int sumDuplicates = 0;
		
		for(int values: counts.values())
		{
			if(!(values == 1))
			{
				sumDuplicates += values;
				countDuplicates++;
			}
		}
		// StdOut.println(countDuplicates);
		return countDuplicates > 1 || sumDuplicates > 2;
	}
	
	public static boolean haveAllPathsEnded(HashSet<ArrayList<String>> paths)
	{
		for(ArrayList<String> list: paths)
		{
			if(!list.get(list.size() - 1).equals("end"))
			{
				return false;
			}
		}
		return true;
	}
}