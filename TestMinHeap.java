import org.junit.Test;
import junit.TestRunner;
import static org.junit.Assert.*;
import java.util.Arrays;

/**
 * This class tests the MinHeap class
 */
public class TestMinHeap {
    @Test
    public void test_min_empty_init() {
        MinHeap heap= new MinHeap();
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
    public void test_min_array_init() {
    	int[] test_array={3,2,5,60,1};
        MinHeap heap= new MinHeap(test_array);
        assertEquals(1,heap.min());
        assertEquals(1,heap.extractMin());
        assertEquals(2,heap.min());
    }


    @Test
    public void test_empty() {
        MinHeap heap= new MinHeap();
        assertTrue(heap.isEmpty());
        heap.insert(3);
        assertFalse(heap.isEmpty());
    }


    @Test
    public void test_sort() {
        MinHeap heap= new MinHeap();
        int[] num=Sort.createRandomArray(10000);
        int[] sorted=Sort.sort(heap,num);
        Arrays.sort(num);
        assertArrayEquals(num,sorted);
    }


    public static void main(String[] args) {
        TestRunner.runTests(TestMinHeap.class);
    }
}
