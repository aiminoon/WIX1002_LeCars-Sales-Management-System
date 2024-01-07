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
public class SalesLevelViewSales {
    public static void displaySales(String empID){
        List<String[]> salesData = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("newsales.csv"));
            String line;
            while((line = br.readLine()) != null)  {
                String[] row = line.split(","); //split CSV line by comma
                salesData.add(row);
            }
            
            // Display sales records for the given employee ID
            System.out.println("Sales records for Employee ID " + empID + ":");
            System.out.printf("%-10s   %-30s    %-14s    %-15s\n", "Sales ID", "Date", "Car Plate", "Customer ID", "Employee ID");
            for (String[] row : salesData){
                if(row.length >= 5) { //ensure there are at least 5 elements in row array
                    String salesId= row[0];
                    String dateTime = row[1];
                    String carPlate = row[2];
                    String custId = row[3];
                    String employeeId = row[4];
             
                
                    if(employeeId.equals(empID)){
                        System.out.printf("%-10s   %-30s    %-14s    %-15s\n", salesId, dateTime, carPlate, custId, employeeId);
                    }
                } else {
                    // Handle rows with null data 
                    System.out.println("Invalid row format: " + String.join(", ", row));
                }
            }
        } catch (IOException | NumberFormatException e){
            e.printStackTrace();  
        }
    }
}
