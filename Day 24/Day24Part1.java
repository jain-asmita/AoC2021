import java.util.HashMap;
import java.util.ArrayList;

public class Day24Part1
{
    public static void main(String[] args)
	{
		
		int[][] instructions =
		{
	        {1,10,2},
			{1,14,13},
			{1,14,13},
			{26,-13,9},
			{1,10,15},
			{26,-13,3},
			{26,-7,6},
			{1,11,5},
			{1,10,16},
			{1,13,1},
			{26,-4,6},
			{26,-9,3},
			{26,-13,7},
			{26,-9,9}
		};

		int x = 0;
		int y = 0;
		int z = 0;
		int w = 0;
		
		long[] monad = new long[1];
		monad[0] = 93997999296915L;
		long[] monadCopy = new long[1];
		monadCopy[0] = 93997999296915L;
		
		loop:
		while(true)
		{
			monad[0] = monadCopy[0];
		    for(int i = 0; i < 14; ++i)
			{
				w = input(monad);
				x = (z % 26) + instructions[i][1];
				z = z / instructions[i][0];
				x = (x == w) ? 0 : 1;
				z = z * (25 * x + 1);
				y = (w + instructions[i][2]) * x;
				z = z + y;
			}
            
			StdOut.println(z + " " + monadCopy[0]);
			if(z == 0)
			{
				break loop;
			}
			else
			{
				decrementMonad(monadCopy);
				x = 0;
		        y = 0;
		        z = 0;
		        w = 0;
			}
		}
		StdOut.println(monad[0]);
	}
	
	public static void decrementMonad(long[] monad)
	{
		while(true)
		{
			monad[0]--;
			if(!String.valueOf(monad[0]).contains("0"))
			{
				break;
			}
		}
	}
	
	public static int input(long[] monad)
	{
		String monadString = String.valueOf(monad[0]);
	    int num = Integer.parseInt(monadString.substring(0, 1));
		monad[0] = (monadString.length() == 1) ? 0L : Long.parseLong(monadString.substring(1, monadString.length()));
		return num;
	}
	
	public static void add(HashMap<String, String> instruction, HashMap<String, Integer> variableValues)
	{
		int numToAdd = (variableValues.containsKey(instruction.get("operand2"))) ? variableValues.get(instruction.get("operand2")) : Integer.parseInt(instruction.get("operand2"));
	    variableValues.put(instruction.get("operand1"), variableValues.get(instruction.get("operand1")) + numToAdd);
	}
	
	public static void mul(HashMap<String, String> instruction, HashMap<String, Integer> variableValues)
	{
		int numToMul = (variableValues.containsKey(instruction.get("operand2"))) ? variableValues.get(instruction.get("operand2")) : Integer.parseInt(instruction.get("operand2"));
	    variableValues.put(instruction.get("operand1"), variableValues.get(instruction.get("operand1")) * numToMul);
	}
	
	public static void div(HashMap<String, String> instruction, HashMap<String, Integer> variableValues)
	{
		int numToDiv = (variableValues.containsKey(instruction.get("operand2"))) ? variableValues.get(instruction.get("operand2")) : Integer.parseInt(instruction.get("operand2"));
	    variableValues.put(instruction.get("operand1"), variableValues.get(instruction.get("operand1")) / numToDiv);
	}
	
	public static void mod(HashMap<String, String> instruction, HashMap<String, Integer> variableValues)
	{
		int numToMod = (variableValues.containsKey(instruction.get("operand2"))) ? variableValues.get(instruction.get("operand2")) : Integer.parseInt(instruction.get("operand2"));
	    variableValues.put(instruction.get("operand1"), variableValues.get(instruction.get("operand1")) % numToMod);
	}
	
	public static void eql(HashMap<String, String> instruction, HashMap<String, Integer> variableValues)
	{
		int numToCompare = (variableValues.containsKey(instruction.get("operand2"))) ? variableValues.get(instruction.get("operand2")) : Integer.parseInt(instruction.get("operand2"));
	    int result = (variableValues.get(instruction.get("operand1"))).equals(numToCompare) ? 1 : 0;
		variableValues.put(instruction.get("operand1"), result);
	}
}