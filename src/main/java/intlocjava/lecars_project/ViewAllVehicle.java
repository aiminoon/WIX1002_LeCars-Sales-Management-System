package intlocjava.lecars_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewAllVehicle {
    
    public static void displayVehicles(){
        List<String[]> vehicleData = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("vehicle.csv"));
            String line;
            
            while((line = br.readLine()) != null)  {
                String[] row = line.split(","); 
                vehicleData.add(row);
            }

            System.out.println("All vehicle records: ");
            
            for (String[] row : vehicleData){
                if(row.length == 5) { 
                    String carPlate= row[0];
                    String carModel = row[1];
                    String acquirePrice = row[2];
                    String carStatus = row[3];
                    String salesPrice = row[4];
                    
                    if(carStatus.equals("0")){
                        carStatus = "Out of Stock"; 
                    } 
                    
                    System.out.printf("%-10s   %-30s    %-14s    %-15s  %-10s \n", 
                            carPlate, carModel, acquirePrice, carStatus, salesPrice);
                }
                
                if(row.length == 4) { 
                    String carPlate= row[0];
                    String carModel = row[1];
                    String acquirePrice = row[2];
                    String carStatus = row[3];
                    String salesPrice = "";
                    
                    if(carStatus.equals("1")){
                        carStatus = "In Stock"; 
                    } 

                    System.out.printf("%-10s   %-30s    %-14s    %-15s  %-10s \n", 
                            carPlate, carModel, acquirePrice, carStatus, salesPrice);
                }
            }
            
        } catch (IOException | NumberFormatException e){
            System.out.println("An error occured when priting data from vehicle.csv file.");
            e.printStackTrace();  
        }
    }
}
