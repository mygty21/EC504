import java.awt.List;

//TODO decreaseKey
public class MinHeap implements Heap{
	private int[] A;
	private int size;
	
	/**
	 * Build a new empty min-heap.
	 */
	public MinHeap(){
		A = new int[2];
		size = 0;
	}
	
	// TODO for test, delete later.
	public String toString(){
		String res = "";
		for	(int i = 0; i < size; i++) {
			res += A[i]+" ";
		}
		
		return res;
	}
	
	/**
	 * Build a new min-heap with an array.
	 * Use buildHeap() to make the input array a valid heap.
	 * 
	 * @param A  the array that used to build the heap.
	 */
	public MinHeap(int[] A){
		this.A = A;
		this.size = A.length;
		buildHeap();
	}
	
	/**
	 * extracMin() returns and remove the minimum number in the heap.
	 * If there the heap is empty, return Integer.MIN_VALUE.
	 * 
	 * @return the minimum number in heap.
	 */
	public int extractMin(){
		if (isEmpty()) {
			return Integer.MIN_VALUE;
		}
		int res = A[0];
		A[0] = A[size-1];
		size--;
		minHeapify(0);
		return res;
	}
	
	/**
	 * min() returns the minimum number in the heap.
	 * 
	 * @return the minimum number in heap.
	 */
	public int min(){
		return A[0];
	}
	
	/**
	 * isEmpty() returns whether the heap is empty or not.
	 * 
	 * @return whether the heap is empty or not.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * insert(int) inserts a number into the heap, and keep the heap valid.
	 * 
	 * @param num   the number that needed to insert into heap.
	 */
	public void insert(int num){
		if (size == A.length){
			int[] temp = new int[size*2];
			for (int i = 0; i < A.length; i++){
				temp[i] = A[i];
			}
			A = temp;
		}
		A[size] = num;
		int i = size;
		size++;
		
		while(i != 0){
			if (A[i] < A[parent(i)]) {
				int temp = A[i];
				A[i] = A[parent(i)];
				A[parent(i)] = temp;
			}
			i = parent(i);
		}
	}
	
	private void buildHeap() {
		for (int i = size/2; i >=0; i--){
			minHeapify(i);
		}
	}

	private void minHeapify(int i){
		if (i >= size) {
			return;
		}
		int min = i;
		if (hasLeftChild(i) && A[left(i)] < A[i]) {
			min = left(i);
		}
		if (hasRightChild(i) && A[right(i)] < A[min]) {
			min = right(i);
		}
		if (min != i){
			int temp = A[i];
			A[i] = A[min];
			A[min] = temp;
			minHeapify(min);
		}
	}

	private int parent(int i){
		return (i-1)/2;
	}
	
	private boolean hasLeftChild(int i){
		return (i*2+1) < size;
	}

	private boolean hasRightChild(int i){
		return (i*2+2) < size;
	}

	private int left(int i) {
		return i*2+1;
	}

	private int right(int i) {
		return i*2+2;
	}
}
