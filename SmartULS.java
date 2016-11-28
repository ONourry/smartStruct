import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//Trees,Hash Tables, AVL tree, Binary Tree, sequence

public class SmartULS<Key,Value> {

	private int tresholdULS = 3000;
	private TreeMap tree;
	private HashMap hashtable;
	private int size;

	public SmartULS() {
		this.tree = new TreeMap<Key,Value>();
		this.size = this.tree.size();
	}

	public SmartULS(int size) {
		if (size >= this.tresholdULS) {
			this.hashtable = new HashMap<Key,Value>(size);
			this.size = this.hashtable.size();
		}

		else {
			this.tree = new TreeMap<Key,Value>();
			this.size = this.tree.size();
		}
	}

	/**
	 * Set the threshold value at which the behavior of the smartULS should change
	 * If the size of the smartULS is bigger than the new threshold value, behave like a hashtable
	 * otherwise behave like a tree
	 * @param newThreshold
	 */
	public void setSmartThresholdULS(int newThreshold) {
		this.tresholdULS = newThreshold;
		
		//Current size is bigger than the threshold and we're using a tree, shift to hashtable
		if(this.tresholdULS <= this.size && this.tree != null){
			this.shift();
		}
		
		else if(this.tresholdULS > this.size && this.hashtable != null) {
			this.shift();
		}
	}

	/**
	 * Randomly generates a new non-existing key of 8 digits
	 * 
	 * @return (Integer) key of 8 digits
	 */
	public int generate() {

		int randomKey = ThreadLocalRandom.current().nextInt(99999999);
		
		if(this.tree != null){
			if(this.tree.containsKey(randomKey)){
				while(this.tree.containsKey(randomKey)){
					randomKey = ThreadLocalRandom.current().nextInt(99999999);
				}
			}
		}
		else if(this.hashtable != null){
			if(this.hashtable.containsKey(randomKey)){
				while(this.hashtable.containsKey(randomKey)){
					randomKey = ThreadLocalRandom.current().nextInt(99999999);
				}
			}
		}
		
		else {
			try {
				throw new Exception("Cannot generate new key for empty structure");
			} catch (Exception e) {
				e.printStackTrace();;
			}
		}

		return randomKey;
	}

