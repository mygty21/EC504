
public class Main {
	public static void main(String[] arg) {
		AVLTree t = new AVLTree();
		t.insert(20);
		t.insert(36);
		t.insert(18);
		t.insert(24);
		t.print();
		t.insert(28);
		t.print();
		t.insert(27);
		t.print();
		t.insert(15);
		t.print();
		t.insert(14);
		t.print();
		t.insert(13);
		t.print();
		t.insert(12);
		t.print();
		t.insert(17);
		t.print();
		t.remove(14);
		t.print();
		t.remove(18);
		t.print();
		t.remove(20);
		t.print();
		t.remove(17);
		t.print();
		t.remove(13);
		t.print();
	}
}
