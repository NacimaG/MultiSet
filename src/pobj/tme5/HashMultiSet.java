package pobj.tme5;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>, Comparator<T>{
	private HashMap<T,Integer> hashMap ;
	private int size;
	/**
	 * constructeurs sans arguments; initialise la hashMap
	 */
	public HashMultiSet() {
		hashMap = new HashMap<>();
		size=0;
	}
	/**
	 * 
	 * @param collection
	 */
	public HashMultiSet(Collection<T> collection){
		for(T elem : collection) {
			this.add(elem);
		}
	}
	@Override
	public boolean isEmpty() {
		return hashMap.size()==0;
	}

	@Override
	public boolean contains(Object o) {
		return hashMap.containsKey(o);
	}

	@Override
	public Iterator<T> iterator() {
		return new HashMultiSetIterator<>(this);  
	}

	@Override
	public Object[] toArray() {
		Object[] array = (Object[]) new Object();
		//for (Object elem : this()) {
			
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		c.removeAll(c);
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(T e, int count) {
		if(count<=0)
			throw new IllegalArgumentException("argument non valide, count doit être > 0 ");
		for (int i =0; i<count; i++) {
			this.add(e);
		}
		size+= count;
		return true;
	}

	@Override
	public boolean add(T e) {
		if (hashMap.get(e)==null) {
			hashMap.put(e, 1);
		}else {
			hashMap.put(e, hashMap.get(e)+1);
		}
		size++;
		return true;
	}
	

	@Override
	public boolean remove(Object e) {
		Object o = hashMap.remove(e);
		size--;
		return o!=null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object e, int count) {
		if(count<=0)
			throw new IllegalArgumentException("argument non valide, count doit être > 0 ");
		if ((this.count((T)e)!=0) && (count <= count((T) e))) {
			hashMap.put((T) e, this.count((T)e)-count);
			size -= this.count((T)e)-count;
			return true;
		}
		return false;
	}

	@Override
	public int count(T o) {
		return hashMap.get(o) == null ? 0:hashMap.get(o); 
		
	}

	@Override
	public void clear() {
		hashMap.clear();
	}

	public int size_Ens() {
		return hashMap.size();
	}
	
	@Override
	public int size() {
		return size;
	}

	public HashMap<T,Integer> get_HasMap() {
		return this.hashMap;
	}
	
	public class HashMultiSetIterator<T> implements Iterator<T>{
		
		private Iterator<Map.Entry<T,Integer>> ite;
		
		private Map.Entry<T,Integer> curr_elem;

		private HashMultiSet<T> hashMapI;
		private int index_elem=0;
		public HashMultiSetIterator(HashMultiSet<T> hashMap ) {
			this.hashMapI = hashMap;
			this.ite = hashMapI.get_HasMap().entrySet().iterator();
		}
		@Override
		public boolean hasNext() {
			return ((index_elem>0)||(this.ite.hasNext()));
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No HasNext !!");
			}
			if (index_elem==0) {
				curr_elem = ite.next() ;
				index_elem = curr_elem.getValue();
			}
				index_elem--;
			return curr_elem.getKey();
		}
	}

	@Override
	public List<T> elements() {
		List<T> l = new ArrayList<T>();
		Iterator<T> ite = this.iterator();
		T tmp_elem;
		if (ite.hasNext()) {
			tmp_elem = ite.next();
			while(ite.hasNext()) {
				T elem = ite.next();
				if (!tmp_elem.equals(elem)) {
					l.add(elem);
					tmp_elem = elem;
				}
			}
		}
		return l;

	}
	@Override
	public int compare(T arg0, T arg1) {
		//System.out.println("COUCOU");
		//System.out.println(arg0);
		int v1 = hashMap.get(arg0);
		int v2 = hashMap.get(arg1);
		//System.out.println(v1+" , "+v2);
		if (v1<v2)
			return -1;
		else if(v1>v2)
			return 1;
		return 0;
	}
	@Override
	public Comparator<T> getComparer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first= true;
		List<T> liste = this.elements();
		sb.append("[");
		for(T elem : hashMap.keySet()) {
			if(first)
				first=false;
			else {
				sb.append(elem);
				sb.append(":");
				sb.append(hashMap.get(elem).intValue());
				sb.append("\n");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	public boolean isConsistent() {
		int siz = 0;
		for(T elem : hashMap.keySet()) {
			Integer nb = hashMap.get(elem);
			if(nb==0 || nb<0) return false;
			siz+=nb;
		}
		if(siz!= size) return false;
		return true;	
	}
}
