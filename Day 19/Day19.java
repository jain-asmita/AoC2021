import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class Day19
{
    public static void main(String[] args)
	{
		
		HashSet<HashMap<String, String>> transformations = new HashSet<HashMap<String, String>>();
		HashMap<String, String> xyz = new HashMap<String, String>()
        {{
             put("x", "x");
             put("y", "y");
             put("z", "z");
        }};
		HashMap<String, String> xzy = new HashMap<String, String>()
        {{
             put("x", "x");
             put("y", "z");
             put("z", "y");
        }};
		HashMap<String, String> yzx = new HashMap<String, String>()
        {{
             put("x", "y");
             put("y", "z");
             put("z", "x");
        }};
		HashMap<String, String> yxz = new HashMap<String, String>()
        {{
             put("x", "y");
             put("y", "x");
             put("z", "z");
        }};
		HashMap<String, String> zxy = new HashMap<String, String>()
        {{
             put("x", "z");
             put("y", "x");
             put("z", "y");
        }};
		HashMap<String, String> zyx = new HashMap<String, String>()
        {{
             put("x", "z");
             put("y", "y");
             put("z", "x");
        }};
        transformations.add(xyz);
		transformations.add(xzy);
		transformations.add(yzx);
		transformations.add(yxz);
		transformations.add(zxy);
		transformations.add(zyx);
		
		HashSet<HashMap<String, Integer>> multipliers = new HashSet<HashMap<String, Integer>>();
		HashMap<String, Integer> m000 = new HashMap<String, Integer>()
        {{
             put("x", 1);
             put("y", 1);
             put("z", 1);
        }};
		HashMap<String, Integer> m001 = new HashMap<String, Integer>()
        {{
             put("x", 1);
             put("y", 1);
             put("z", -1);
        }};
		HashMap<String, Integer> m010 = new HashMap<String, Integer>()
        {{
             put("x", 1);
             put("y", -1);
             put("z", 1);
        }};
		HashMap<String, Integer> m011 = new HashMap<String, Integer>()
        {{
             put("x", 1);
             put("y", -1);
             put("z", -1);
        }};
		HashMap<String, Integer> m100 = new HashMap<String, Integer>()
        {{
             put("x", -1);
             put("y", 1);
             put("z", 1);
        }};
		HashMap<String, Integer> m101 = new HashMap<String, Integer>()
        {{
             put("x", -1);
             put("y", 1);
             put("z", -1);
        }};
		HashMap<String, Integer> m110 = new HashMap<String, Integer>()
        {{
             put("x", -1);
             put("y", -1);
             put("z", 1);
        }};
		HashMap<String, Integer> m111 = new HashMap<String, Integer>()
        {{
             put("x", -1);
             put("y", -1);
             put("z", -1);
        }};
		multipliers.add(m000);
		multipliers.add(m001);
		multipliers.add(m010);
		multipliers.add(m011);
		multipliers.add(m100);
		multipliers.add(m101);
		multipliers.add(m110);
		multipliers.add(m111);
		
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n\r\n--- scanner [0-9]+ ---\r\n");
		int nScanners = input_byLine.length;
		ArrayList<ArrayList<HashMap<String, Integer>>> allData = new ArrayList<ArrayList<HashMap<String, Integer>>>();
		
		for(int scannerN = 0; scannerN < nScanners; ++scannerN)
		{
			String[] beaconCoords = input_byLine[scannerN].split("\r\n");
			ArrayList<HashMap<String, Integer>> scannerData = new ArrayList<HashMap<String, Integer>>();
			int nBeacons = beaconCoords.length;
			for(int beaconN = 0; beaconN < nBeacons; ++beaconN)
			{
				HashMap<String, Integer> beaconData = new HashMap<String, Integer>();
				String[] beaconCoordsArray = beaconCoords[beaconN].split(",");
				int x = Integer.parseInt(beaconCoordsArray[0]);
				int y = Integer.parseInt(beaconCoordsArray[1]);
				int z = Integer.parseInt(beaconCoordsArray[2]);
				beaconData.put("x", x);
				beaconData.put("y", y);
				beaconData.put("z", z);
				scannerData.add(beaconData);
			}
			allData.add(scannerData);
		}
		
		ArrayList<ArrayList<ArrayList<HashMap<String, Integer>>>> distances = new ArrayList<ArrayList<ArrayList<HashMap<String, Integer>>>>();
		for(int scannerN = 0; scannerN < nScanners; ++scannerN)
		{
			ArrayList<ArrayList<HashMap<String, Integer>>> scannerDistance = new ArrayList<ArrayList<HashMap<String, Integer>>>();
			for(int beaconN = 0; beaconN < allData.get(scannerN).size(); ++beaconN)
			{
				ArrayList<HashMap<String, Integer>> beaconDistance = new ArrayList<HashMap<String, Integer>>();
				for(int secondBeaconN = 0; secondBeaconN < allData.get(scannerN).size(); ++secondBeaconN)
				{
					HashMap<String, Integer> distance = new HashMap<String, Integer>();
					distance.put("x", allData.get(scannerN).get(beaconN).get("x") - allData.get(scannerN).get(secondBeaconN).get("x"));
					distance.put("y", allData.get(scannerN).get(beaconN).get("y") - allData.get(scannerN).get(secondBeaconN).get("y"));
					distance.put("z", allData.get(scannerN).get(beaconN).get("z") - allData.get(scannerN).get(secondBeaconN).get("z"));
					beaconDistance.add(distance);
				}
				scannerDistance.add(beaconDistance);
			}
			distances.add(scannerDistance);
		}
		
		
		ArrayList<ArrayList<ArrayList<HashMap<String, Integer>>>> commonPointsSet = new ArrayList<ArrayList<ArrayList<HashMap<String, Integer>>>>();
		ArrayList<ArrayList<ArrayList<HashMap<String, Integer>>>> commonPointsMapToSet = new ArrayList<ArrayList<ArrayList<HashMap<String, Integer>>>>();
		
		for(int l1 = 0; l1 < nScanners; l1++)
		{
			ArrayList<ArrayList<HashMap<String, Integer>>> list1 = new ArrayList<ArrayList<HashMap<String, Integer>>>();
			ArrayList<ArrayList<HashMap<String, Integer>>> list1MapTo = new ArrayList<ArrayList<HashMap<String, Integer>>>();
			for(int l2 = 0; l2 < nScanners; l2++)
			{
				ArrayList<HashMap<String, Integer>> list2 = new ArrayList<HashMap<String, Integer>>();
				ArrayList<HashMap<String, Integer>> list2MapTo = new ArrayList<HashMap<String, Integer>>();
				for(int i = 0; i < distances.get(l1).size(); ++i)
		        {
					for(int j = 0; j < distances.get(l2).size(); ++j)
		        	{
					    int icount = 0;
					    here:
		        	    for(int b = 0; b < distances.get(l1).get(i).size(); ++b)
		        	    {
		        	    	    for(int b2 = 0; b2 < distances.get(l2).get(j).size(); ++b2)
                                {
		        	    			if(comparePoints(distances.get(l1).get(i).get(b), distances.get(l2).get(j).get(b2)))
		        	    			{
		        	    				icount++;
					    				if(icount >= 11)
		        	                    {
		        	                    	list2.add(allData.get(l1).get(i));
					                    	list2MapTo.add(allData.get(l2).get(j));
					    					break here;
		        	                    }
		        	    			}
                                }							
		        	    	
		        	    }
					}
		        }
				list1.add(list2);
				list1MapTo.add(list2MapTo);
			}
			commonPointsSet.add(list1);
			commonPointsMapToSet.add(list1MapTo);
		}
		
		int totalPoints = 0;
		int commonPoints = 0;
		for(int i = 0; i < nScanners; ++i)
		{
			totalPoints += allData.get(i).size();
			for(int j = 0; j < nScanners; ++j)
			{			
				if(i < j)
				{
					commonPoints += commonPointsSet.get(i).get(j).size();
				}
				
			}
		}
		
		boolean[] visitedScanner = new boolean[nScanners];
		int currentScannerN = 0;
		
		ArrayList<ArrayList<HashMap<String, Integer>>> allDataTransformed = new ArrayList<ArrayList<HashMap<String, Integer>>>();
		for(int i = 0; i < nScanners; ++i)
		{
			ArrayList<HashMap<String, Integer>> list = new ArrayList<HashMap<String, Integer>>();
			for(int j = 0; j < allData.get(i).size(); ++j)
			{
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				list.add(map);
			}
			allDataTransformed.add(list);
		}
		
		
		allDataTransformed.set(0, allData.get(0));
		ArrayList<HashMap<String, Integer>> locs = new ArrayList<HashMap<String, Integer>>();
		iterateAll(locs, 0, visitedScanner, allDataTransformed, commonPointsSet, commonPointsMapToSet, allData, transformations, multipliers);
		
		HashSet<HashMap<String, Integer>> set = new HashSet<HashMap<String, Integer>>();
		for(int i = 0; i < allDataTransformed.size(); ++i)
		{
			set.addAll(allDataTransformed.get(i));
		}
		
		StdOut.println(set.size());
		
		int maxDistance = 0;
		for(int i = 0; i < locs.size(); ++i)
		{
			for(int j = 0; j < locs.size(); ++j)
			{
				int manhattanDistance = Math.abs(locs.get(i).get("x") - locs.get(j).get("x")) + Math.abs(locs.get(i).get("y") - locs.get(j).get("y")) + Math.abs(locs.get(i).get("z") - locs.get(j).get("z"));
				maxDistance = Math.max(manhattanDistance, maxDistance);
			}
		}
		
		StdOut.println(maxDistance);

	}
	
	public static void iterateAll(ArrayList<HashMap<String, Integer>> locs, int currentScannerN, boolean[] visitedScanner, ArrayList<ArrayList<HashMap<String, Integer>>> allDataTransformed, ArrayList<ArrayList<ArrayList<HashMap<String, Integer>>>> commonPointsSet, ArrayList<ArrayList<ArrayList<HashMap<String, Integer>>>> commonPointsMapToSet, ArrayList<ArrayList<HashMap<String, Integer>>> allData, HashSet<HashMap<String, String>> transformations, HashSet<HashMap<String, Integer>> multipliers)
	{
		if(areAllVisited(visitedScanner))
		{
			return;
		}
		
		int nScanners = allDataTransformed.size();
		for(int i = 0; i < nScanners; ++i)
		{
			if(i != currentScannerN && commonPointsSet.get(currentScannerN).get(i).size() > 0 && !visitedScanner[i])
			{
				visitedScanner[i] = true;
				for(int j = 0; j < commonPointsSet.get(currentScannerN).get(i).size(); ++j)
				{
					int index = allData.get(currentScannerN).indexOf(commonPointsSet.get(currentScannerN).get(i).get(j));
					commonPointsSet.get(currentScannerN).get(i).set(j, allDataTransformed.get(currentScannerN).get(index));
				}
				allDataTransformed.set(i, findTransformation(commonPointsSet.get(currentScannerN).get(i), commonPointsMapToSet.get(currentScannerN).get(i), allData.get(i), transformations, multipliers));
				locs.add(findTranslation(commonPointsSet.get(currentScannerN).get(i), commonPointsMapToSet.get(currentScannerN).get(i), allData.get(i), transformations, multipliers));
				iterateAll(locs, i, visitedScanner, allDataTransformed, commonPointsSet, commonPointsMapToSet, allData, transformations, multipliers);
			}
		}
	}
	
	public static boolean areAllVisited(boolean[] array)
	{
		for(int i = 0; i < array.length; ++i)
		{
			if(!array[i])
			{
				return false;
			}
		}
		return true;
	}
	
	public static ArrayList<HashMap<String, Integer>> findTransformation(ArrayList<HashMap<String, Integer>> one, ArrayList<HashMap<String, Integer>> two, ArrayList<HashMap<String, Integer>> allPointstwo, HashSet<HashMap<String, String>> transformations, HashSet<HashMap<String, Integer>> multipliers)
	{
		HashMap<String, HashMap<String, Integer>> result = new HashMap<String, HashMap<String, Integer>>();
		ArrayList<HashMap<String, Integer>> transformedPoints = new ArrayList<HashMap<String, Integer>>();
		
		for(HashMap<String, String> transformation: transformations)
		{
			for(HashMap<String, Integer> multiplier: multipliers)
			{
				HashSet<HashMap<String, Integer>> translations = new HashSet<HashMap<String, Integer>>();
				for(int i = 0; i < one.size(); ++i)
				{
					HashMap<String, Integer> map = new HashMap<String, Integer>();
					map.put("x", one.get(i).get("x") - two.get(i).get(transformation.get("x")) * multiplier.get("x"));
					map.put("y", one.get(i).get("y") - two.get(i).get(transformation.get("y")) * multiplier.get("y"));
					map.put("z", one.get(i).get("z") - two.get(i).get(transformation.get("z")) * multiplier.get("z"));
                    translations.add(map);					
				}
				
				if(translations.size() == 1)
				{
					for(HashMap<String, Integer> temp: translations)
					{
						for(int j = 0; j < allPointstwo.size(); ++j)
						{
							int x = allPointstwo.get(j).get(transformation.get("x")) * multiplier.get("x") + temp.get("x");
							int y = allPointstwo.get(j).get(transformation.get("y")) * multiplier.get("y") + temp.get("y");
							int z = allPointstwo.get(j).get(transformation.get("z")) * multiplier.get("z") + temp.get("z");
							HashMap<String, Integer> map2 = new HashMap<String, Integer>();
							map2.put("x", x);
							map2.put("y", y);
							map2.put("z", z);
							transformedPoints.add(map2);
						}
						return transformedPoints;
					}
				}
			}
		}
		return null;
	}
	
	public static boolean comparePoints(HashMap<String, Integer> one, HashMap<String, Integer> two)
	{
		if (!(Math.abs(one.get("x")) == Math.abs(two.get("x")) || Math.abs(one.get("x")) == Math.abs(two.get("y")) || Math.abs(one.get("x")) == Math.abs(two.get("z"))))
		{
			return false;
		}
		if (!(Math.abs(one.get("y")) == Math.abs(two.get("x")) || Math.abs(one.get("y")) == Math.abs(two.get("y")) || Math.abs(one.get("y")) == Math.abs(two.get("z"))))
		{
			return false;
		}
		if (!(Math.abs(one.get("z")) == Math.abs(two.get("x")) || Math.abs(one.get("z")) == Math.abs(two.get("y")) || Math.abs(one.get("z")) == Math.abs(two.get("z"))))
		{
			return false;
		}
		return true;
	}
	
	public static HashMap<String, Integer> findTranslation(ArrayList<HashMap<String, Integer>> one, ArrayList<HashMap<String, Integer>> two, ArrayList<HashMap<String, Integer>> allPointstwo, HashSet<HashMap<String, String>> transformations, HashSet<HashMap<String, Integer>> multipliers)
	{
		HashMap<String, HashMap<String, Integer>> result = new HashMap<String, HashMap<String, Integer>>();
		
		ArrayList<HashMap<String, Integer>> transformedPoints = new ArrayList<HashMap<String, Integer>>();
		
		for(HashMap<String, String> transformation: transformations)
		{
			for(HashMap<String, Integer> multiplier: multipliers)
			{
				HashSet<HashMap<String, Integer>> translations = new HashSet<HashMap<String, Integer>>();
				for(int i = 0; i < one.size(); ++i)
				{
					HashMap<String, Integer> map = new HashMap<String, Integer>();
					map.put("x", one.get(i).get("x") - two.get(i).get(transformation.get("x")) * multiplier.get("x"));
					map.put("y", one.get(i).get("y") - two.get(i).get(transformation.get("y")) * multiplier.get("y"));
					map.put("z", one.get(i).get("z") - two.get(i).get(transformation.get("z")) * multiplier.get("z"));
                    translations.add(map);					
				}
				
				if(translations.size() == 1)
				{
					for(HashMap<String, Integer> temp: translations)
					{
						return temp;
					}
				}
			}
		}
		return null;
	}
}