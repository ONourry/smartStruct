import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//Trees,Hash Tables, AVL tree, Binary Tree, sequence

public class SmartULS<Long,Value> {

	private int tresholdULS = 1000;
	private TreeMap tree;
	private Hashtable hashtable;
	private int size;

	public SmartULS() {
		this.tree = new TreeMap<Long,Value>();
		this.size = this.hashtable.size();
	}

	public SmartULS(int size) {
		if (size >= this.tresholdULS) {
			this.hashtable = new Hashtable<Long,Value>(size);
			this.size = this.hashtable.size();
		}

		else {
			this.tree = new TreeMap<Long,Value>();
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
	 * @return (long) key of 8 digits
	 */
	public long generate() {

		long randomKey = ThreadLocalRandom.current().nextLong(99999999);
		
		if(this.tree != null){
			if(this.tree.containsKey(randomKey)){
				while(this.tree.containsKey(randomKey)){
					randomKey = ThreadLocalRandom.current().nextLong(99999999);
				}
			}
		}
		else if(this.hashtable != null){
			if(this.hashtable.containsKey(randomKey)){
				while(this.hashtable.containsKey(randomKey)){
					randomKey = ThreadLocalRandom.current().nextLong(99999999);
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
	public Set<Long> allKeys(SmartULSOLD uls) {
		
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
			Set<Long> keys = this.hashtable.keySet();
			
			TreeSet<Long> treeSet = new TreeSet<Long>();
			treeSet.addAll(keys);
			
			keys = treeSet;
			return keys;
		}
	}

	/**
	 * add an entry for the given key and value
	 * 
	 * @param uls
	 *            SmartULS object
	 * @param key
	 *            (long) key to the value
	 * @param value
	 *            (Object) object to be added with the specified key
	 */
	public void add(SmartULSOLD uls, long key, Value value) {

		if (this.tree != null) {
			this.tree.put(key, value);
		}

		else if (this.hashtable != null) {
			this.hashtable.put(key, value);
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
	public void remove(SmartULSOLD uls, long key, Value value) {
		
		if (this.tree != null) {
			this.tree.remove(key, value);
		}

		else if (this.hashtable != null) {
			this.hashtable.remove(key, value);
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
	public Value getValues(SmartULSOLD uls, long key) {
		
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
	public long nextKey(SmartULSOLD uls, long key) {
		
		if(this.tree == null && this.hashtable == null){
			try {
				throw new Exception("Cannot return next key of undefined structure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (this.tree != null) {
			return (long)this.tree.ceilingKey(key);
		}
		
		else{
			
			Long[] myKeys = (Long[]) this.hashtable.keySet().toArray();
			
			//This method uses Quicksort
			Arrays.sort(myKeys);
			
			int keyIndex = Arrays.binarySearch(myKeys, key);
			
			return ((long)keyIndex + 1);
		}
	}

	/**
	 * Return the previous key in the sequence of the given key
	 * @param uls
	 * @param key
	 * @return
	 */
	public long prevKey(SmartULSOLD uls, long key) {
		

		if(this.tree == null && this.hashtable == null){
			try {
				throw new Exception("Cannot return previous key of undefined structure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (this.tree != null) {
			return (long) this.tree.floorKey(key);
		}
		
		else{
			Long[] myKeys = (Long[]) this.hashtable.keySet().toArray();
			
			//This method uses Quicksort
			Arrays.sort(myKeys);
			
			int keyIndex = Arrays.binarySearch(myKeys, key);
			
			return ((long)keyIndex -1);
		}
		
		
	}

	/**
	 * Return a set of key between the range of the two keys given in argument
	 * @param key1
	 * @param key2
	 * @return
	 */
	public Set rangeKey(long key1, long key2) {
		
		if(this.tree == null && this.hashtable == null){
			try {
				throw new Exception("Cannot give range of key of Undefined structure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(this.tree != null){
			return this.tree.subMap(key1, key2).keySet();
		}
		
		else {
			TreeMap myKeys = (TreeMap) this.hashtable.keySet();
			
			return (Set) myKeys.subMap(key1, key2);
			
		}
	}

	
//Utility methods--------------------------------------------------------------------------------------------
	/**
	 * Shift the structure from tree to hashtable and from hashtable to tree
	 * when the threshold value is smaller than the current size
	 */
	private void shift() {
		
		if(this.tree != null){
			Set<Long> keys = this.tree.keySet();
			
			this.hashtable = new Hashtable<Long,Object>(this.tree.size());
			
			for(Long key : keys){
				this.hashtable.put((long)key, this.tree.get((long)key));
			}
			
			this.tree = null;
		}
		
		else if(this.hashtable != null){
			Set<Long> keys = this.hashtable.keySet();
			
			this.tree = new TreeMap<Long,Object>();
			
			for(Long key : keys){
				this.tree.put((long)key, this.hashtable.get((long)key));
			}
			
			this.hashtable = null;
		}
	}
}
