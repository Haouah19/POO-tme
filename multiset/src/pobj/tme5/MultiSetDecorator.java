package pobj.tme5;

import java.util.AbstractCollection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MultiSetDecorator<T> extends AbstractCollection<T> implements MultiSet<T>{

	private MultiSet<T> decorated;
	
	
	

	public MultiSetDecorator(MultiSet<T> decorated) {
		this.decorated = decorated;
	}
	
	public boolean isConsistent() {
		List<T> elem = elements();
		int som = 0;
		for(T e : elem) {
			int val = count(e);
			if (val <= 0) {
				return false;
			}
			som += val;
		}
		
		if (som == size())
			return true;
		
		return false;
		
	}
	
	public boolean add(T e, int count) {
		boolean b = decorated.add(e, count);
		assert isConsistent();
		return b;
	}

	public boolean add(T e) {
		boolean b = decorated.add(e);
		assert isConsistent();
		return b;
	}

	public boolean remove(Object e) {
		boolean b = decorated.remove(e);
		assert isConsistent();
		return b;
	}

	public boolean remove(Object e, int count) {
		boolean b = decorated.remove(e, count);
		assert isConsistent();
		return b;
	}

	public int count(T o) {
		return decorated.count(o);
	}

	public void clear() {
		decorated.clear();
	}

	public int size() {
		return decorated.size();
	}

	public List<T> elements() {
		return decorated.elements();
	}
	
	public boolean isEmpty() {
		return decorated.isEmpty();
	}

	public boolean contains(Object o) {
		return decorated.contains(o);
	}

	public Iterator<T> iterator() {
		return decorated.iterator();
	}
	
	public boolean containsAll(Collection<?> c) {
		return decorated.containsAll(c);
	}

	public boolean addAll(Collection<? extends T> c) {
		return decorated.addAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		return decorated.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return decorated.retainAll(c);
	}

	public boolean equals(Object o) {
		return decorated.equals(o);
	}

	public int hashCode() {
		return decorated.hashCode();
	}
	
	public String toString() {
		return decorated.toString();
	}

}
