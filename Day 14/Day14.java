import java.util.HashMap;
import java.util.*;

public class Day14
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		long startTime = System.nanoTime();
		String[] input_byLine = allInput.split("\r\n");
		
		HashMap<String, String> mappings = new HashMap<String, String>();
		HashMap<String, Long> currentString = new HashMap<String, Long>();
		int nIterations = 40;
		
		for(int line = 2; line < input_byLine.length; ++line)
		{
		    String[] mappingParts = input_byLine[line].split(" -> ");
			mappings.put(mappingParts[0], mappingParts[1]);
		}
		
		for(int pos = 0; pos < input_byLine[0].length() - 1; ++pos)
		{
		    String key = input_byLine[0].substring(pos, pos + 2);
			if(currentString.containsKey(key))
			{
			    currentString.put(key, currentString.get(key) + (long) 1);
			}
			else
			{
			    currentString.put(key, (long) 1);
			}
		}
		
		String lastLetter = input_byLine[0].substring(input_byLine[0].length() - 1, input_byLine[0].length());
		
		
		for(int iter = 0; iter < nIterations; ++iter)
	    {
			HashMap<String, Long> newString = new HashMap<String, Long>();
			for(HashMap.Entry<String, Long> entry : currentString.entrySet())
		    {
		    	String mapsTo = mappings.get(entry.getKey());
				String firstLetter = entry.getKey().substring(0, 1);
				String secondLetter = entry.getKey().substring(1, 2);
				
				if(newString.containsKey(firstLetter + mapsTo))
				{
					newString.put(firstLetter + mapsTo, newString.get(firstLetter + mapsTo) + entry.getValue());
				}
				else
				{
					newString.put(firstLetter + mapsTo, entry.getValue());
				}
				
				if(newString.containsKey(mapsTo + secondLetter))
				{
					newString.put(mapsTo + secondLetter, newString.get(mapsTo + secondLetter) + entry.getValue());
				}
				else
				{
					newString.put(mapsTo + secondLetter, entry.getValue());
				}
		    }
			currentString = newString;
			// StdOut.println(currentString);
		}
		StdOut.println(silverScore(currentString, lastLetter));
		long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime * Math.pow(10, -9));
	}
	
	public static long silverScore(HashMap<String, Long> map, String lastLetter)
	{
		HashMap<String, Long> counts = new HashMap<String, Long>();
		for(HashMap.Entry<String, Long> entry : map.entrySet())
		{
			String firstLetter = entry.getKey().substring(0, 1);
			
			if(counts.containsKey(firstLetter))
			{
				counts.put(firstLetter, counts.get(firstLetter) + (long) entry.getValue());
			}
			else
			{
				counts.put(firstLetter, (long) entry.getValue());
			}
		}
		
		counts.put(lastLetter, counts.get(lastLetter) + (long) 1);
		
		return Collections.max(counts.values()) - Collections.min(counts.values());
	}
}