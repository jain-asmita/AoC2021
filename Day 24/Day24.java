import java.util.HashMap;
import java.util.ArrayList;

public class Day24
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		ArrayList<HashMap<String, String>> instructions = new  ArrayList<HashMap<String, String>>();
		
		for(int i = 0; i < input_byLine.length; ++i)
		{
		    String[] instruction = input_byLine[i].split(" ");
			HashMap<String, String> instructionMap = new HashMap<String, String>();
			instructionMap.put("operation", instruction[0]);
			instructionMap.put("operand1", instruction[1]);
			if(instruction.length > 2)
			{
			    instructionMap.put("operand2", instruction[2]);
			}
			instructions.add(instructionMap);
		}
		
		HashMap<String, Integer> variableValues = new HashMap<String, Integer>();
		variableValues.put("w", 0);
		variableValues.put("x", 0);
		variableValues.put("y", 0);
		variableValues.put("z", 0);
		
		long[] monad = new long[1];
		monad[0] = 99999999999999L;
		long[] monadCopy = new long[1];
		monadCopy[0] = 99999999999999L;
		
		loop:
		while(true)
		{
		    for(int line = 0; line < input_byLine.length; ++line)
			{
				monad[0] = monadCopy[0];
			    HashMap<String, String> instruction = instructions.get(line);
				if(instruction.get("operation").equals("inp"))
				{
				    input(instruction, variableValues, monad);
				}
	            else if(instruction.get("operation").equals("add"))
				{
				    add(instruction, variableValues);
				}
				else if(instruction.get("operation").equals("mod"))
				{
				    mod(instruction, variableValues);
				}
				else if(instruction.get("operation").equals("div"))
				{
				    div(instruction, variableValues);
				}
				else if(instruction.get("operation").equals("mul"))
				{
				    mul(instruction, variableValues);
				}
				else
				{
				    eql(instruction, variableValues);
				}
				//StdOut.println(instruction);
				//StdOut.println(variableValues);
			}

			if(variableValues.get("z").equals(0))
			{
				break loop;
			}
			else
			{
				decrementMonad(monadCopy);
				variableValues.put("w", 0);
		        variableValues.put("x", 0);
		        variableValues.put("y", 0);
		        variableValues.put("z", 0);
			}
			StdOut.println(monadCopy[0]);
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
	
	public static void input(HashMap<String, String> instruction, HashMap<String, Integer> variableValues, long[] monad)
	{
		String monadString = String.valueOf(monad[0]);
	    int num = Integer.parseInt(monadString.substring(0, 1));
		variableValues.put(instruction.get("operand1"), num);
		monad[0] = (monadString.length() == 1) ? 0L : Long.parseLong(monadString.substring(1, monadString.length()));
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