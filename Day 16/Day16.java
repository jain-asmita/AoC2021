import java.util.HashMap;

public class Day16
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
		
		for(int i = 0; i < inputHexLength; ++i)
		{
		    binaryInput = binaryInput + hexToBin.get(allInput.substring(i, i + 1));
		}
		
		int[] globalLocationInString = new int[1];
		globalLocationInString[0] = 0;
		
		int[] globalVersionSum = new int[1];
		globalVersionSum[0] = 0;
		
		int versionNumber = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 3));
		globalVersionSum[0] += versionNumber;
		globalLocationInString[0] += 3;
		
		int packageType = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 3));
		globalLocationInString[0] += 3;
		
		if(packageType == 4)
		{
			literal(binaryInput, globalLocationInString, globalVersionSum);
		}
		else
		{
			int lengthType = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 1));
			globalLocationInString[0] += 1;
			if(lengthType == 1)
			{
				int nPackages = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 11));
				globalLocationInString[0] += 11;
				operatorCount(0, nPackages, binaryInput, globalLocationInString, globalVersionSum);
			}
			else
			{
				int packageLength = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 15));
				globalLocationInString[0] += 15;
				operatorLength(0, packageLength, binaryInput, globalLocationInString, globalVersionSum);
			}
		}
		
		StdOut.println(globalVersionSum[0]);
	}
	
	public static void operatorCount(int count, int maxCount, String binaryInput, int[] globalLocationInString, int[] globalVersionSum)
	{
		if(count == maxCount)
		{
			return;
		}
		
		int versionNumber = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 3));
		globalVersionSum[0] += versionNumber;
		globalLocationInString[0] += 3;
		
		int packageType = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 3));
		globalLocationInString[0] += 3;
		
		if(packageType == 4)
		{
			literal(binaryInput, globalLocationInString, globalVersionSum);
		}
		else
		{
			int lengthType = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 1));
			globalLocationInString[0] += 1;
			if(lengthType == 1)
			{
				int nPackages = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 11));
				globalLocationInString[0] += 11;
				operatorCount(0, nPackages, binaryInput, globalLocationInString, globalVersionSum);
			}
			else
			{
				int packageLength = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 15));
				globalLocationInString[0] += 15;
				operatorLength(0, packageLength, binaryInput, globalLocationInString, globalVersionSum);
			}
		}
		
		count++;
		operatorCount(count, maxCount, binaryInput, globalLocationInString, globalVersionSum);
	}
	
	public static void operatorLength(int position, int length, String binaryInput, int[] globalLocationInString, int[] globalVersionSum)
	{
		if(position >= length)
		{
			return;
		}
		
		int locInString = globalLocationInString[0];
		
		int versionNumber = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 3));
		globalVersionSum[0] += versionNumber;
		globalLocationInString[0] += 3;
		
		int packageType = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 3));
		globalLocationInString[0] += 3;
 
		if(packageType == 4)
		{
			literal(binaryInput, globalLocationInString, globalVersionSum);
		}
		else
		{
			int lengthType = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 1));
			globalLocationInString[0] += 1;
			if(lengthType == 1)
			{
				int nPackages = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 11));
				globalLocationInString[0] += 11;
				operatorCount(0, nPackages, binaryInput, globalLocationInString, globalVersionSum);
			}
			else
			{
				int packageLength = binStringToDecInt(binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 15));
				globalLocationInString[0] += 15;
				operatorLength(0, packageLength, binaryInput, globalLocationInString, globalVersionSum);
			}
		}
		
		position += (globalLocationInString[0] - locInString);
		operatorLength(position, length, binaryInput, globalLocationInString, globalVersionSum);
	}
	
	public static void literal(String binaryInput, int[] globalLocationInString, int[] globalVersionSum)
	{
		while(true)
		{
			String firstCharacter = binaryInput.substring(globalLocationInString[0], globalLocationInString[0] + 1);
			globalLocationInString[0] += 5;
			if(firstCharacter.equals("0"))
			{
				break;
			}
		}
		return;
	}
	
	public static int binStringToDecInt(String bin)
	{
		int dec = 0;
		for(int pos = bin.length() - 1; pos >= 0; --pos)
		{
			int binNum = Integer.parseInt(bin.substring(pos, pos + 1));
			dec += binNum * Math.pow(2, bin.length() - 1 - pos);
		}
		
		return dec;
	}
}