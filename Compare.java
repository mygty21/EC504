import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.awt.Desktop;
import java.util.ArrayList;


public class Compare {
    private Heap ds1;
    private Heap ds2;
    private int trails;
    private String[] title;
    private String[] result1;
    private String[] result2;

    public Compare(){
        trails=5;
    }

    public Compare(int trails){
        this.trails=trails;
    }

    public void run_compare(Heap h1, Heap h2, ArrayList<Integer> nums){
        ds1=h1;
        ds2=h2;
        int size=nums.size();
        title=new String[size];
        result1=new String[size];
        result2=new String[size];
        for(int i=0;i<nums.size();i++){
            int n=nums.get(i);
            System.out.println("Sorting in progress. array size="+n);
            int[] results=compare(n);
            title[i]=String.valueOf(n);
            result1[i]=String.valueOf(results[0]);
            result2[i]=String.valueOf(results[1]);
        }
        generate_log();
    }

    protected int[] compare(int n){
        long start;
        long duration1=0,duration2=0;
        int[] result=new int[2];
        int[] unsorted = Sort.createRandomArray(n);
        for(int i=0;i<trails;i++){
            start=System.nanoTime();
            int[] sorted1 = Sort.sort(ds1, unsorted);
            duration1+=(System.nanoTime()-start)/1000;

            start=System.nanoTime();
            int[] sorted2 = Sort.sort(ds2, unsorted);
            duration2+=(System.nanoTime()-start)/1000;
        }
        // calculate the average running time
        duration1/=trails;
        duration2/=trails;
//        System.out.println("duration1: "+duration1);
//        System.out.println("duration2: "+duration2);
        result[0]=(int)duration1;
        result[1]=(int)duration2;
        return result;

    }

    protected void generate_log(){
        String name_ds1=checkds(ds1);
        String name_ds2=checkds(ds2);
        try{
            String filename="result_"+name_ds1+"_"+name_ds2+".html";
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head><h1 style=\"text-align:center;\">Running Time Comparison (Micro Seconds)</h1></head>");
            writer.println("<body>");
            writer.println("<style>\n" +
                    "table, th, td {\n" +
                    "    border: 1px solid black;\n" +
                    "    border-collapse: collapse;\n" +
                    "}\n" +
                    "</style>");
            writer.println("<table style=\"width:40%\" align=\"center\">");
            writer.println("<tr>");
            StringBuilder row=new StringBuilder();
            row.append("<th>n</th>\n");
            for(int i=0;i<title.length;i++){
                row.append("<th>"+title[i]+"</th>\n");
            }
            writer.println(row.toString());
            writer.println("</tr>");
            writer.println("<tr>");
            row=new StringBuilder();
            row.append("<th>"+name_ds1+"</th>\n");
            for(int i=0;i<result1.length;i++){
                row.append("<td>"+result1[i]+"</td>\n");
            }
            writer.println(row.toString());
            writer.println("</tr>");
            writer.println("<tr>");
            row=new StringBuilder();
            row.append("<th>"+name_ds2+"</th>\n");
            for(int i=0;i<result2.length;i++){
                row.append("<td>"+result2[i]+"</td>\n");
            }
            writer.println(row.toString());
            writer.println("</tr>");
            writer.println("</table>");
            writer.println("</body>");

            writer.close();
            
            File htmlfile = new File(filename);
            Desktop.getDesktop().browse(htmlfile.toURI());
        } catch (IOException e) {
            System.err.println("File creation failed:");
        }
    }
    
    public String checkds(Heap h){  
        if(h instanceof Fheap){
            return "Fheap";
        }
        if(h instanceof MinHeap){
            return "MinHeap";
        }
        if(h instanceof AVLTree){
            return "AVLTree";
        }
        else return "";
    }

    public void set_trails(int n){
        trails=n;
    }

}
