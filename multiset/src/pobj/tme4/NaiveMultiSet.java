package pobj.tme4;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NaiveMultiSet <T> extends AbstractCollection<T> implements MultiSet<T> {

	private List<T> myList;
	
	
	public NaiveMultiSet() {
		this.myList = new ArrayList<>();
	}
	
	public NaiveMultiSet(Collection<T> copy) {
		this.myList = new ArrayList<>(copy);
	}

	@Override
	public boolean add(T e) {
		return myList.add(e);
	}

	
	
	@Override
	public boolean add(T e, int count) {
		for (int i=0; i<count; i++) {
			myList.add(e);
		}
		return true;
	}

	public boolean remove(Object e) {
		return myList.remove(e);
	}
	
	@Override
	public boolean remove(Object e, int count) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int count(T o) {
		int cpt = 0;
		
		for (T e : myList) 
			if (e.equals(o))
				cpt++;
		
		return cpt;
	}

	@Override
	public List<T> elements() {
		Set<T> set = new HashSet<>(myList);

		return new ArrayList<>(set);

	}


	
	@Override
	public Iterator<T> iterator() {
		return myList.iterator();
	}

	@Override
	public int size() {
		return myList.size(); 
	}

	

}
