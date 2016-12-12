import visualization.HeapRender;
import java.util.ArrayList;
import java.util.Scanner;

public class Main { 
    public static void main(String[] arg) {
        int choices=0;
        int trails=5;
        Scanner scanner = new Scanner(System.in);
        MinHeap mh = new MinHeap();
        Heap avl = new AVLTree();
        Heap fh = new Fheap();
        Compare cp;
        ArrayList<Integer> times = new ArrayList<Integer>();
        times.add(10000);
        times.add(50000);
        times.add(100000);
        times.add(300000);
        times.add(500000);
        while(choices<5){
            System.out.println("Please select which data structures to compare(1-4): ");
            System.out.println("1. Fibonacci Heap vs MinHeap");
            System.out.println("2. Fibonacci Heap vs AVL Tree");
            System.out.println("3. AVL Tree vs MinHeap");
            System.out.println("4. Launch demo for HeapRenderer");
            System.out.println("5. Exit");
            choices=scanner.nextInt();
            switch(choices){
                case 1:
                    System.out.println("Please sepecify the number of trails(default=5): ");
                    trails=scanner.nextInt();
                    if(trails<0) trails=5;
                    cp = new Compare(trails);
                    cp.run_compare(mh,fh,times);
                    break;
                case 2:
                    System.out.println("Please sepecify the number of trails(default=5): ");
                    trails=scanner.nextInt();
                    if(trails<0) trails=5;
                    cp = new Compare(trails);
                    cp.run_compare(fh,avl,times);
                    break;
                case 3:
                    System.out.println("Please sepecify the number of trails(default=5): ");
                    trails=scanner.nextInt();
                    if(trails<0) trails=5;
                    cp = new Compare(trails);
                    cp.run_compare(mh,avl,times);
                    break;
                case 4:
                    System.out.println("Launching HeapRenderer...");
                    Demo.render();
                    break;
                case 5:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Wrong choice. Please select from 1-4. Exit...");
                    choices=4;
                    break;
            }
        }                         
    }
}
