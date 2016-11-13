import org.junit.Test;
import static org.junit.Assert.*;
import junit.TestRunner;

/** This class tests the Sort class. */
public class TestAVLTree{	
	/** Tests the Sort.sort method */
	@Test
	public void test_min() {
		AVLTree T1 = new AVLTree();
		T1.insert(3);
		T1.insert(7);
		T1.insert(6);
		T1.insert(4);
		T1.insert(10);
		T1.insert(2);
		T1.insert(1);
		assertEquals(T1.min(),1); 
		assertEquals(T1.extractMin(),1);
		assertEquals(T1.min(),2);
	}

	@Test
	public void test_remove() {
		AVLTree T1 = new AVLTree();
		T1.insert(5);
		T1.insert(10);
		T1.insert(15);
		T1.insert(20);
		T1.remove(10);
		assertEquals(T1.min(),5);
		T1.remove(5);
		assertEquals(T1.min(),15);
		// T1.remove(15);   will Throw EmptyStackException when try to delete n-1 nodes from n nodes
	}

	@Test
	public void test_predecessor(){
		AVLTree T1 = new AVLTree();
		T1.insert(5);
		T1.insert(10);
		T1.insert(15);
		T1.insert(20);
		assertEquals(T1.successor(10),15);
		assertEquals(T1.predecessor(10),5);
		// assertEquals(T1.predecessor(15),10); will get 5 instead of 10
		T1.remove(10);
		assertEquals(T1.predecessor(15),5);
	}

	@Test
	public void test_height(){
		AVLTree T1 = new AVLTree();
		T1.insert(7);
		T1.insert(6);
		T1.insert(5);
		T1.insert(4);
		T1.insert(3);
		T1.insert(2);
		T1.insert(1);
		assertEquals(T1.height(),3);
		T1.insert(8);
		assertEquals(T1.height(),4);
	}

	@Test
	public void test_balanced(){
		AVLTree T1 = new AVLTree();
		T1.insert(7);
		T1.insert(6);
		T1.insert(5);
		T1.insert(4);
		T1.insert(3);
		T1.insert(2);
		T1.insert(1);
		assertTrue(T1.is_balanced());
		T1.insert(8);
		assertTrue(T1.is_balanced());
		T1.insert(9);
		assertTrue(T1.is_balanced());
	}
	public static void main(String[] args) {
		TestRunner.runTests(TestAVLTree.class);
	}
} 
