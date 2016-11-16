import java.util.concurrent.ThreadLocalRandom;
import java.util.Hashtable;

//Trees,Hash Tables, AVL tree, Binary Tree, sequence

public class SmartULS {
	
	private int defaultTresholdULS = 1000;
	private LinearHash struct = new LinearHash();
	
	
	public SmartULS(){
		
	}

	public SmartULS(long size){
		
	}
	
	public void setSmartThresholdULS(long size){
		
	}
	
	/**
	 * Randomly generates a new non-existing key of 8 digits
	 * @return (long) key of 8 digits
	 */
	public long generate(){
		
		long randomKey = ThreadLocalRandom.current().nextLong(99999999);
		
		try {
			while (this.struct.contains(randomKey)){
				
				randomKey = ThreadLocalRandom.current().nextLong(99999999);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return randomKey;
	}
	
	/**
	 * return all keys in SmartULS as a sorted sequence
	 * @param uls SmartULS object
	 */
	public void allKeys(SmartULS uls){
	}
	
	/**
	 * add an entry for the given key and value
	 * @param uls	SmartULS object
	 * @param key	(long) key to the value 
	 * @param value	(Object) object to be added with the specified key
	 */
	public void add(SmartULS uls,long key, Object value){
		
	}
	
	
	public void getValues(SmartULS uls, long key){
		
	}
	
	public void nextKey(SmartULS uls, long key){
		
	}
	
	public void prevKey(SmartULS uls, long key){
		
	}
	
	public void rangeKey(long key1, long key2){
		
	}
	
	private void shift(Object structure){
		
	}
}
