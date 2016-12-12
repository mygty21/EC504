/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import visualization.HeapRender;
import java.util.Random;

/**
 *
 * @author allegro_l
 */
public class Demo {
    public static void render(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Size of Fheap1:");
        int s1=scanner.nextInt();
        Fheap heap1= new Fheap();
//        Fheap heap2= new Fheap();
        Random rnd = new Random();
        for(int i=0;i<s1;i++) heap1.insert(rnd.nextInt(5*s1));
//        for(int i=0;i<5;i++) heap2.insert(rnd.nextInt(30));
        HeapRender Rheap1=new HeapRender(heap1.min);
        Rheap1.show();
        heap1.extractMin();
        HeapRender Rheap1_1=new HeapRender(heap1.min);
        Rheap1_1.show();
        heap1.extractMin();
        HeapRender Rheap1_2=new HeapRender(heap1.min);
        Rheap1_2.show();
//        heap2.extractMin();
//        HeapRender Rheap2=new HeapRender(heap2.min);
//        Rheap2.show();
//        heap1.union(heap2);
//        HeapRender Rheap3=new HeapRender(heap1.min);
//        Rheap3.show();
    }
}
