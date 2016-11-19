import java.util.*;

//Trees,Hash Tables, AVL tree, Binary Tree, sequence

public class SmartULS {
	
	private int tresholdULS = 1000;
	private Object structure;
	
	
	public SmartULS(){
		(LinearHash)this.structure = new LinearHash();
	}

	
	public SmartULS(long size){
		if(size >= this.tresholdULS){
			(Hashtable)this.structure = new Hashtable(size);
		}
		
		else{
			(LinearHash)this.structure = new LinearHash(size);
		}
	}
	
	public void setSmartThresholdULS(long size){
		this.tresholdULS = size;
	}
	
	/**
	 * Randomly generates a new non-existing key of 8 digits
	 * @return (long) key of 8 digits
	 */
	public long generate(){
		
		long randomKey = ThreadLocalRandom.current().nextLong(99999999);
		
		try {
			
			if(this.structure.contains(randomKey)){
				while (this.structure.contains(randomKey)){
					
					randomKey = ThreadLocalRandom.current().nextLong(99999999);
				}
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
	public Set allKeys(SmartULS uls){
		Set<Key> keys;
		if(this.structure instanceof Hashtable){
			keys = (Hashtable)this.structure.keySet()
		}
		
		else if(this.structure instanceof LinearHash){
			keys = (LinearHash)this.structure.keySet();
		}
		
		
	}
	
	/**
	 * add an entry for the given key and value
	 * @param uls	SmartULS object
	 * @param key	(long) key to the value 
	 * @param value	(Object) object to be added with the specified key
	 */
	public void add(SmartULS uls,long key, Object value){
		try{
			this.structure.put(key,value);
			
			if(this.structure instanceof LinearHash && this.structure.size() >= this.tresholdULS){
				this.shift();
			}
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void remove(SmartULS, long key, Object value){
		
		try{
			this.structure.remove(key);
			
			if(this.structure instanceof Hashtable && this.structure.size() < this.tresholdULS){
				this.shift();
			}
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Object getValues(SmartULS uls, long key){
		return this.structure.get(key);
	}
	
	
	public Key nextKey(SmartULS uls, long key){
		
		Set<Key> myKeys = this.structure.keySet();
		
		Iterator<Key> iterator = myKeys.iterator();
		
		//Check with compiler how to get key
	}
	
	public void prevKey(SmartULS uls, long key){
		
	}
	
	public void rangeKey(long key1, long key2){
		
	}
	
	private Object shift(Object structure){
		
		//Object newStructure;
		
		if(structure instanceof Hashtable){
			
			LinearHash newStructure = new LinearHash(structure.size());
			
			//Copy everything
			
			return newStructure
		}
		
		else if(structure instanceof LinearHash){
			
			Hashtable newStructure = new Hashtable(structure.size());
			
			//Copy everything
			
			return newStructure
		}
		
		else{
			throw new Exception("Unrecognized structure");
		}
		//return newStructure
	}
}
