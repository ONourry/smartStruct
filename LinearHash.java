
public class LinearHash<Key,Value>{
	
	private int nbrElements;
	private int sizeHash;
	
	private Key[] keys;
	private Value[] values;
	
	private final int DEFAULT_SIZE = 11;
	private final double LOAD_FACTOR_TOO_BIG = 0.75;
	private final double LOAD_FACTOR_TOO_SMALL = 0.20;
	
	//Default constructor
	public LinearHash(){
		this.sizeHash = this.DEFAULT_SIZE;
		this.nbrElements = 0;
		
		keys = new Key[this.DEFAULT_SIZE];
		values = new Value[this.DEFAULT_SIZE];
	}
	
	//Constructor taking a size as argument
	public LinearHash(int size){
		this.sizeHash = size;
		this.nbrElements = 0;
		
		keys = new Key[this.sizeHash];
		values = new Value[this.sizeHash];
	}
	
	
	//Put a value and a key in the hash table
	public void put(Key key, Value value) throws Exception{
		//Invalid key
		if(key == null)
			throw new Exception("Invalid Key");
		
		index = hashKey(key);
		
		//Key valid but empty value
		else if(value == null){
			throw new Exception("Invalid Value")
		}
		
		//If we exceed the 0.75 load, double the size
		if (( ((double)this.nbrElements) / ((double)this.sizeHash) ) > LOAD_FACTOR_TOO_BIG)
			resize(2*this.sizeHash);
		
		
		//Hash index is already occupied
		if(keys[index] != null){
			
			int newIndex;
			
			for(newIndex = index; keys[newIndex] != null; i++){
				
				//Return to beginning of array and keep searching if last index has been reached
				if(newIndex == keys.length - 1)
					newIndex = 0;
				
				//Key already in hashtable, update value
				if(keys[newIndex].equals(key)){
					values[newIndex] = value;
					return;
				} 
			}
			
			//Adjust attributes
			keys[newIndex] = key;
			values[newIndex] = value;
			this.nbrElements++;

		}
		
		
	}
	
	public boolean contains(Key key){
		if(key == null)
			throw new Exception("Invalid Key");
		return get(key) != null;
	}
	
	//Get the corresponding value of the key given in parameter
	public Value get(Key key){
		if(key == null)
			throw new Exception("Invalid Key");
			
		for(int i = hashKey(key); keys[i] != null; i++){
			//There has been something there before but it was deleted, to avoid issues with the .equals method
			if(keys[i] == ("-1"))
				continue;
			
			if(keys[i].equals(key)){
				return values[i];
			}
		}
		
		//Couldnt find anything at the specified key
		return null;
	}
	
	//Remove a key (Set to -1) from the hashtable and set the value to null
	public void remove(Key key){
		
		if(key == null)
			throw new Exception("Invalid Key");
		
		if (!contains(key))
			return;
		
		for(int i = hashKey(key); keys[i] != null; i++){
			
			//Return to beginning of array and keep searching if last index has been reached
			if(i == keys.length - 1)
				i = 0;
			
			if(keys[i].equals(key)){
				keys[i] = "-1";
				values[i] = "-1";
			}
		}
		
		this.sizeHash--;
		
		//Hash table has too much empty space, resize
		if (( ((double)this.nbrElements) / ((double)this.sizeHash) ) < LOAD_FACTOR_TOO_SMALL)
			resize(this.sizeHash/2);
	}
	
	//Get hash code for the key
	private int hashKey(Key key){
		return (key.hashCode() % this.sizeHash);
	}
	
	
	//Resize the hashtable
	private void resize(int sizeHash){
		LinearHash<Key,Value> newHash = new LinearHash<Key,Value>(sizeHash);
		
		for(int i=0; i<this.sizeHash;i++){
			if(newHash[i] != null)
				newHash[i].put(this.keys[i],this.values[i]);
		}
		
		this.keys = newHash.keys;
		this.values = newHash.values;
		this.sizeHash = newHash.sizeHash;
	}
}