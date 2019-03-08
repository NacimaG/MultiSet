package pobj.tme4;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class NaiveMultiSet<T>extends AbstractCollection<T> implements MultiSet<T> {
	ArrayList<T> list;
	private NaiveMultiSetComparator compar = new NaiveMultiSetComparator(this);
	int size;
	public NaiveMultiSet() {
		list = new ArrayList<>();
		size=0;
	}
	public NaiveMultiSet(Collection<T> collection) {
		for(T elem : collection) {
			this.add(elem);
		}
	}
	@Override
	public boolean isEmpty() {
		return list.size()==0;
	}

	@Override
	public boolean contains(Object o) {
		for(T elem : list)
			if(elem.equals((T)o))
				return true;
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
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
		for(int i=0;i<count;i++)
			list.add(e);
		return true;
	}

	@Override
	public boolean add(T e) {
		list.add(e);
		return true;
	}

	@Override
	public boolean remove(Object e) {
		list.remove(e);
		return true;
	}

	@Override
	public boolean remove(Object e, int count) {
		for(int i=0;i<count;i++)
			list.remove(e);
		return true;
	}

	@Override
	public int count(T o) {
		int cpt=0;
		for(int i=0;i<list.size();i++)
			if(list.get(i).equals(o))
				cpt++;
		return cpt;
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public int size() {
		return list.size();
	}
	@Override
	public Comparator<T> getComparer() {
		return compar;
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
public class NaiveMultiSetComparator<T> implements Comparator<T>{
		private NaiveMultiSet<T> nms ; 
		public NaiveMultiSetComparator(NaiveMultiSet<T> nms){
			this.nms=nms;
		}
		
		@Override
		public int compare(T arg0, T arg1) {
			System.out.println("coucocu");
			if(nms.count(arg0)<nms.count(arg1))
					return 1;
			else if(nms.count(arg0)== nms.count(arg1) )
					return 0;
			else
				return -1;
		}
	}



}
