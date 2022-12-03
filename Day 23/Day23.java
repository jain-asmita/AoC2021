import java.util.HashMap;
public class Day23
{
    public static void main(String[] args)
	{
		long startTime = System.nanoTime();
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int yLength = input_byLine.length;
		int xLength = input_byLine[0].length();
		
		HashMap<HashMap<HashMap<String, Integer>, String>, Integer> positions = new HashMap<HashMap<HashMap<String, Integer>, String>, Integer>();
		HashMap<HashMap<String, Integer>, String> loc = new HashMap<HashMap<String, Integer>, String>();
		
		HashMap<String, Integer> costs = new HashMap<String, Integer>();
		costs.put("A", 1);
		costs.put("B", 10);
		costs.put("C", 100);
		costs.put("D", 1000);
		
		HashMap<String, Integer> destinationColumns = new HashMap<String, Integer>();
		destinationColumns.put("A", 3);
		destinationColumns.put("B", 5);
		destinationColumns.put("C", 7);
		destinationColumns.put("D", 9);
		
		for(int y = 0; y < yLength; ++y)
		{
		    for(int x = 0; x < xLength; ++x)
			{
			    if(!input_byLine[y].substring(x, x + 1).equals("#"))
				{
					HashMap<String, Integer> pos = new HashMap<String, Integer>();
					pos.put("x", x);
					pos.put("y", y);
					loc.put(pos, input_byLine[y].substring(x, x + 1));
				};
			}
		}
		
		positions.put(loc, 0);
		int minCost = Integer.MAX_VALUE;
		
		//int count = 0;
		while(positions.size() > 0)
		{
			HashMap<HashMap<HashMap<String, Integer>, String>, Integer> newPositions = new HashMap<HashMap<HashMap<String, Integer>, String>, Integer>();
			for(HashMap.Entry<HashMap<HashMap<String, Integer>, String>, Integer> entry: positions.entrySet())
			{
                HashMap<HashMap<HashMap<String, Integer>, String>, Integer> nextPosForState = getNextPositions(entry.getKey(), entry.getValue(), costs, destinationColumns);
				if(nextPosForState.size() == 0)
				{
					minCost = isFinished(entry.getKey(), destinationColumns) ? (Math.min(minCost, entry.getValue())) : minCost;
				}
				else
				{
					for(HashMap.Entry<HashMap<HashMap<String, Integer>, String>, Integer> entry2: nextPosForState.entrySet())
				    {
				    	//drawMap(entry2.getKey());
				    	//StdOut.println(entry2.getValue());
				    	if(newPositions.containsKey(entry2.getKey()))
				    	{
				    		newPositions.put(entry2.getKey(), Math.min(entry2.getValue(), newPositions.get(entry2.getKey())));
				    	}
				    	else
				    	{
				    		newPositions.put(entry2.getKey(), entry2.getValue());
				    	}
				    }
				}
            }
	        positions = newPositions;		
		}
		
		StdOut.println(minCost);
		long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime * Math.pow(10, -9));
	}
	
	public static boolean isFinished(HashMap<HashMap<String, Integer>, String> state, HashMap<String, Integer> destinationColumns)
	{
		for(HashMap.Entry<String, Integer> cols: destinationColumns.entrySet())
		{
			for(int y = 2; y <= 5; ++y)
			{
				HashMap<String, Integer> checkState = new HashMap<String, Integer>();
				checkState.put("x", cols.getValue());
				checkState.put("y", y);
				if(!state.get(checkState).equals(cols.getKey()))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public static void drawMap(HashMap<HashMap<String, Integer>, String> inputMap)
	{
        for(int y = 0; y < 7; ++y)
		{
			for(int x = 0; x < 13; ++x)
			{
				HashMap<String, Integer> point = new HashMap<String, Integer>();
			    point.put("x", x);
				point.put("y", y);
				if(inputMap.containsKey(point))
				{
					StdOut.print(inputMap.get(point));
				}
				else
				{
					StdOut.print("#");
				}
			}
			StdOut.println("");
		}
	}
	
	public static HashMap<HashMap<HashMap<String, Integer>, String>, Integer> getNextPositions(HashMap<HashMap<String, Integer>, String> state, int cost, HashMap<String, Integer> costs, HashMap<String, Integer> destinationColumns)
	{
		HashMap<HashMap<HashMap<String, Integer>, String>, Integer> newStates = new HashMap<HashMap<HashMap<String, Integer>, String>, Integer>();
		
		for(HashMap.Entry<HashMap<String, Integer>, String> piece: state.entrySet())
		{
			// Hallway
			if(costs.keySet().contains(piece.getValue()) && piece.getKey().get("y").equals(1))
			{
				newStates.putAll(simulateHallway(state, piece.getKey(), piece.getValue(), cost, costs.get(piece.getValue()), destinationColumns.get(piece.getValue())));
			}
			// Rooms
			else if(costs.keySet().contains(piece.getValue()) && piece.getKey().get("y") > 1)
			{
				newStates.putAll(simulateRooms(state, piece.getKey(), piece.getValue(), cost, costs.get(piece.getValue()), destinationColumns.get(piece.getValue())));
			}
		}
		
		return newStates;	
	}
	
	public static HashMap<HashMap<HashMap<String, Integer>, String>, Integer> simulateRooms(HashMap<HashMap<String, Integer>, String> state, HashMap<String, Integer> piecePos, String pieceVal, int cost, int pieceCost, int destinationColumn)
	{
		HashMap<HashMap<HashMap<String, Integer>, String>, Integer> newStates = new HashMap<HashMap<HashMap<String, Integer>, String>, Integer>();
		
		if(piecePos.get("x").equals(destinationColumn))
		{
			boolean pieceAlreadyCorrect = true;
			for(int y = piecePos.get("y"); y <= 5; ++y)
			{
				HashMap<String, Integer> checkState = new HashMap<String, Integer>();
				checkState.put("x", destinationColumn);
				checkState.put("y", y);
				
				if(!state.get(checkState).equals(pieceVal))
				{
					pieceAlreadyCorrect = false;
					break;
				}
			}
			if(pieceAlreadyCorrect)
			{
				return newStates;
			}
		}
		
		if(piecePos.get("y") >= 3)
	    {
			boolean pieceStuck = false;
			for(int y = piecePos.get("y") - 1; y > 1; --y)
		    {
				HashMap<String, Integer> checkState = new HashMap<String, Integer>();
			    checkState.put("x", piecePos.get("x"));
			    checkState.put("y", y);
				
				if(!state.get(checkState).equals("."))
				{
					pieceStuck = true;
					break;
				}
			}
			if(pieceStuck)
			{
				return newStates;
			}
		}
		
		// Go left
		int nMoves = 0;
		int x = piecePos.get("x");
		int y = piecePos.get("y");
		while(y != 1)
		{
			nMoves++;
			y--;
		}
		
		HashMap<String, Integer> checkState = new HashMap<String, Integer>();
		checkState.put("x", x - 1);
		checkState.put("y", y);
		
		while(state.get(checkState).equals(".") || state.get(checkState).equals("-"))
		{
			if(state.get(checkState).equals("."))
			{
				HashMap<HashMap<String, Integer>, String> states = new HashMap<HashMap<String, Integer>, String>();
		        states.putAll(state);
				states.put(checkState, pieceVal);
				states.put(piecePos, ".");
				newStates.put(states, cost + nMoves * pieceCost);
			}
			x--;
			nMoves++;
			checkState.put("x", x);
		    checkState.put("y", y);
			if(x == 0)
			{
				break;
			}
		
		}
		
		// Go right
		nMoves = 0;
		x = piecePos.get("x");
		y = piecePos.get("y");

		while(y != 1)
		{
			nMoves++;
			y--;
		}
		
		checkState.put("x", x + 1);
		checkState.put("y", y);
		
		while(state.get(checkState).equals(".") || state.get(checkState).equals("-"))
		{
			if(state.get(checkState).equals("."))
			{
				HashMap<HashMap<String, Integer>, String> states = new HashMap<HashMap<String, Integer>, String>();
		        states.putAll(state);
				states.put(checkState, pieceVal);
				states.put(piecePos, ".");
				newStates.put(states, cost + nMoves * pieceCost);
			}
			x++;
			nMoves++;
			checkState.put("x", x);
		    checkState.put("y", y);
			if(x == 12)
			{
				break;
			}
		}
		
		return newStates;
	}
	
	
	public static HashMap<HashMap<HashMap<String, Integer>, String>, Integer> simulateHallway(HashMap<HashMap<String, Integer>, String> state, HashMap<String, Integer> piecePos, String pieceVal, int cost, int pieceCost, int destinationColumn)
	{
		HashMap<HashMap<HashMap<String, Integer>, String>, Integer> newStates = new HashMap<HashMap<HashMap<String, Integer>, String>, Integer>();
		HashMap<HashMap<String, Integer>, String> states = new HashMap<HashMap<String, Integer>, String>();
		states.putAll(state);
		
	    int nMoves = 0;
		for(int y = 2; y <= 5; ++y)
		{
			HashMap<String, Integer> checkState = new HashMap<String, Integer>();
			checkState.put("x", destinationColumn);
			checkState.put("y", y);
			
			if(!state.get(checkState).equals(pieceVal) && !state.get(checkState).equals("."))
			{
				return newStates;
			}
		}
		
		
		
		if(destinationColumn < piecePos.get("x"))
		{
			for(int x = piecePos.get("x") - 1; x >= destinationColumn; --x)
			{
				HashMap<String, Integer> stateToCheck = new HashMap<String, Integer>();
				stateToCheck.put("x", x);
				stateToCheck.put("y", 1);
				nMoves++;
				if(!(state.get(stateToCheck).equals(".") || state.get(stateToCheck).equals("-")))
				{
					return newStates;
				}
			}
		}
		else
		{
			for(int x = piecePos.get("x") + 1; x <= destinationColumn; ++x)
			{
				HashMap<String, Integer> stateToCheck = new HashMap<String, Integer>();
				stateToCheck.put("x", x);
				stateToCheck.put("y", 1);
				nMoves++;
				if(!(state.get(stateToCheck).equals(".") || state.get(stateToCheck).equals("-")))
				{
					return newStates;
				}
			}
		}
			
		for(int y = 2; y <= 6; ++y)
		{
			HashMap<String, Integer> stateToCheck = new HashMap<String, Integer>();
		    stateToCheck.put("x", destinationColumn);
		    stateToCheck.put("y", y);
			nMoves++;

			if(y == 6 || !state.get(stateToCheck).equals(".") && y > 2)
			{
				HashMap<String, Integer> stateToAdd = new HashMap<String, Integer>();
		        stateToAdd.put("x", destinationColumn);
		        stateToAdd.put("y", y - 1);
				
				states.put(stateToAdd, pieceVal);
				states.put(piecePos, ".");
				newStates.put(states, cost + (nMoves - 1) * pieceCost);
				return newStates;

			}
			else if(!state.get(stateToCheck).equals("."))
			{
				return newStates;
			}
		}
		
        return newStates;    
	}
}