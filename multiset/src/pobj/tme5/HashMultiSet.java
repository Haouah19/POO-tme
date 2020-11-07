package pobj.tme5;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
	private int size;
	private Map<T, Integer> myList;
	
	public HashMultiSet() {
		this.size = 0;
		myList = new HashMap<>();
	}
	
	public HashMultiSet(Collection<T> copy) {
		this.size =0;
		myList = new HashMap<T, Integer>();
		for (T e : copy) {
			add(e);
		}
	}
	
	@Override
	public boolean add(T e, int count) {
		if(count<0)
			throw new IllegalArgumentException("argument 'count' must be positive");
		
		if (myList.containsKey(e)) {
			myList.put(e, myList.get(e)+count);
			size += count;
			
//			//Tester isConsistant
//			assert isConsistant();
			return true;
		}
		
		if(count==0)
		return false;
		
		myList.put(e,count);
		size += count;
//		//Tester isConsistant
//		assert isConsistant();
		return true;
	}

	@Override
	public boolean add(T e) {
		if (myList.containsKey(e)) {
			myList.put(e, myList.get(e)+1);
			size++;
			
//			//Tester isConsistant
//			assert isConsistant();
			return true;
		}
		myList.put(e,1);
		size++;
		
//		//Tester isConsistant
//		assert isConsistant();
		return true;
			
	}

	@Override
	public boolean remove(Object e) {
		if (myList.containsKey(e)) {
			myList.put((T) e, myList.get(e)-1);
			size--;
			//need to check 
			if(count((T) e)==0)
				myList.remove(e);	
			
//			//Tester isConsistant
//			assert isConsistant();
			return true;
		}
		
//		//Tester isConsistant
//		assert isConsistant();
		return false;
			
	}

	@Override
	public boolean remove(Object e, int count) {
		if(count<0)
			throw new IllegalArgumentException("argument 'count' must be positive");

		System.out.println(count((T) e));
		if ( myList.get(e)-count<0) 
			throw new IllegalArgumentException("count must be lower or equal to the number of elements");			
		
		if (myList.containsKey(e) && myList.get(e)-count>=0) {
			myList.put((T) e, myList.get(e)-count);
			size -= count;
			//need to check the cast first
			if(count((T)e)==0)
				myList.remove(e);
	
//			//Tester isConsistant
//			assert isConsistant();
			return true;
		}
		
//		//Tester isConsistant
//		assert isConsistant();
		return false;

	}

	@Override
	public int count(T o) {
		if(myList.containsKey(o)) {
			return myList.get(o);
		}
		return 0;
	}

	@Override
	public void clear() {
		myList.clear();
		size=0;
	}

	@Override
	public int size() {
		return size;
	}
	
	private boolean isConsistant() {
		int s=0;
		for( int i:myList.values()) {
			if(i<0)
				return false;
			s+=i;
		}
		if(size !=s)
			return false;
		return true;
	}
	
	
	private class HashMultiSetIterator implements Iterator<T>{
		
		private Iterator<Map.Entry<T,Integer>> myListIterator;
		private T cle;
		private int count;
		private int indice;
		
		public HashMultiSetIterator() {
			this.myListIterator = myList.entrySet().iterator();
			this.indice = 0;
			this.count= 0;
		}
		
		
		@Override
		public boolean hasNext() {
			return indice < size();
		}
		
		@Override
		public T next() {
			if (hasNext()) {
				if (count == 0) {
					Map.Entry<T, Integer> entry = myListIterator.next();
					count = entry.getValue();
					cle = entry.getKey();
				}
				
				indice++;
				count--;
				return cle;
				
			}else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}


	@Override
	public Iterator<T> iterator() {
		return new HashMultiSetIterator();
	}

	@Override
	public List<T> elements() {
		List<T> copy = new ArrayList<>();
		copy.addAll(myList.keySet());
		return copy;

	}

	@Override
	public String toString() {
		
		//return myList.toString();
		//previous line returns {e=n;e2=n2}
		
		StringBuilder b = new StringBuilder();
		b.append("[");
		Iterator<Map.Entry<T, Integer>> it = myList.entrySet().iterator();

	    while(it.hasNext()){
	    	Map.Entry<T, Integer> e = it.next();
	    	T key = e.getKey();
	    	int val = e.getValue();
	    	b.append(key + ":" + val + "; ");
	    }
	    
	    //verifier si la liste est vide et ne contient pas d'elements
	    if(b.length()>=3) {
	    b.deleteCharAt(b.length()-1); //delete espace en plus 
	    b.deleteCharAt(b.length()-1); //delete point virgule en plus
	    }
	    b.append("]");
		return b.toString();
		
		
	}
	
	
}
