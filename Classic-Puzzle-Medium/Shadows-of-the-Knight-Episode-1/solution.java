import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    private static Boolean DEBUG = true;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt(); // width of the building.
        int h = in.nextInt(); // height of the building.
        int n = in.nextInt(); // maximum number of turns before game over.
        int x0 = in.nextInt();// starting x position
        int y0 = in.nextInt();// starting y position
                
        int x = x0; // current x position
        int y = y0; // current y position
        
        // Search space
        int xL = 0;     // minimum x value
        int xR = w-1;   // maximum x value
        int yL = 0;     // minimum y value
        int yR = h-1;   // maximum y value

        // game loop
        while (true) {
            String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            printDebug("Bomb Dir = "+bombDir);
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            
            if (bombDir.startsWith("U")){
                yR = y-1;
                y = (yL+yR)/2;
                printDebug("Set yR = "+yR+", yL = "+yL+", y = "+y);
            }
            else if (bombDir.startsWith("D")){
                yL = y+1;
                y = (yL+yR)/2;
                printDebug("Set yL = "+yL+", yR = "+yR+", y = "+y);
            }
            else{
                printDebug("No vertical adjustment");
            }
            
            
            if (bombDir.contains("L")){
                xR = x-1;
                x = (xL+xR)/2;
                printDebug("Set xR = "+xR+", xL = "+xL+", x = "+x);
            }
            else if (bombDir.contains("R")){
                xL = x+1;
                x = (xL+xR)/2;
                printDebug("Set xL = "+xL+", xR = "+xR+", x = "+x);
            }
            else{
                printDebug("No horizontal adjustment");
            }
            

            // the location of the next window Batman should jump to.
            System.out.println(x+" "+y);
        }
    }
    
    private static void printDebug(String s){
        if (DEBUG){
            System.err.println(s);
        }
    }
}