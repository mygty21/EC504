import org.junit.Test;
import junit.TestRunner;
import static org.junit.Assert.*;
import java.util.Arrays;

/**
 * This class tests the Fheap class
 */
public class TestFHeap {
    @Test
    public void test_min() {
        Fheap heap= new Fheap();
        heap.insert(3);
        heap.insert(2);
        heap.insert(5);
        heap.insert(60);
        heap.insert(1);
        assertEquals(1,heap.min());
        assertEquals(1,heap.extractMin());
        assertEquals(2,heap.min());
    }



    @Test
    public void test_empty() {
        Fheap heap= new Fheap();
        assertTrue(heap.isEmpty());
        heap.insert(3);
        assertFalse(heap.isEmpty());
    }


    @Test
    public void test_union() {
        Fheap heap1= new Fheap();
        Fheap heap2= new Fheap();
        heap1.insert(3);
        heap1.insert(2);
        heap1.insert(5);
        heap1.insert(60);
        heap1.insert(10);
        heap2.insert(6);
        heap2.insert(14);
        heap2.insert(1);
        heap1.union(heap2);
        assertEquals(1,heap1.min());
        assertEquals(1,heap1.extractMin());
        assertEquals(2,heap1.extractMin());
        assertEquals(3,heap1.extractMin());
        assertEquals(5,heap1.extractMin());
        assertEquals(6,heap1.extractMin());
        assertEquals(10,heap1.extractMin());
        assertEquals(14,heap1.extractMin());
        assertEquals(60,heap1.extractMin());
        assertTrue(heap1.isEmpty());
    }


    @Test
    public void test_sort() {
        Fheap heap= new Fheap();
        int[] num=Sort.createRandomArray(10000);
        int[] sorted=Sort.sort(heap,num);
        Arrays.sort(num);
        assertArrayEquals(num,sorted);
    }


    public static void main(String[] args) {
        TestRunner.runTests(TestFHeap.class);
    }
}
