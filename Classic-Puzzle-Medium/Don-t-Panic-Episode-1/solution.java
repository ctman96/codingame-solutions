import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Elevator {
    int floor;
    int pos;
    
    public Elevator(int floor, int pos){
        this.floor=floor;
        this.pos=pos;
    }
}

class Clone {
    int floor;
    int pos;
    
    public Clone(int floor, int pos){
        this.floor = floor;
        this.pos = pos;
    }
}

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators
        List<Elevator> elevators = new ArrayList<Elevator>();
        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            elevators.add(new Elevator(elevatorFloor, elevatorPos));
        }
        // game loop
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT
            System.err.println ("Clone: "+cloneFloor +","+clonePos+","+direction);
            String out = "WAIT";
            if (cloneFloor <= exitFloor){
                for (Elevator e : elevators){
                    System.err.println ("Elevator: "+e.floor+","+e.pos);
                    if (e.floor == cloneFloor){
                        if (direction.equals("RIGHT")) {
                            out = (clonePos  > e.pos) ? "BLOCK" : "WAIT";
                        }
                        else {
                            out = (clonePos < e.pos) ? "BLOCK" : "WAIT";
                        }
                    }
                }
                if (exitFloor == cloneFloor){
                    System.err.println ("On exit floor heading "+direction);
                    if (direction.equals("RIGHT")) {
                        out = (clonePos  > exitPos) ? "BLOCK" : "WAIT";
                    }
                    else {
                        out = (clonePos  < exitPos) ? "BLOCK" : "WAIT";
                    }
                }
            }
            System.out.println(out); // action: WAIT or BLOCK
        }
    }
    // if floor is odd/even, move to pos and block
}
