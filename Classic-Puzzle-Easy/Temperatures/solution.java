import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    
    public String temps;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        int closest = 10000;
        in.nextLine();
        String temps = in.nextLine(); // the n temperatures expressed as integers ranging from -273 to 5526
        if (temps.equals("")){
            System.out.println(0);
        }else{
            System.err.println("temps = "+temps);
            
            int itemps[] = new int[n];
            //Parser
            String[] arr = temps.split(" ");
            for (int i = 0; i<arr.length; i++){
                itemps[i] = Integer.parseInt(arr[i]);
                System.err.println("temps["+i+"] = "+itemps[i]);
            }
            
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            for (int i = 0; i < n; i++){
                int t = itemps[i];
                int abst = (t < 0) ? -t : t;
                int absc = (closest < 0) ? -closest : closest;
                System.err.println("abst= "+abst+", absc= "+absc);
                if ((abst < absc)||(abst == absc && t > closest))
                    closest = t;
                    
            }
        System.out.println(closest);
        }
    }
}
