import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static String MESSAGE;
    static StringBuilder output;
    static StringBuilder unary;
    static StringBuilder binary;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        init(in);
        messageToBinary();
        binaryToUnary();

        System.err.println("=======================");
        System.out.println(output.toString());
    }
    
    
    
    private static void init(Scanner in){
        MESSAGE = in.nextLine();
        System.err.println("Message: "+MESSAGE);
        
        output = new StringBuilder();
        unary = new StringBuilder();
        binary = new StringBuilder();
        
    }
    
    
    // convert MESSAGE String to Binary String
    private static void messageToBinary(){
        System.err.println("=== Message to Binary ===");
        char[] charr = MESSAGE.toCharArray();
        for (char ch : charr){
            System.err.println("For Char: "+ch);
            
            String str = String.format("%7s", Integer.toBinaryString(ch & 0xFF)).replace(' ', '0');
            System.err.println("Bin= "+str);
            
            binary.append(str);
            System.err.println();
        }
        System.err.println("Binary: "+binary);
        System.err.println();
    }
    
    //convert binary string to unary string
    // message encoded in blocks of 0 separated by space
    // two consecutive blocks produce a series of same value bits
    // 0 encoded as 00, 1 as 0, for the first block
    // second block, the number of zeros is the numbers of bits in the series
    private static void binaryToUnary(){
        System.err.println("=== Binary to Unary ===");
        char prev = 'X';
        int count = 0;
        char[] bin = binary.toString().toCharArray();
        for (char c : bin)
            System.err.println(c);
        for (char c : bin){
            if(c != prev){
                System.err.println("Char "+c+" != "+prev);
                System.err.println("Count is "+count);
                if (prev=='0')
                    output.append("00 ");
                if (prev=='1')
                    output.append("0 ");
                for(int i=0;i<count;i++)
                    output.append("0");
                if (count != 0)
                    output.append(" ");
                count = 1;
            }else
                count++;
            prev = c;
        }
        if (prev=='0')
            output.append("00 ");
        if (prev=='1')
            output.append("0 ");
        for(int i=0; i<count;i++){
            output.append("0");
        }
    }
}
