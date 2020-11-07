package pobj.tme5.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetDecorator;

public class HashMultiSetTest2 {
	
//	question 5.3 Tests JUnit 4	
	private MultiSet<String> m;
	

	
	
	@Before
	public void before() {
//		m = new HashMultiSet<>();
// 		question 5.5 Vérification de la cohérence interne avec un décorateur
		MultiSet<String> m1 = new HashMultiSet<String>();
		m = new MultiSetDecorator<String>(m1);
	}
	
	
	@Test 
	public void testAdd1() {
		m.add("a");
		m.add("a",5);
		assertEquals(6, m.count("a"));
	}

	@Test(expected = IllegalArgumentException.class) 
	public void testAdd2() {
		m.add("a");
		m.add("a",-1);
	}
	
	//Test adding 0 occurence 
	@Test 
	public void testAdd3() {
		m.add("a",0);
		assertEquals(0,m.count("a"));
	}

	//Test removing 1 occurence 
	@Test 
	public void testRemove1() {
		m.add("a");
		m.remove("a");
		assertEquals(0, m.count("a"));


		m.add("a", 2);
		m.remove("a");
		assertEquals(1, m.count("a"));
		
	}

	//Test removing a negative occurence
	@Test(expected = IllegalArgumentException.class)
	public void TestRemove2()  {
		m.add("a", 1);
		m.remove("a",-1);
	}
	
	//Test removing 0 occurence then a number of occurences
	@Test
	public void TestRemove3()  {
		m.add("a", 3);
		m.remove("a", 0);
		assertEquals(3,m.count("a"));
		
		m.remove("a", 1);
		assertEquals(2,m.count("a"));

	}
	
	//Test removing all occurences
	@Test
	public void TestRemove4()  {
		m.add("a", 3);
		m.remove("a", 3);
		assertEquals(0,m.count("a"));
		
	}	
	
	//Test removing more elements ( count > get(e) )
	@Test(expected = IllegalArgumentException.class)
	public void TestRemove5()  {
			m.add("a", 2);
			m.remove("a", 3);
	}
	
	//Testing size with different combinations
	@Test
	public void TestSize()  {
		m.add("a", 3);
		m.remove("a", 3);
		assertEquals(0,m.size());
		
		m.add("a", 3);
		m.add("b",2);
		assertEquals(5,m.size());
		
		m.remove("a");
		assertEquals(4,m.size());	
		
		m.remove("b",2);
		assertEquals(2,m.size());			

	}	
	
	//Test count 
	@Test
	public void TestCount()  {
		assertEquals(0,m.count("a"));
		
		m.add("a");
		assertEquals(1,m.count("a"));
		
		m.add("b",2);
		assertEquals(1,m.count("a"));
		assertEquals(2,m.count("b"));		
		
		m.remove("a");	
		assertEquals(0, m.count("a"));
		
		assertEquals(0, m.count("c"));
	}
	
	@Test
	public void TestClear(){
		m.add("a", 10);
		m.add("b",2);
		m.clear();
		assertEquals(0,m.count("a"));
		assertEquals(0,m.count("b"));
		
		System.out.println(m);
		assertEquals(0,m.size());
	}
	
	//Testing toString
	@Test
	public void TestToString() {
		m.add("a", 2);
		m.add("b", 4);
		m.add("c", 3);
		assertEquals("[a:2; b:4; c:3]",m.toString());
		
		m.clear();
		assertEquals("[]", m.toString());
	}
	
	
	//Testing add and remove with different combinations
	@Test
	public void TestAddRemove() {
		m.add("a", 2);
		m.add("b", 4);
		m.remove("a");
		m.add("c", 3);
		
		assertEquals(1, m.count("a"));
		assertEquals(4, m.count("b"));
		assertEquals(3, m.count("c"));
		
		m.remove("a");
		m.add("c", 10);
		m.remove("c",2);
		assertEquals(0, m.count("a"));
		assertEquals(4, m.count("b"));
		assertEquals(11, m.count("c"));
		
		
	}
	
}
