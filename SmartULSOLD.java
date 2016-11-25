import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//Trees,Hash Tables, AVL tree, Binary Tree, sequence

public class SmartULSOLD {

	private int tresholdULS = 1000;
	private Object structure;
	private int size;

	public SmartULSOLD() {
		this.structure = new LinearHash<Long,Object>();
		this.size = ((LinearHash) this.structure).size();
	}

	public SmartULSOLD(int size) {
		if (size >= this.tresholdULS) {
			this.structure = new Hashtable<Long,Object>(size);
			this.size = ((Hashtable) this.structure).size();
		}

		else {
			this.structure = new LinearHash<Long,Object>(size);
			this.size = ((LinearHash) this.structure).size();
		}
	}

	public void setSmartThresholdULS(int size) {
		this.tresholdULS = size;
	}

	/**
	 * Randomly generates a new non-existing key of 8 digits
	 * 
	 * @return (long) key of 8 digits
	 */
	public long generate() {

		long randomKey = ThreadLocalRandom.current().nextLong(99999999);
		if (this.structure instanceof LinearHash) {
			if (((LinearHash) this.structure).contains(randomKey)) {
				while (((LinearHash) this.structure).contains(randomKey)) {

					randomKey = ThreadLocalRandom.current().nextLong(99999999);
				}
			}

		} else if (this.structure instanceof Hashtable) {
			if (((Hashtable) this.structure).contains(randomKey)) {
				while (((Hashtable) this.structure).contains(randomKey)) {

					randomKey = ThreadLocalRandom.current().nextLong(99999999);
				}
			}
		}

		else {
			try {
				throw new Exception("Unrecognized structure");
			} catch (Exception e) {
				e.printStackTrace();
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
	public Set allKeys(SmartULSOLD uls) {
		Set<Object> keys = new HashSet<Object>();
		
		if (this.structure instanceof Hashtable) {
			keys = ((Hashtable) this.structure).keySet();
		}

		else if (this.structure instanceof LinearHash) {
			keys = ((LinearHash) this.structure).keySet();
			
		} else {
			try {
				throw new Exception("Unrecognized structure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return keys;
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
	public void add(SmartULSOLD uls, long key, Object value) {

		if (this.structure instanceof LinearHash) {
			((LinearHash) this.structure).put(key, value);

			if (((LinearHash) this.structure).size() >= this.tresholdULS) {
				this.shift(this.structure);
			}
		} else if (this.structure instanceof Hashtable) {
			((Hashtable) this.structure).put(key, value);
		}

		else {
			try {
				throw new Exception("Unrecognized Structure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void remove(SmartULSOLD uls, long key, Object value) {
		
		if (this.structure instanceof LinearHash){
			((LinearHash) this.structure).remove(key);
			
			if(((LinearHash) this.structure).size() < this.tresholdULS){
				this.shift(this.structure);
			}
		}
		else if(this.structure instanceof Hashtable){
			((Hashtable) this.structure).remove(key);
		}
		
	}

	public Object getValues(SmartULSOLD uls, long key) {
		Object value = new Object();
		
		if (this.structure instanceof LinearHash){
			value = ((LinearHash) this.structure).get(key);
		}
		else if (this.structure instanceof Hashtable){
			value = ((Hashtable) this.structure).get(key);
		}
		else{
			try{
				throw new Exception("Unrecognized structure");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return value;
		
	}

	public Long nextKey(SmartULSOLD uls, long key) {
		
		/*HashSet<Long> myKeys = new HashSet<Long>();*/
		/*
		if (this.structure instanceof LinearHash){
			myKeys = ((LinearHash) this.structure).keySet();
		}
		else if (this.structure instanceof Hashtable){
			myKeys = ((Hashtable) this.structure).keySet();
		}
		
		else{
			try{	
				throw new Exception("Unrecognized structure");
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		Iterator<Object> iterator = myKeys.iterator();
		
		while(iterator.hasNext()){
			if((long)iterator.next() == key){
				iterator = (Iterator<Object>) iterator.next() ;
				return (long)iterator.next();
			}
			
			iterator = (Iterator<Object>)iterator.next();
		}
		

		//Couldnt find the key
		return null;*/
		
		/*
		Set<Long> myKeys;
		
		if (this.structure instanceof LinearHash){
			myKeys = ((LinearHash) this.structure).keySet();
		}
		else if (this.structure instanceof Hashtable){
			myKeys = ((Hashtable) this.structure).keySet();
		}
		
		else{
			try{	
				throw new Exception("Unrecognized structure");
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		key_array = 
		*/
	}

	public void prevKey(SmartULSOLD uls, long key) {
		

		Set<Object> myKeys = new HashSet<Object>();
		
		if (this.structure instanceof LinearHash){
			myKeys = ((LinearHash) this.structure).keySet();
		}
		else if (this.structure instanceof Hashtable){
			myKeys = ((Hashtable) this.structure).keySet();
		}
		
		else{
			try{	
				throw new Exception("Unrecognized structure");
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//How to get previous key???
		
		
	}

	public void rangeKey(long key1, long key2) {

	}

	private void shift(Object structure) {

		// Object newStructure;

		if (this.structure instanceof Hashtable) {

			LinearHash newStructure = new LinearHash<Long,Object>(((Hashtable) this.structure).size());
			
			Set<Long> currentKeys = ((Hashtable) this.structure).keySet();
			
			for(Long key : currentKeys){
				newStructure.put(key, ((Hashtable) this.structure).get(key));
			}

			this.structure = newStructure;
		}

		else if (this.structure instanceof LinearHash) {

			Hashtable newStructure = new Hashtable<Long,Object>(((LinearHash) this.structure).size());

			// Copy everything
			

			Set<Long> currentKeys = ((LinearHash) this.structure).keySet();
			
			for(Long key : currentKeys){
				newStructure.put(key, ((LinearHash) this.structure).get(key));
			}

			this.structure = newStructure;
		}

		else {
			try {
				throw new Exception("Unrecognized structure");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
