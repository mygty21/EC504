
public class Main {
	public static void main(String[] arg) {
		Fheap h = new Fheap();
		
		h.insert(1);
		h.insert(6);
		h.insert(5);
		h.insert(4);
		h.insert(10);
		h.insert(2);
		h.insert(9);
		h.insert(3);
		h.insert(8);
		h.insert(7);
		while(!h.isEmpty()){
			System.out.print(h.extractMin()+" ");
		}
	}
}
