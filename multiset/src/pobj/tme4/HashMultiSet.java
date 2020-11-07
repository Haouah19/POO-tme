package pobj.tme4;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * HashMultiSet 
 */
public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
	/**
	 * la taille du HashMultiSet 
	 */
	private int size;
	
	/**
	 * la liste contenant respectivement les objet et leur nombre d'occurences 
	 */
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
		if (myList.containsKey(e)) {
			myList.put(e, myList.get(e)+count);
			size += count;
			return true;
		}
		myList.put(e,count);
		size += count;
		return true;
	}

	@Override
	public boolean add(T e) {
		if (myList.containsKey(e)) {
			myList.put(e, myList.get(e)+1);
			size++;
			return true;
		}
		myList.put(e,1);
		size++;
		return true;
			
	}

	@Override
	public boolean remove(Object e) {
		if (myList.containsKey(e)) {
			myList.remove(e, 1);
			size--;
			System.out.println(size());
			//need to check 
			if(count((T) e)==0)
				myList.remove(e);
			return true;
		}
		return false;
			
	}

	@Override
	public boolean remove(Object e, int count) {
//		if (myList.containsKey(e)) {
//			myList.remove(e, myList.get(e)-count);
//			size -= count;
//			return true;
//		}
//		return false;
		throw new UnsupportedOperationException();
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
		Set<T> set = new HashSet<>(myList.keySet());

		return new ArrayList<>(set);

	}
}
