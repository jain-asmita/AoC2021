import java.util.ArrayList;
import java.util.HashMap;

public class Day10
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int maxLineLength = 110;
		int nLines = input_byLine.length;
		ArrayList<ArrayList<String>> input = new ArrayList<ArrayList<String>>();
		// String[][] input = new String[nLines][maxLineLength];
		
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
		
		int silverAnswer = 0;
		for(int line = 0; line < nLines; ++line)
		{
		    String errorSymbol = checkLine(input.get(line), openCloseSymbolMap);
		    if(!errorSymbol.equals(""))
		    {
		    	silverAnswer += pointsMapping.get(errorSymbol);
		    }				
		}
		
		StdOut.println(silverAnswer);
		
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