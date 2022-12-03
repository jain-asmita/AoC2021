import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;

public class Day8Part2
{
    public static void main(String[] args)
	{
		ArrayList<HashMap<Integer, HashSet<String>>> output = new ArrayList<HashMap<Integer, HashSet<String>>>();
		
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int nLines = input_byLine.length;

		
		for(int line = 0; line < nLines; ++line)
		{
			String outputPart = input_byLine[line].split("\s\\|\s")[1];
			String[] outputPartArray = outputPart.split(" ");
			HashMap<Integer, HashSet<String>> lineDetails = new HashMap<Integer, HashSet<String>>();
			
			for (int digit = 0; digit < 4; ++digit)
			{
				String digitString = outputPartArray[digit];
				HashSet<String> digitDetails = new HashSet<String>();
				for (int position = 0; position < digitString.length(); ++position)
				{
					digitDetails.add(digitString.substring(position, position + 1));
				}
				lineDetails.put(digit, digitDetails);
			}
			output.add(lineDetails);
		}
		
		int goldAnswer = 0;
		
		for(int line = 0; line < nLines; ++line)
		{
			String inputPart = input_byLine[line].split("\s\\|\s")[0];
			String[] inputPartArray = inputPart.split(" ");
			HashMap<String, String> lineDetails = findMapping(inputPartArray);
			
			HashMap<HashSet<String>, String> realMappings = new HashMap<HashSet<String>, String>();
		
		    HashSet<String> nine = new HashSet<String>();
		    nine.add(lineDetails.get("a"));
		    nine.add(lineDetails.get("c"));
		    nine.add(lineDetails.get("g"));
	        nine.add(lineDetails.get("d"));
		    nine.add(lineDetails.get("f"));
	        nine.add(lineDetails.get("b"));
		    realMappings.put(nine, "9");
		    
		    HashSet<String> eight = new HashSet<String>();
		    eight.add(lineDetails.get("a"));
		    eight.add(lineDetails.get("c"));
		    eight.add(lineDetails.get("e"));
	        eight.add(lineDetails.get("d"));
		    eight.add(lineDetails.get("g"));
		    eight.add(lineDetails.get("f"));
	        eight.add(lineDetails.get("b"));
		    realMappings.put(eight, "8");
		    
		    HashSet<String> seven = new HashSet<String>();
		    seven.add(lineDetails.get("a"));
	        seven.add(lineDetails.get("c"));
	        seven.add(lineDetails.get("f"));
		    realMappings.put(seven, "7");
		    
		    HashSet<String> six = new HashSet<String>();
		    six.add(lineDetails.get("a"));
		    six.add(lineDetails.get("e"));
	        six.add(lineDetails.get("d"));
		    six.add(lineDetails.get("g"));
		    six.add(lineDetails.get("f"));
	        six.add(lineDetails.get("b"));
		    realMappings.put(six, "6");
		    
		    HashSet<String> five = new HashSet<String>();
		    five.add(lineDetails.get("g"));
		    five.add(lineDetails.get("a"));
	        five.add(lineDetails.get("d"));
		    five.add(lineDetails.get("f"));
	        five.add(lineDetails.get("b"));
		    realMappings.put(five, "5");
		    
		    HashSet<String> four = new HashSet<String>();
		    four.add(lineDetails.get("c"));
		    four.add(lineDetails.get("d"));
		    four.add(lineDetails.get("f"));
	        four.add(lineDetails.get("b"));
		    realMappings.put(four, "4");
		    
		    HashSet<String> three = new HashSet<String>();
		    three.add(lineDetails.get("a"));
		    three.add(lineDetails.get("c"));
	        three.add(lineDetails.get("d"));
		    three.add(lineDetails.get("f"));
	        three.add(lineDetails.get("g"));
		    realMappings.put(three, "3");
		    
		    HashSet<String> two = new HashSet<String>();
		    two.add(lineDetails.get("a"));
		    two.add(lineDetails.get("c"));
	        two.add(lineDetails.get("d"));
		    two.add(lineDetails.get("g"));
		    two.add(lineDetails.get("e"));
		    realMappings.put(two, "2");
		    
		    HashSet<String> one = new HashSet<String>();
		    one.add(lineDetails.get("c"));
	        one.add(lineDetails.get("f"));
		    realMappings.put(one, "1");
		    
		    HashSet<String> zero = new HashSet<String>();
		    zero.add(lineDetails.get("a"));
		    zero.add(lineDetails.get("c"));
		    zero.add(lineDetails.get("e"));
	        zero.add(lineDetails.get("f"));
		    zero.add(lineDetails.get("g"));
	        zero.add(lineDetails.get("b"));
		    realMappings.put(zero, "0");
			
			for (int digitN = 0; digitN < 4; ++digitN)
			{
				HashSet<String> digitDetails = output.get(line).get(digitN);
				String digitString = "";
				for(HashMap.Entry<HashSet<String>, String> entry : realMappings.entrySet())
				{
					if(entry.getKey().equals(digitDetails))
					{
						digitString = digitString + entry.getValue();
					}
				}
				
				goldAnswer += Integer.parseInt(digitString) * Math.pow(10, 3 - digitN);
			}
		}
		
		StdOut.println(goldAnswer);
	}
	
	
	public static HashMap<String, String> findMapping(String[] uniqueNumbers)
	{
		int[] uniqueNumberLength = new int[10];
		for(int i = 0; i < 10; ++i)
		{
			uniqueNumberLength[i] = uniqueNumbers[i].length();
		}
		
		HashSet<String> seven = new HashSet<String>();
		HashSet<String> sevenCopy = new HashSet<String>();
		HashSet<String> four = new HashSet<String>();
		HashSet<String> fourCopy = new HashSet<String>();
		HashSet<String> one = new HashSet<String>();
		HashSet<String> eight = new HashSet<String>();
		
		for(int i = 0; i < 10; ++i)
		{
			if(uniqueNumberLength[i] == 2)
			{
				for(int j = 0; j < 2; ++j)
				{
					one.add(uniqueNumbers[i].substring(j, j + 1));
				}
			}
			else if(uniqueNumberLength[i] == 3)
			{
				for(int j = 0; j < 3; ++j)
				{
					seven.add(uniqueNumbers[i].substring(j, j + 1));
					sevenCopy.add(uniqueNumbers[i].substring(j, j + 1));
				}
			}
			else if(uniqueNumberLength[i] == 4)
			{
				for(int j = 0; j < 4; ++j)
				{
					four.add(uniqueNumbers[i].substring(j, j + 1));
					fourCopy.add(uniqueNumbers[i].substring(j, j + 1));
				}
			}
			else if(uniqueNumberLength[i] == 7)
			{
				for(int j = 0; j < 7; ++j)
				{
					eight.add(uniqueNumbers[i].substring(j, j + 1));
				}
			}
		}
		
		HashMap<String, Integer> occurrences = new HashMap<String, Integer>();
		occurrences.put("a", 0);
		occurrences.put("b", 0);
		occurrences.put("c", 0);
		occurrences.put("d", 0);
		occurrences.put("e", 0);
		occurrences.put("f", 0);
		occurrences.put("g", 0);
		
		for(int i = 0; i < 10; ++i)
		{
			for(String key : occurrences.keySet())
			{
				if (uniqueNumbers[i].contains(key))
				{
					occurrences.put(key, occurrences.get(key) + 1);
				}
			}
		}
		
		HashMap<String, String> outputMap = new HashMap<String, String>();
		HashSet<String> dgCandidates = new HashSet<String>();
		HashSet<String> dgCandidatesCopy = new HashSet<String>();
		
		for(HashMap.Entry<String, Integer> entry : occurrences.entrySet())
		{
			if(entry.getValue().equals(6))
			{
				outputMap.put("b", entry.getKey());
			}
			else if(entry.getValue().equals(4))
			{
				outputMap.put("e", entry.getKey());
			}
			else if(entry.getValue().equals(9))
			{
				outputMap.put("f", entry.getKey());
			}
			else if(entry.getValue().equals(8))
			{
				sevenCopy.removeAll(one);
				String a = "";
				for(String k : sevenCopy)
				{
					a = k;
				}
				if(entry.getKey().equals(a))
				{
					outputMap.put("a", a);
				}
				else
				{
					outputMap.put("c", entry.getKey());
				}
			}
			else
			{
				dgCandidates.add(entry.getKey());
				dgCandidatesCopy.add(entry.getKey());
			}
		}
		
		fourCopy.removeAll(outputMap.values());
		String d = "";
		for(String k : fourCopy)
		{
			d = k;
		}
		outputMap.put("d", d);
		
		dgCandidatesCopy.removeAll(fourCopy);
		String g = "";
		
		for(String k : dgCandidatesCopy)
		{
			g = k;
		}
		outputMap.put("g", g);
		
		return outputMap;
	}
}