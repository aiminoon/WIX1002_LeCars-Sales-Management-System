package intlocjava.lecars_project;

import java.io.*;

public class ViewManagement {
    
    public static void viewAllInfo(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            int i = 0;
            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                
                if (i == 0) {
                    printInfo(data);
                    i++;
                } else {
                    printInfo(data);
                }
            }
        
        } catch (IOException e){
            System.out.println("An error occured while reading " + filename + ".");
            e.printStackTrace();
        }
    }

    public static void printInfo(String[] data) {
        if (data.length == 4) {
            System.out.printf("%-10s   %-30s    %-14s    %-15s\n", 
                    data[0], data[1], data[2], data[3]);
        } else {
            System.out.printf("%-10s   %-30s    %-14s    %-15s   %-15s\n",
                    data[0], data[1], data[2], data[3], data[4]);
        }
    }
}
