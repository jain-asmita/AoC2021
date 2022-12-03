import java.util.HashMap;
import java.util.*;

public class Day3Part2
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byline = allInput.split("\r\n");
		int xLength = input_byline[0].length();
		int yLength = input_byline.length;
		int[][] input = new int[xLength][yLength];
		
		for (int x = 0; x < xLength; ++x)
		{
		    for (int y = 0; y < yLength; ++y)
			{
			    input[x][y] = Integer.parseInt(input_byline[y].substring(x, x + 1)); 
			}
		}
		
		int[] gammaRateBinary = new int[xLength];
		int[] epsilonRateBinary = new int[xLength];
		
		for (int x = 0; x < xLength; ++x)
		{
		    int sum = 0;
			for (int y = 0; y < yLength; ++y)
			{
			    sum += input[x][y];
			}
			
		    if (sum >= yLength / 2)
			{
			    gammaRateBinary[x] = 1;
			}
			else
			{
			    epsilonRateBinary[x] = 1;
			}
		}
		
		int[] oxygenRateContainsLine = new int[yLength];
		int[] c02RateContainsLine = new int[yLength];
		
		for (int y = 0; y < yLength; ++y)
		{
			oxygenRateContainsLine[y] = 1;
			c02RateContainsLine[y] = 1;
		}
		
		int oxygenRateIndex = -1;
		int c02RateIndex = -1;
		
		for (int x = 0; x < xLength; ++x)
		{
			int criteria = 0;
			int[] prod = productArrays(input[x], oxygenRateContainsLine);
			double length = sumArray(oxygenRateContainsLine);
			if ((double) sumArray(prod) >= (double) length / 2)
			{
				criteria = 1;
			}
			
			for (int y = 0; y < yLength; ++y)
			{
				if(input[x][y] != criteria)
				{
					oxygenRateContainsLine[y] = 0;
				}
			}
			
			if (sumArray(oxygenRateContainsLine) == 1)
			{
				oxygenRateIndex = findIndexOne(oxygenRateContainsLine);
				break;
			}
		}
		
		for (int x = 0; x < xLength; ++x)
		{
			int criteria = 1;
			int[] prod = productArrays(input[x], c02RateContainsLine);
			double length = sumArray(c02RateContainsLine);
			if ((double) sumArray(prod) >= (double) length / 2)
			{
				criteria = 0;
			}
					
			for (int y = 0; y < yLength; ++y)
			{
				if(input[x][y] != criteria)
				{
					c02RateContainsLine[y] = 0;
				}
			}
			
			if (sumArray(c02RateContainsLine) == 1)
			{
				c02RateIndex = findIndexOne(c02RateContainsLine);
				break;
			}
		}
		
		long gammaRate = binaryIntArrayToDecimal(gammaRateBinary);
		long epsilonRate = binaryIntArrayToDecimal(epsilonRateBinary);
		
		long oxygenRate = binaryIntArrayToDecimal(getColumn(input, oxygenRateIndex));
		long c02Rate = binaryIntArrayToDecimal(getColumn(input, c02RateIndex));
		
		StdOut.println(oxygenRateIndex);
		StdOut.println(c02RateIndex);
		
		StdOut.println(oxygenRate * c02Rate);
	}
	
	public static long binaryIntArrayToDecimal(int[] inputArray)
	{
	    int length = inputArray.length;
		long decimal = 0;
		for (int i = 0; i < length; ++i)
		{
		    decimal += Math.pow(2, i) * inputArray[length - i - 1];
		}
		return decimal;
	}
	
	public static int sumArray(int [] inputArray)
	{
		int sum = 0;
		for (int i = 0; i < inputArray.length; ++i)
		{
			sum += inputArray[i];
		}
		return sum;
	}
	
	public static int findIndexOne(int[] inputArray)
	{
		int index = -1;
		for (int i = 0; i < inputArray.length; ++i)
		{
			if (inputArray[i] == 1)
			{
				return i;
			}
		}
		return index;
	}
	
	public static int[] getColumn(int[][] array, int index){
        int[] column = new int[array.length];
        for(int i=0; i<column.length; i++){
           column[i] = array[i][index];
        }
        return column;
    }
	
	public static int sumProduct(int[] a, int[] b){
		int sum = 0;
		for (int i = 0; i < a.length; ++i)
		{
			sum += (a[i] * b[i]);
		}
		return sum;
	}
	
	public static int[] productArrays(int[] a, int[] b)
	{
		int[] c = new int[a.length];
		for (int i = 0; i < a.length; ++i)
		{
			c[i] = a[i] * b[i];
		}
		return c;
	}
}