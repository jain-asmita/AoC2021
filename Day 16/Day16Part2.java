import java.util.HashMap;
import java.util.ArrayList;
import java.util.*;

public class Day16Part2
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		HashMap<String, String> hexToBin = new HashMap<String, String>();
		hexToBin.put("0", "0000");
		hexToBin.put("1", "0001");
		hexToBin.put("2", "0010");
		hexToBin.put("3", "0011");
		hexToBin.put("4", "0100");
		hexToBin.put("5", "0101");
		hexToBin.put("6", "0110");
		hexToBin.put("7", "0111");
		hexToBin.put("8", "1000");
		hexToBin.put("9", "1001");
		hexToBin.put("A", "1010");
		hexToBin.put("B", "1011");
		hexToBin.put("C", "1100");
		hexToBin.put("D", "1101");
		hexToBin.put("E", "1110");
		hexToBin.put("F", "1111");
		
		int inputHexLength = allInput.length();
		String binaryInput = "";
		
		HashMap<Long, String> typeToOperation = new HashMap<Long, String>();
		typeToOperation.put((long) 0, "sum");
		typeToOperation.put((long) 1, "product");
		typeToOperation.put((long) 2, "minimum");
		typeToOperation.put((long) 3, "maximum");
		typeToOperation.put((long) 5, "greater than");
		typeToOperation.put((long) 6, "less than");
		typeToOperation.put((long) 7, "equal to");
		
		for(int i = 0; i < inputHexLength; ++i)
		{
		    binaryInput = binaryInput + hexToBin.get(allInput.substring(i, i + 1));
		}
		
		int[] globalLocationInString = new int[1];
		globalLocationInString[0] = 0;
		
		globalLocationInString[0] += 3;
		
		long packageType = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 3));
		globalLocationInString[0] += 3;
		
		if(packageType == 4)
		{
			literal(binaryInput, globalLocationInString, new ArrayList<Long>());
		}
		else
		{
			long lengthType = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 1));
			globalLocationInString[0] += 1;
			
			String operation = typeToOperation.get(packageType);
				
			if(lengthType == 1)
			{
				long nPackages = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 11));
				globalLocationInString[0] += 11;
				StdOut.println(operatorCount((long) 0, nPackages, binaryInput, globalLocationInString, new ArrayList<Long>(), operation, typeToOperation));
			}
			else
			{
				long packageLength = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 15));
				globalLocationInString[0] += 15;
				StdOut.println(operatorLength(0, packageLength, binaryInput, globalLocationInString, new ArrayList<Long>(), operation, typeToOperation));
			}
		}
		
		StdOut.println(binaryInput);
		
	}
	
	public static long operatorCount(long count, long maxCount, String binaryInput, int[] globalLocationInString, ArrayList<Long> numbers, String operation, HashMap<Long, String> typeToOperation)
	{
		if(count == maxCount)
		{
			StdOut.println("count");
			StdOut.println(numbers);
			StdOut.println(operation);
			
			switch(operation)
			{
				case "sum":
				    return sum(numbers);
				case "product":
				    return product(numbers);
				case "minimum":
				    return Collections.min(numbers);
				case "maximum":
				    return Collections.max(numbers);
				case "greater than":
				    return greaterThan(numbers);
				case "less than":
				    return lessThan(numbers);
				case "equal to":
				    return equals(numbers);
			}
		}
		
		globalLocationInString[0] += 3;		
		long packageType = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 3));
		globalLocationInString[0] += 3;
		
		if(packageType == (long) 4)
		{
			literal(binaryInput, globalLocationInString, numbers);
		}
		else
		{
			long lengthType = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 1));
			String nextOperation = typeToOperation.get(packageType);
			globalLocationInString[0] += 1;
			if(lengthType == 1)
			{
				long nPackages = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 11));
				globalLocationInString[0] += 11;
				numbers.add(operatorCount((long) 0, nPackages, binaryInput, globalLocationInString, new ArrayList<Long>(), nextOperation, typeToOperation));
			}
			else
			{
				long packageLength = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 15));
				globalLocationInString[0] += 15;
				numbers.add(operatorLength(0, packageLength, binaryInput, globalLocationInString, new ArrayList<Long>(), nextOperation, typeToOperation));
			}
		}
		
		count++;
		return operatorCount(count, maxCount, binaryInput, globalLocationInString, numbers, operation, typeToOperation);
	}
	
	public static long operatorLength(int position, long length, String binaryInput, int[] globalLocationInString, ArrayList<Long> numbers, String operation, HashMap<Long, String> typeToOperation)
	{
		if(position >= length)
		{
			StdOut.println("length");
			StdOut.println(numbers);
			StdOut.println(operation);
			
			switch(operation)
			{
				case "sum":
				    return sum(numbers);
				case "product":
				    return product(numbers);
				case "minimum":
				    return Collections.min(numbers);
				case "maximum":
				    return Collections.max(numbers);
				case "greater than":
				    return greaterThan(numbers);
				case "less than":
				    return lessThan(numbers);
				case "equal to":
				    return equals(numbers);
			}
		}
		
		int locInString = globalLocationInString[0];
		
		globalLocationInString[0] += 3;
		
		long packageType = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 3));
		globalLocationInString[0] += 3;
 
		if(packageType == (long) 4)
		{
			literal(binaryInput, globalLocationInString, numbers);
		}
		else
		{
			long lengthType = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 1));
			String nextOperation = typeToOperation.get(packageType);
			globalLocationInString[0] += 1;
			if(lengthType == 1)
			{
				long nPackages = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 11));
				globalLocationInString[0] += 11;
				numbers.add(operatorCount((long) 0, nPackages, binaryInput, globalLocationInString, new ArrayList<Long>(), nextOperation, typeToOperation));
			}
			else
			{
				long packageLength = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 15));
				globalLocationInString[0] += 15;
				numbers.add(operatorLength(0, packageLength, binaryInput, globalLocationInString, new ArrayList<Long>(), nextOperation, typeToOperation));
			}
		}
		
		position += (globalLocationInString[0] - locInString);
		return operatorLength(position, length, binaryInput, globalLocationInString, numbers, operation, typeToOperation);
	}
	
	public static void literal(String binaryInput, int[] globalLocationInString, ArrayList<Long> values)
	{
		String binNumber = "";
		int i = 0;
		while(true)
		{
			String firstCharacter = binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 1);
			binNumber += binaryInput.substring(globalLocationInString[0] + 1, globalLocationInString[0] + 5);
			i+=5;
			globalLocationInString[0] += 5;
			
			if(firstCharacter.equals("0"))
			{
				values.add(binStringToDecInt(binNumber));
				StdOut.println(values);
				break;
			}
		}
		return;
	}
	
	public static long binStringToDecInt(String bin)
	{
		long dec = (long) 0;
		for(int pos = bin.length() - 1; pos >= 0; --pos)
		{
			int binNum = Integer.parseInt(bin.substring(pos, pos + 1));
			dec += (long) binNum * Math.pow(2, bin.length() - 1 - pos);
		}
		
		return dec;
	}
	
	public static long sum(ArrayList<Long> arrayList)
	{
		long sum = 0;
		for(int i = 0; i < arrayList.size(); ++i)
		{
		    sum += arrayList.get(i);	
		}
		return sum;
	}
	
	public static long product(ArrayList<Long> arrayList)
	{
		long product = (long) 1;
		for(int i = 0; i < arrayList.size(); ++i)
		{
		    product = product * arrayList.get(i);	
		}
		return product;
	}
	
	public static long greaterThan(ArrayList<Long> arrayList)
	{
		if(arrayList.get(0) > arrayList.get(1))
		{
			return (long) 1;
		}
		return (long) 0;
	}
	
	public static long lessThan(ArrayList<Long> arrayList)
	{
		if(arrayList.get(0) < arrayList.get(1))
		{
			return (long) 1;
		}
		return (long) 0;
	}
	
	public static long equals(ArrayList<Long> arrayList)
	{
		if(arrayList.get(0).equals(arrayList.get(1)))
		{
			return (long) 1;
		}
		return (long) 0;
	}
}