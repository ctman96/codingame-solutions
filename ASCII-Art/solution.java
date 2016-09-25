import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static int L;
    static int H;
    static String T;
    static String[] arr;
    static String[] out;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        init(in);
        getASCII(in);
        stringToASCII();

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        for(int i = 0; i<H; i++)
            System.out.println(out[i]);
    }
    
    //Initialize variables, input
    private static void init(Scanner in){
        L = in.nextInt();
        H = in.nextInt();
        System.err.println("Width: "+L+", Height: "+H);
        in.nextLine();
        
        T = in.nextLine();//String to produce    
        System.err.println(T);
        
        arr = new String[H];//Rows of the ASCII alphabet given
        out = new String[H];//output
    }
    
    // fill arr with the rows of the given ASCII alphabet
    private static void getASCII(Scanner in){
        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            System.err.println(ROW);
            arr[i] = ROW;
            out[i] = "";
        }
    }
    
    // Convert the String to ASCII by getting the letter from
    // the given alphabet stored in arr
    // non-alphabetic characters are set to index 26, which is ?
    private static void stringToASCII(){
        char[] chars = T.toCharArray();
        for (char c : chars){
            int index;
            if (!((c >= 'a' && c <= 'z')||(c>='A'&&c<='Z')))
                index = 26;
            else
                index = (int)c%32-1;
            System.err.println(c+" is "+index);
            
            String o = "";
            for (int i = 0; i < H; i++) {
                String ROW = arr[i];
                int start = index*L;
                int end = start+L;
                String s = ROW.substring(start, end);
                out[i] = out[i]+s;
            }
        }
    }
}
