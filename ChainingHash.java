import java.util.Arrays;

/*If all the values in the second array are already put back in the original array
			 * or the current value of the first Array at indexArray1 is smaller than the value of the second
			 * Array at indexArray2, then the current value of the array 1 is smaller and must be placed in the original array
			*/
			if(indexArray2 == array2.length || (indexArray1 < array1.length && comp.compare(array1[indexArray1], array2[indexArray2]) < 0)){
				originalArray[indexOriginalArray] = array1[indexArray1];
				indexArray1++;
			}
			
			else{
				originalArray[indexOriginalArray] = array2[indexArray2];
				indexArray2++;
			}
			indexOriginalArray++;
			
			
			
			
			//Implementation of mergesort to sort the keys
			private Comparable[] sortKeys(Comparable[] myKeys){
				int size = myKeys.length;
				
				//If the array has only 2 elements, no need to split
				if(size < 2){
					return myKeys;
				}
				
				//Divide the array in 2 and subdivide it
				int mid = size / 2;
				
				Comparable[] firstHalf = Arrays.copyOfRange(myKeys, 0, mid);
				Comparable[] secondHalf = Arrays.copyOfRange(myKeys, mid, size);
				
				//Keep dividing
				sortKeys(firstHalf);
				sortKeys(secondHalf);
				
				//Merge the small arrays
				merge(firstHalf,secondHalf,myKeys);
				
				return myKeys;
				
			}
			
			private <Key> void merge(Comparable[] array1, Comparable[] array2, Comparable[] originalArray){
				int indexArray1 = 0;
				int indexArray2 = 0;
				int indexOriginalArray = 0;
				
				while(indexArray1 + indexArray2 < originalArray.length){
					
					if(array1[indexArray1].compareTo(array2[indexArray2]) < 0){
						originalArray[indexOriginalArray] = array1[indexArray1];
						indexArray1++;
					}
					
					else{
						originalArray[indexOriginalArray] = array2[indexArray2];
						indexArray2++;
					}
					indexOriginalArray++;
					
					//The value of the second array at indexArray2 is smaller than the value of array1 at index indexArray1
					
				}
				
			}
