import visualization.HeapRender;

public class Main {
	public static void main(String[] arg) {
		Fheap h = new Fheap();
		
		int[] arr = Sort.createRandomArray(10000);
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		arr = Sort.sort(h, arr);
		
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
}
