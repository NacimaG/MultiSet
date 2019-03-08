package pobj.tme5.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;
public class HashMultiSetTest2 {
	private  MultiSet<String> m;

	@Before
	public void before() {
		m = new HashMultiSet<>();
		affiche();
	}
	
	@Test 
	public void testAdd1() {
		m.add("a");
		m.add("b",3);
		m.add("a",5);
		//m.add("b",-10);

		assertEquals(6, m.count("a"));
	}
	@Test
	public void testToString() {
		m.toString();
	}
	@Test
	public void affiche() {
		System.out.println(m.toString());
	}

	@Test
	public void testRemove() {
		m.remove("a");
	}
	

}
