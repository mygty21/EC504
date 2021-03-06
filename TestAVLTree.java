import org.junit.Test;
import static org.junit.Assert.*;
import junit.TestRunner;
import java.util.Arrays;

/** This class tests the AVLTree class. */
public class TestAVLTree{	
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
		T1.remove(15);  
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

	@Test
    public void test_sort() {
        AVLTree avl= new AVLTree();
        int[] num=Sort.createRandomArray(10000);
        int[] sorted=Sort.sort(avl,num);
        Arrays.sort(num);
        assertArrayEquals(num,sorted);
    }
	public static void main(String[] args) {
		TestRunner.runTests(TestAVLTree.class);
	}
} 
