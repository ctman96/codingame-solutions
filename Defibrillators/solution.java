import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static double LON;
    static double LAT;
    static int N;
    static String[] defibs;
    static Coords[] coords;

    // Main
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        init(in);
        initDefibs(in);
        parseDefibs();

        String closest = closest();
        System.out.println(closest);
    }
    
    
    // Initialize Variables
    private static void init(Scanner in){
        System.err.println("=========================");
        System.err.println("Initializing Variables...");
        System.err.println("=========================");
        
        LON = llDouble(in.next());  //Convert input lon/lat into doubles
        LAT = llDouble(in.next());
        N = in.nextInt();
        in.nextLine();
        
        System.err.println("lon: "+LON+", lat: "+LAT);
        System.err.println("Num: "+N);
        
        defibs = new String[N];
        coords = new Coords[N];
        
        System.err.println("=========================");
        System.err.println();
        System.err.println();
    }
    
    
    // Convert the input lat or lon into a double
    // Input is given as a string, with a comma instead of a decimal point
    private static double llDouble(String s){
        String sd = s.replace(",",".");
        double d = Double.parseDouble(sd);
        return d;   
    }
        
    
    //Take in Defibrillators
    //Store in defibs array
    private static void initDefibs(Scanner in){
        System.err.println("==========================");
        System.err.println("Fetching Defibrillators...");
        System.err.println("==========================");
        
        for (int i = 0; i < N; i++) {
            String DEFIB = in.nextLine();
            defibs[i] = DEFIB;
            System.err.println(i+": "+DEFIB);
            System.err.println();
            System.err.println("--------------------------");
        }
        
        System.err.println("==========================");
        System.err.println();
        System.err.println();
    }

    
    
    //Parse defibrillators into Coords
    //Defibrillator contains 6 parts (0-5)
    // 0=its number (not necessarily in order)
    // 1=location name, 2=location address, 3=empty
    // 4=longitude, 5=latitude
    private static void parseDefibs(){
        System.err.println("==========================");
        System.err.println("Parsing Defibrillators...");
        System.err.println("==========================");
        
        int i = 0;
        for (String defib : defibs){
            System.err.println("==========");
            System.err.println("Parsing...");
            System.err.println("==========");
            String[] parts = defib.split(";");
            
            int num = Integer.parseInt(parts[0])-1;
            double lon= llDouble(parts[4]);
            double lat= llDouble(parts[5]);
            String location = parts[1];
            String addr = parts[2];

            System.err.println("Defib #"+i);
            System.err.println(parts[1]+", "+parts[2]);
            System.err.println("Lon: "+lon+", Lat: "+lat);
            coords[i] = new Coords(lon, lat, location, addr);
            System.err.println(coords[i].location);
            i++;
        }
    }
    
    
    
    // Calculate the closest difibrillator
    // Produce the location name of the defibrillator that is 
    //   the shortest distance from user's location (LON,LAT)
    private static String closest(){
        System.err.println("======================");
        System.err.println("Comparing Distances...");
        System.err.println("======================");
        
        String c = "";
        double distance = 100000000;
        
        for (int i = 0; i<N; i++){
            System.err.println(coords[i].location);
            double d = coords[i].getDist(LON, LAT);
            System.err.println("Dist: "+d);
            
            if (d < distance){
                System.err.println("is closer than "+distance);
                distance = d;
                c = coords[i].location;
            }
            
        }
        return c;
    }
}

class Coords{
    double lat;
    double lon;
    String location;
    String address;
    
    // Constructor
    public Coords(double lon, double lat, String location, String address){
        super();
        this.lon = lon;
        this.lat = lat;
        this.location = location;
        this.address = address;
    }
    
    //Produce the x value used to calculate distance between two coordinates
    private double getX(double lonB, double latB){
        double x = (lonB - lon)*Math.cos((lat+latB)/2);
        return x;
    }
    
    //Produce the y value used to calculate distance between two coordinates
    private double getY(double lonB, double latB){
        double y = (latB - lat);
        return y;
    }
    
    //Calculate the distance between the Coords' (lon,lat) and the given (lonb,latb)
    public double getDist(double lonB, double latB){
        double x = getX(lonB, latB);
        double y = getY(lonB, latB);
        
        double dist = Math.sqrt((x*x)+(y*y))*6371;
        
        return dist;
    }
}

