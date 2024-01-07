package intlocjava.lecars_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ViewAllVehicle {
    public static void displayVehicles(){
        List<String[]> vehicleData = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("newvehicle.csv"));
            String line;
            while((line = br.readLine()) != null)  {
                String[] row = line.split(","); //split CSV line by comma
                vehicleData.add(row);
            }
            
            // Display sales records for the given employee ID
            System.out.println("All vehicle recoreds: ");
            
            for (String[] row : vehicleData){
                if(row.length >= 5) { //ensure there are at least 5 elements in row array
                    String carPlate= row[0];
                    String carModel = row[1];
                    String acquirePrice = row[2];
                    String carStatus = row[3];
                    String salesPrice = row[4];
                    
                    if(carStatus.equals("0")){
                        carStatus = "Out of Stock"; 
                    } 
                    
                    System.out.printf("%-10s   %-30s    %-14s    %-15s\n", carPlate, carModel, acquirePrice, carStatus, salesPrice);
             
                } else {
                    // Handle rows with null data 
                    System.out.println("In Stock: " + String.join(", ", row));
                }
            }
        } catch (IOException | NumberFormatException e){
            e.printStackTrace();  
        }
    }
}
