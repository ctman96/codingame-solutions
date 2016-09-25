import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static int N;
    static int Q;
    static Map<String,String> map;
    static String[] files;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        init(in);

        setMap(in);
        getFiles(in);

        printMIME();
    }
    
    // Initialize Variables
    private static void init(Scanner in){
        System.err.println();
        System.err.println();
        System.err.println("==========================");
        System.err.println("Initializing Variables...");
        System.err.println("==========================");
        N = in.nextInt(); // Number of elements which make up the association table.
        Q = in.nextInt(); // Number Q of file names to be analyzed.
        System.err.println("N: "+N+", Q: "+Q);
        map = new HashMap<String, String>();
        files = new String[Q];
    }
    
    // fill map with Extension/MIME pairs given by input
    private static void setMap(Scanner in){
        System.err.println();
        System.err.println();
        System.err.println("===================");
        System.err.println("Populating Map...");
        System.err.println("===================");
        for (int i = 0; i < N; i++) {
            String EXT = in.next().toLowerCase(); // file extension
            String MT = in.next(); // MIME type.
            System.err.println("File ext: "+EXT+", MIME: "+MT);
            map.put(EXT,MT);
            System.err.println();
        }
        in.nextLine();
    }
    
    // fill files array with file extensions
    // cut extensions off of the file names from input
    private static void getFiles(Scanner in){
        System.err.println();
        System.err.println();
        System.err.println("=================");
        System.err.println("Getting Files...");
        System.err.println("=================");
        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine().toLowerCase(); // One file name per line.
            int n = FNAME.lastIndexOf('.')+1;
            String ext = "";
            if (FNAME.contains("."))
                ext = FNAME.substring(n);

            String mext = map.get(ext);
            System.err.println("File "+i+": "+FNAME+", Ext: "+ext+", MIME: "+mext);
            
            files[i]=mext;
            System.err.println();
        }
    }
    
    // print the MIME file extension for each file
    private static void printMIME(){
        System.err.println();
        System.err.println("==========");
        System.err.println("Printing...");
        System.err.println("==========");
        for (int i = 0;i<files.length;i++){
            String ext = files[i];
            if (ext==null){
                System.out.println("UNKNOWN");
            }else{
                System.out.println(ext);
            }
        }
    }
}
