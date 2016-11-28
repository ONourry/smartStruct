import java.util.*;
import java.io.*;

public class TestSmartULS {

	public static void main(String[] args) {
		SmartULS<Integer, String> structure = new SmartULS<Integer, String>();
		
		//Benchmark testing---------------------------------------------------------
		/*
		System.out.println("BENCHMARK TESTING...");
		System.out.println();
		long startime = System.currentTimeMillis();
		
		
		
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
		System.out.println(System.currentTimeMillis() - startime + " millisecond since start of the program");
		
		int randomKey1 = getRandomKey(structure);
		int randomKey2 = getRandomKey(structure);
		
		while(randomKey1 == randomKey2){
			randomKey2 = getRandomKey(structure);
		}
		
		System.out.println(randomKey1);
		System.out.println(randomKey2);
		
		
		System.out.println();
		testStructure(structure,randomKey1,randomKey2);
		
		System.out.println(System.currentTimeMillis() - startime + " total millisecond for benchmark test");
		*/
		
		//MANUAL TESTING--------------------------------------------------------------------------------
		
		
		int nbrOfObjects = 100;
		int counter = 0;
		while(counter < nbrOfObjects){
			String aString = "Object" + counter;
			structure.add(structure, structure.generate(), aString);
			counter++;
		}
		
		int randomKey1 = getRandomKey(structure);
		int randomKey2 = getRandomKey(structure);
		
		while(randomKey1 == randomKey2){
			randomKey2 = getRandomKey(structure);
		}
		
		System.out.println("FIRST SET OF TEST");
		System.out.println("RANDOM KEYS TO USE FOR TESTING");
		System.out.println(randomKey1);
		System.out.println(randomKey2);
		testStructure(structure,randomKey1,randomKey2);
		
	
		int threshold = 50;
		structure.setSmartThresholdULS(threshold);
		System.out.println("CHANGING THRESHOLD VALUE TO " + threshold);
		
		randomKey1 = getRandomKey(structure);
		randomKey2 = getRandomKey(structure);
		
		while(randomKey1 == randomKey2){
			randomKey2 = getRandomKey(structure);
		}
		
		System.out.println("SECOND SET OF TEST  WITH NEW THRESHOLD");
		System.out.println("RANDOM KEYS TO USE FOR TESTING");
		System.out.println(randomKey1);
		System.out.println(randomKey2);
		testStructure(structure,randomKey1,randomKey2);
		
	}
	
	//Call existing methods from the SmartULS structure to test its functionalities
	private static void testStructure(SmartULS structure,int key1,int key2){
		
		/*Set<Integer> myKeys = structure.allKeys(structure);
		
		System.out.println("Current elements in structure");
		for (Integer key: myKeys){
			System.out.println("Key: " + key + "  Value: " + structure.getValues(structure, key));
		}
		*/
		System.out.println("CURRENT SIZE OF THE STRUCTURE " + structure.getSize());
		
		System.out.println("NEXT AND PREVIOUS KEY OF KEY " + key1);
		System.out.println(structure.nextKey(structure, key1));
		System.out.println(structure.prevKey(structure, key1));
		
		
		System.out.println("NUMBER OF KEYS BETWEEN KEY " + key1 + " AND " + key2);
		System.out.println(structure.rangeKey(key1, key2));
		
		System.out.println();
		System.out.println();
		
	}
	
	//Generate a random key from the SmartULS structure
	private static int getRandomKey(SmartULS structure){
		Random rand = new Random();
		int keyIndex = rand.nextInt(structure.getSize() - 1) + 1;
		int counter = 0;
		int nthKey = 0;
		
		for(Object key : structure.allKeys(structure)){
			if((counter == keyIndex)){
				nthKey = (int) key;
				break;
			}else {
				counter++;
			}
			
		}
		if(nthKey == 0){
			return getRandomKey(structure);
		}
		else{
			return nthKey;
		}
		
		
	}
	

}
