import java.util.*;
import java.io.*;

public class TestSmartULS {

	public static void main(String[] args) {
		long startime = System.currentTimeMillis();
		
		SmartULS<Integer, String> structure = new SmartULS<Integer, String>();
		
		String filepath = "D:\\Term 2\\A34_352\\src";
		
		File file1 = new File(filepath + "\\uls_test_file1.txt");
		File file2 = new File(filepath + "\\uls_test_file2.txt");
		File file3 = new File(filepath + "\\uls_test_file3.txt");
		
		
		Scanner sc = null;
		
		try {
			sc = new Scanner(file3);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("Adding keys . . .");
		while(sc.hasNextInt()){
			structure.add(structure, sc.nextInt(), null);
			//System.out.println(sc.nextInt());
		}
		System.out.println("Keys added.");
		System.out.println(System.currentTimeMillis() - startime);
		
		int counter = 0;
		int five_hundredth_key = 0;
		int thousandth_key = 0;
		
		for(Integer key : structure.allKeys(structure)){
			if(counter == 500){
				five_hundredth_key = key;
			} else if(counter == 1000){
				thousandth_key = key;
				break;
			} 
			
			counter++;
			
		}
		
		
		
		System.out.println();
		testStructure(structure,five_hundredth_key,thousandth_key);
		
		System.out.println(System.currentTimeMillis() - startime);
		
/*
88033207
33222085
40352258
53192317
97595844
12521590
54838886
54672331
33271173
33283991
32863024
18575218
33241405
33291617
33214525
73658984
28117252
71626499
36594396
33279441
*/
		
		/*
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
		*/
	}
	
	private static void testStructure(SmartULS structure,int key1,int key2){
		
		//Set<Integer> myKeys = structure.allKeys(structure);
		
		//Iterator<Integer> it = myKeys.iterator();
		/*for (Integer key: myKeys){
			System.out.println("Key: " + key + "  Value: " + structure.getValues(structure, key));
		}*/
		System.out.println(structure.getSize());
		
		System.out.println("NEXT AND PREVIOUS KEY OF KEY " + key1);
		System.out.println(structure.nextKey(structure, key1));
		System.out.println(structure.prevKey(structure, key1));
		
				System.out.println();
		
		System.out.println("KEYS BETWEEN KEY " + key1 + " AND " + key2);
		System.out.println(structure.rangeKey(key1, key2));
		
		System.out.println();
		System.out.println();
		
	}

}
