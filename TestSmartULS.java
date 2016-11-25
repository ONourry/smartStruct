import java.util.Iterator;
import java.util.Set;

public class TestSmartULS {

	public static void main(String[] args) {
		
		SmartULS<Integer, String> structure = new SmartULS<Integer, String>();
		
		
		structure.add(structure, 1000,"John");
		structure.add(structure, 1001,"Mary");
		structure.add(structure, 1019,"Adam");
		structure.add(structure, 1017,"Harold");
		structure.add(structure, 11200,"Lobster");
		structure.add(structure, 1007,"Ham");
		structure.add(structure, 1005,"Melissandre");
		structure.add(structure, 1002,"Captain Krabs");
		System.out.println("TREE BEHAVIOUR");
		testStructure(structure);
		
		structure.add(structure, 1003,"Patrick");
		structure.add(structure, 1004,"Sandy");
		structure.add(structure, 1006,"Senor Juan");
		System.out.println("Hashtable behavior BEHAVIOUR");
		testStructure(structure);

		structure.remove(structure, 1008,"Patrick");
		structure.remove(structure, 1009,"Sandy");
		structure.remove(structure, 10010,"Senor Juan");
		System.out.println("TREE BEHAVIOUR");
		testStructure(structure);
		
	}
	
	private static void testStructure(SmartULS structure){
		
		Set<Integer> myKeys = structure.allKeys(structure);
		
		Iterator<Integer> it = myKeys.iterator();
		for (Integer key: myKeys){
			System.out.println("Key: " + key + "  Value: " + structure.getValues(structure, key));
		}
		
		System.out.println();
		System.out.println();
		System.out.println("NEXT AND PREVIOUS KEY OF KEY 1001");
		System.out.println(structure.nextKey(structure, 1001));
		System.out.println(structure.prevKey(structure, 1001));
		
		System.out.println();
		System.out.println();
		
		System.out.println("KEYS BETWEEN KEY 1002 AND KEY 1005");
		System.out.println(structure.rangeKey(1004, 11200));
		
		System.out.println();
		System.out.println();
	}

}
