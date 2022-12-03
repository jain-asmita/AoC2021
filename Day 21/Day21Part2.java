import java.util.HashMap;

public class Day21Part2
{
    public static void main(String[] args)
	{
		long startTime = System.nanoTime();
	    HashMap<HashMap<String, Integer>, Long> state = new HashMap<HashMap<String, Integer>, Long>();
		HashMap<String, Integer> init = new HashMap<String, Integer>();
		init.put("p1Score", 0);
		init.put("p2Score", 0);
		init.put("p1Pos", 3);
		init.put("p2Pos", 1);
		state.put(init, (long) 1);
		
		HashMap<Integer, Integer> roll = new HashMap<Integer, Integer>();
		roll.put(3, 1);
		roll.put(4, 3);
		roll.put(5, 6);
		roll.put(6, 7);
		roll.put(7, 6);
		roll.put(8, 3);
		roll.put(9, 1);
		
		int i = 0;
		long nWinsP1 = 0;
		long nWinsP2 = 0;
		
		while(state.size() > 0)
		{
			int pTurn = (i % 2) + 1;
			int pNotTurn = ((i + 1)% 2) + 1;
			HashMap<HashMap<String, Integer>, Long> newState = new HashMap<HashMap<String, Integer>, Long>();
			for(HashMap.Entry<HashMap<String, Integer>, Long> stateEntry: state.entrySet())
			{
				for(HashMap.Entry<Integer, Integer> rollEntry: roll.entrySet())
				{
					boolean targetReached = false;
					HashMap<String, Integer> turn = new HashMap<String, Integer>();
					turn.put("p" + pTurn + "Pos", ((stateEntry.getKey().get("p" + pTurn + "Pos") + rollEntry.getKey()) % 10));
					turn.put("p" + pTurn + "Score", stateEntry.getKey().get("p" + pTurn + "Score") + turn.get("p" + pTurn + "Pos") + 1);
					turn.put("p" + pNotTurn + "Pos", stateEntry.getKey().get("p" + pNotTurn + "Pos"));
					turn.put("p" + pNotTurn + "Score", stateEntry.getKey().get("p" + pNotTurn + "Score"));
					
					if(turn.get("p" + pTurn + "Score").equals(21) || turn.get("p" + pTurn + "Score") > 21)
					{
						targetReached = true;
						if(pTurn == 1)
						{
							nWinsP1 += stateEntry.getValue() * rollEntry.getValue();
						}
						else
						{
							nWinsP2 += stateEntry.getValue() * rollEntry.getValue();
						}
					}
					
					if(newState.containsKey(turn) && !targetReached)
					{
						newState.put(turn, newState.get(turn) + stateEntry.getValue() * rollEntry.getValue());
					}
					else if(!targetReached)
					{
						newState.put(turn, stateEntry.getValue() * rollEntry.getValue());
					}
				}
			}
			state = newState;		
			++i;
			System.out.println(state.size());
		}
		
		System.out.println(nWinsP1);
		System.out.println(nWinsP2);
		
		long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime * Math.pow(10, -9));
	}
}