
public class Main {
	public static void main(String[] arg) {
		Fheap fh = new Fheap();
		fh.insert(4);
		fh.insert(7);
		fh.insert(10);
		fh.insert(3);
		fh.insert(45);
		fh.printlist(fh.min);
		System.out.println(fh.n);

		Fheap fh2 = new Fheap();
		fh2.insert(20);
		fh2.insert(54);
		fh2.insert(15);
		fh2.insert(1);
		fh2.insert(35);
		fh2.insert(8);
		fh2.printlist(fh2.min);
		System.out.println(fh2.n);
		
		fh.union(fh2);
		fh.printlist(fh.min);
		System.out.println(fh.n);
		
		while(!fh.isEmpty()){
			System.out.print("heap min="+fh.extractMin());
			System.out.println();
		}
	}
}
