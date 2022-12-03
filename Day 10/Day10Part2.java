import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class Day10Part2
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int maxLineLength = 110;
		int nLines = input_byLine.length;
		ArrayList<ArrayList<String>> input = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> inputPart2 = new ArrayList<ArrayList<String>>();
		
		HashMap<String, String> openCloseSymbolMap = new HashMap<String, String>();
		openCloseSymbolMap.put("{", "}");
		openCloseSymbolMap.put("[", "]");
		openCloseSymbolMap.put("(", ")");
		openCloseSymbolMap.put("<", ">");
		
		HashMap<String, Integer> pointsMapping = new HashMap<String, Integer>();
		pointsMapping.put(")", 3);
		pointsMapping.put("]", 57);
	    pointsMapping.put("}", 1197);
		pointsMapping.put(">", 25137);
		
		for(int lineN = 0; lineN < nLines; ++lineN)
		{
			ArrayList<String> line = new ArrayList<String>();
			for(int pos = 0; pos < input_byLine[lineN].length(); ++pos)
			{
				line.add(input_byLine[lineN].substring(pos, pos + 1));
			}
			input.add(line);
		}
		
		HashMap<String, Integer> pointsMappingPart2 = new HashMap<String, Integer>();
		pointsMappingPart2.put(")", 1);
		pointsMappingPart2.put("]", 2);
        pointsMappingPart2.put("}", 3);
		pointsMappingPart2.put(">", 4);
		
		int silverAnswer = 0;
		ArrayList<Long> goldAnswer = new ArrayList<Long>();
		
		for(int line = 0; line < nLines; ++line)
		{
		    String errorSymbol = checkLine(input.get(line), openCloseSymbolMap);
		    if(!errorSymbol.equals(""))
		    {
		    	silverAnswer += pointsMapping.get(errorSymbol);
		    }
            else
            {
                ArrayList<String> charactersToAdd = findRemainingCharacters(input.get(line), openCloseSymbolMap);
				goldAnswer.add(findScore(charactersToAdd, pointsMappingPart2));
			}				
		}
		
		StdOut.println(silverAnswer);
		Collections.sort(goldAnswer);
		int position = goldAnswer.size()/2;
		StdOut.println(goldAnswer.get(position));
		
	}
	
	public static long findScore(ArrayList<String> charactersToAdd, HashMap<String, Integer> pointsMapping)
	{
		long points = 0;
		int length = charactersToAdd.size();
		
		for(int i = 0; i < length; ++i)
		{
			points = points * 5 + (long) pointsMapping.get(charactersToAdd.get(i));
		}
		return points;
	}
	
	public static ArrayList<String> findRemainingCharacters(ArrayList<String> line, HashMap<String, String> openCloseSymbolMap)
	{
		ArrayList<String> additionalCharacters = new ArrayList<String>();
		ArrayList<String> startCharacter = new ArrayList<String>();
		int length = line.size();
		for(int pos = 0; pos < length; ++pos)
		{
			if(openCloseSymbolMap.keySet().contains(line.get(pos)))
			{
				startCharacter.add(line.get(pos));
			}
			else
			{
				int startCharacterLength = startCharacter.size();
				if(openCloseSymbolMap.get(startCharacter.get(startCharacterLength - 1)).equals(line.get(pos)))
				{
					startCharacter.remove(startCharacterLength - 1);
				}
			}
		}
		
		for(int i = 0; i < startCharacter.size(); ++i)
		{
			additionalCharacters.add(openCloseSymbolMap.get(startCharacter.get(startCharacter.size() - 1 - i)));
		}
		
		return additionalCharacters;
	}
	
	public static boolean isLineValid(ArrayList<String> line, HashMap<String, String> openCloseSymbolMap)
	{
		int length = line.size();
		if(openCloseSymbolMap.get(line.get(0)).equals(line.get(length - 1)))
		{
			return true;
		}
		return false;
	}
	
	public static String checkLine(ArrayList<String> line, HashMap<String, String> openCloseSymbolMap)
	{
		int length = line.size();
		ArrayList<String> startCharacter = new ArrayList<String>();
		
		for(int pos = 0; pos < length; ++pos)
		{
			if(openCloseSymbolMap.keySet().contains(line.get(pos)))
			{
				startCharacter.add(line.get(pos));
			}
			else
			{
				int startCharacterLength = startCharacter.size();
				if(openCloseSymbolMap.get(startCharacter.get(startCharacterLength - 1)).equals(line.get(pos)))
				{
					startCharacter.remove(startCharacterLength - 1);
				}
				else
				{
					return line.get(pos);
				}
			}
		}
		
		return "";
	}
}