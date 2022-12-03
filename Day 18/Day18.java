import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.*;

public class Day18
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");

		ArrayList<Integer> sums = new ArrayList<Integer>();
		
		for(int i = 0; i < input_byLine.length; ++i)
		{
			for(int j = 0; j < input_byLine.length; ++j)
			{
				if(i != j)
				{
					String inputStr = "[" + input_byLine[i] + "," + input_byLine[j] + "]";
			        String newString;
				    while(true)
		            {
		            	newString = inputStr;
		            	inputStr = reduce(inputStr);
		            	if(newString.equals(inputStr))
		            	{
		            		break;
		            	}
		            }
				    StdOut.println(i + " " + j);
				    sums.add(calculateScore(inputStr));
				}
				
			}
		}
		
		StdOut.println(Collections.max(sums));
	}
	
	public static String reduce(String inputStr)
	{
	    int strLength = inputStr.length();
		int bracketCount = 0;
		//boolean lastPosWasInt = false;
		
		for(int i = 0; i < strLength; i++)
		{
		    String currentStr = inputStr.substring(i, i + 1);
		    if(bracketCount == 5)
			{
			    inputStr = explode(inputStr, i);
				return inputStr;
			}
			else if(currentStr.equals("["))
			{
			    bracketCount++;
			}
			else if(currentStr.equals("]"))
			{
			    bracketCount--;
			}
		}
		boolean lastPosWasInt = false;
		
		for(int i = 0; i < strLength; i++)
		{
			String currentStr = inputStr.substring(i, i + 1);
			if(lastPosWasInt && isNum(currentStr))
			{
				return split(inputStr, i);
			}
			else if(isNum(currentStr))
			{
				lastPosWasInt = true;
			}
			else
			{
				lastPosWasInt = false;
			}
		}
		
		return (inputStr);
	}
	
	public static String split(String str, int pos)
	{
		pos--;
		String wholeNumString = "";
		for(int i = pos; pos < str.length(); ++i)
	    {
			String currentStr = str.substring(i, i+1);
			if(!(currentStr.equals("[") || currentStr.equals("]") || currentStr.equals(",")))
			{
				wholeNumString += currentStr;
			}
			else
			{
				break;
			}
		}
		
		int firstNum = (Integer.parseInt(wholeNumString) / 2);
		int secondNum = (Integer.parseInt(wholeNumString) / 2) + Integer.parseInt(wholeNumString) % 2;
		return str.substring(0, pos) + "[" + firstNum + "," + secondNum + "]" + str.substring(pos + wholeNumString.length());
	}
	
	public static boolean isNum(String str)
	{
		return !(str.equals("[") || str.equals("]") || str.equals(","));
	}
	
	public static String explode(String str, int pos)
	{
		String firstNumString = "";
		int x = pos;
		while(x < str.length())
		{
			String nextCh = str.substring(x, x + 1);
			++x;
			if(isNum(nextCh))
			{
				firstNumString += nextCh;
			}
			else
			{
				break;
			}
		}
		
		int firstNum = Integer.parseInt(firstNumString);
		
		String secondNumString = "";
		while(x < str.length())
		{
			String nextCh = str.substring(x, x + 1);
			++x;
			if(isNum(nextCh))
			{
				secondNumString += nextCh;
			}
			else
			{
				break;
			}
		}
		
		int secondNum = Integer.parseInt(secondNumString);
		
		String newStr = "";
		String newStr2 = "";
	    if(pos != 0)
		{
		    for(int i = pos - 1; i >= 0; --i)
			{
			    String currentStr = str.substring(i, i + 1);
				if(!(currentStr.equals("[") || currentStr.equals("]") || currentStr.equals(",")))
				{
					newStr += currentStr;
				}
				else if(!newStr.equals(""))
				{
				    newStr2 = String.valueOf(Integer.parseInt(new StringBuilder(newStr).reverse().toString()) + firstNum);
				    str = str.substring(0, i + 1) + newStr2 + str.substring(i + newStr.length() + 1);
					break;
				}
			}
		}
		
		pos += (newStr2.length() - newStr.length());

		
		newStr = "";
		newStr2 = ""; 
		
		if(pos != str.length() - 1)
		{
		    for(int i = pos + firstNumString.length() + 1 + secondNumString.length(); i < str.length(); ++i)
		    {
		        String currentStr = str.substring(i, i + 1);
				if(!(currentStr.equals("[") || currentStr.equals("]") || currentStr.equals(",")))
				{
					newStr += currentStr;
				}
				else if(!newStr.equals(""))
				{
				    newStr2 = String.valueOf(Integer.parseInt(newStr) + secondNum);
					str = str.substring(0, i - newStr.length()) + newStr2 + str.substring(i);
					break;
				}
		    }
		}
		
		str = str.substring(0, pos - 1) + "0" + str.substring(pos + 2 + firstNumString.length() + secondNumString.length());
		return str;
	}
	
	public static int calculateScore(String str)
	{
		Pattern pattern = Pattern.compile("\\[\\d+,\\d+\\]");
		String newStr = str;
		String newStrAtStart = newStr;
		while(true)
		{
			newStr = newStrAtStart;
			Matcher m = pattern.matcher(newStr);
			int adjustment = 0;
			while(m.find())
			{
				String data = newStr.substring(m.start() + 1, m.end() - 1);
				int firstNum = Integer.parseInt(data.split(",")[0]);
				int secondNum = Integer.parseInt(data.split(",")[1]);
				newStrAtStart = newStrAtStart.substring(0, m.start() + adjustment) + (3*firstNum+2*secondNum) + newStrAtStart.substring(m.end()+ adjustment);
			    adjustment += String.valueOf((3*firstNum+2*secondNum)).length() - (m.end() - m.start());		
			}
			if(newStrAtStart.equals(newStr))
			{
				break;
			}
		}
		
		return Integer.parseInt(newStr);
		
		
	}
	
}