	/**
	 * return all keys in SmartULS as a sorted sequence
	 * 
	 * @param uls
	 *            SmartULS object
	 */
	public Set<Key> allKeys(SmartULS uls) {
		
		if(this.tree == null && this.hashtable == null){
			try {
				throw new Exception("Cannot add element to undefined structure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (this.tree != null) {
			return this.tree.keySet();
		}

		else{
			Set<Key> keys = this.hashtable.keySet();
			
			TreeSet<Key> treeSet = new TreeSet<Key>();
			treeSet.addAll(keys);
			
			keys = treeSet;
			return keys;
		}
	}

	/**
	 * add an entry for the given key and value
	 * 
	 * @param structure
	 *            SmartULS object
	 * @param key
	 *            (Integer) key to the value
	 * @param value
	 *            (Object) object to be added with the specified key
	 */
	public void add(SmartULS structure, Key key, Value value) {

		if (this.tree != null) {
			this.tree.put(key, value);
			this.size++;
			if (this.size >= this.tresholdULS){
				this.shift();
			}
		}

		else if (this.hashtable != null) {
			this.hashtable.put(key, value);
			this.size++;
		}
		
		else {
			try {
				throw new Exception("Cannot add element to undefined structure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Remove the value map to the specified key
	 * @param uls
	 * @param key
	 * @param value
	 */
	public void remove(SmartULS uls, Key key, Value value) {
		
		if (this.tree != null) {
			if(this.tree.containsKey(key) && this.tree.get(key).equals(value)){
				this.tree.remove(key);
				this.size--;
			}
			else{
				try {
					throw new Exception("Cannot remove, Invalid Key or Value");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}

		else if (this.hashtable != null) {
			
			if(this.hashtable.containsKey(key) && this.hashtable.get(key).equals(value)){
				this.hashtable.remove(key);
				this.size--;
			}
			else{
				try {
					throw new Exception("Cannot remove, Invalid Key or Value");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (this.size < this.tresholdULS){
				this.shift();
			}
		}
		
		else {
			try {
				throw new Exception("Cannot remove element from undefined structure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Get the value mapped to the specified key
	 * @param uls
	 * @param key
	 * @return
	 */
	public Value getValues(SmartULS uls, Key key) {
		
		if(this.tree == null && this.hashtable == null){
			try {
				throw new Exception("Cannot get element of undefined structure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (this.tree != null) {
			return (Value)this.tree.get(key);
		}

		else{
			return (Value)this.hashtable.get(key);
		}
	}

	/**
	 * Return the next key in the sequence of the given key
	 * @param uls
	 * @param key
	 * @return
	 */
	public Key nextKey(SmartULS uls, Key key) {
		
		if(this.tree == null && this.hashtable == null){
			try {
				throw new Exception("Cannot return next key of undefined structure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (this.tree != null) {
			//System.out.println("Getting next key from tree");
			return (Key) this.tree.higherKey(key);	
		}
		
		else{
			//System.out.println("Getting next key from Hashtable");
			Key[] myKeys = (Key[]) this.hashtable.keySet().toArray();
			
			//This method uses Quicksort
			Arrays.sort(myKeys);
			
			int keyIndex = Arrays.binarySearch(myKeys, key);
			
			if(keyIndex == myKeys.length - 1){
				return null;
			}
			else{
				return (myKeys[keyIndex + 1]);
			}
		}
	}

	/**
	 * Return the previous key in the sequence of the given key
	 * @param uls
	 * @param key
	 * @return
	 */
	public Key prevKey(SmartULS uls, Key key) {
		

		if(this.tree == null && this.hashtable == null){
			try {
				throw new Exception("Cannot return previous key of undefined structure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (this.tree != null) {
			return (Key) this.tree.lowerKey(key);
		}
		
		else{
			Key[] myKeys = (Key[]) this.hashtable.keySet().toArray();
			
			//This method uses Quicksort
			Arrays.sort(myKeys);
			
			int keyIndex = Arrays.binarySearch(myKeys, key);
			
			return (myKeys[keyIndex -1]);
		}
		
		
	}

	/**
	 * Return a set of key between the range of the two keys given in argument
	 * @param key1
	 * @param key2
	 * @return
	 */
	public int rangeKey(Key key1, Key key2) {
		
		if(this.tree == null && this.hashtable == null){
			try {
				throw new Exception("Cannot give range of key of Undefined structure");
			} catch (Exception e) {
				e.getMessage();
			}
		}
		
		
		//First element is included, -1 required
		else if(this.tree != null && this.tree.containsKey(key1) && this.tree.containsKey(key2)){
			if((Integer)key2 > (Integer)key1){
				return this.tree.subMap(key1, key2).keySet().size() - 1;	
			}
			else{
				return this.tree.subMap(key2, key1).keySet().size() - 1;
			}
		}
		
		else if(this.hashtable != null && this.hashtable.containsKey(key1) && this.hashtable.containsKey(key2)) {
				Key[] myKeys =  (Key[]) this.hashtable.keySet().toArray();
				
				//MergeSort
				Arrays.sort(myKeys,0,myKeys.length-1);

				int keyIndex = 0;
				int nbrEntries = 0;
				
				//Key2 > Key1
				if((Integer)key2 > (Integer)key1){
					System.out.println("OPTION 1");
					keyIndex = Arrays.binarySearch(myKeys,key1);
					System.out.println("Index of " + key1 + ", " + keyIndex);
					//Key 2 is the next key in the last, nothing in between
					if(myKeys[keyIndex + 1].equals(key2)){
						return 0;
					}
					
					int currentIndex = keyIndex;
					while(myKeys[currentIndex].equals(key2) == false){
						System.out.println("\t" + myKeys[currentIndex]);
						nbrEntries++;
						currentIndex++;
					}
				} 
				//Key1 > Key2
				else{
					System.out.println("OPTION 2");
					keyIndex = Arrays.binarySearch(myKeys,key2);
					System.out.println("Index of " + key2 + ", " + keyIndex);
					if(myKeys[keyIndex + 1].equals(key1)){
						return 0;
					}
					
					//Start at first element after key1
					int currentIndex = keyIndex;
					while(myKeys[currentIndex].equals(key1) == false){
						System.out.println("\t" + myKeys[currentIndex]);
						nbrEntries++;
						currentIndex++;
					}
				}
				
				
				
				//Current value is key2, -1 required
				return nbrEntries;
			
		}
		
		else{
			try {
				throw new Exception("Invalid Key");
			} catch (Exception e) {
				e.getMessage();
			}
		}
			return 0;
	}

	
//Utility methods--------------------------------------------------------------------------------------------
	/**
	 * Shift the structure from tree to hashtable and from hashtable to tree
	 * when the threshold value is smaller than the current size
	 */
	private void shift() {
		
		if(this.tree != null){
			System.out.println("Shifting from tree to hashtable");
			Set<Key> keys = this.tree.keySet();
			
			this.hashtable = new HashMap<Key,Value>(this.size);
			
			for(Key key : keys){
				//System.out.println("Adding " +  key + " to hashtable");
				this.hashtable.put(key, this.tree.get(key));
			}
			
			this.tree = null;
		}
		
		else if(this.hashtable != null){
			System.out.println("Shifting from hashtable to tree");
			Set<Key> keys = this.hashtable.keySet();
			
			this.tree = new TreeMap<Key,Value>();
			
			for(Key key : keys){
				this.tree.put(key, this.hashtable.get(key));
			}
			
			this.hashtable = null;
		}
	}
	
	public int getSize(){
		return this.size;
	}

}
