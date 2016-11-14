import java.util.Random;

public class Sort {
	
	public static int[] sort(Heap h, int[] num){
		for(int i=0;i<num.length;i++){
			h.insert(num[i]);
		}
		int[] res = new int[num.length];
		for(int i=0;i<num.length;i++){
			res[i] = h.extractMin();
		}
		return res;
	}
	
	public static int[] createRandomArray(int n){
		int[] num = new int[n];
		for(int i=1;i<=n;i++){
			num[i-1] = i;
		}
		Random rnd = new Random();
		int i,j;
        for (i = num.length - 1; i > 0; i--) {
           j = rnd.nextInt(num.length) % (i + 1);
           int temp = num[i];
           num[i] = num[j];
           num[j] = temp;
        }  
		return num;
	}
	
}
