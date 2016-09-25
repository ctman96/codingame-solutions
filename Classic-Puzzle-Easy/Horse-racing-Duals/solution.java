import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static int N; // Number of horses
    static ArrayList<Integer> str;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        init(in);
        Collections.sort(str);
        System.out.println(minDif());
    }
    
    private static void init(Scanner in){
        N = in.nextInt(); 
        str = new ArrayList<Integer>();
        
        for (int i = 0; i < N; i++) {
            int pi = in.nextInt();
            str.add(pi);
        }
    }
    
    private static int minDif(){
        int mindif = 10000000;
        for(int i = 1; i<str.size();i++){
            int dif = str.get(i)-str.get(i-1);
            if (dif < mindif)
                mindif = dif;
        }
        return mindif;
    }
}
