package intlocjava.lecars_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalesLevelViewSales {
    
    public static void displaySales(String empID){
        List<String[]> salesData = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("sales.csv"));
            String line;
            
            while((line = br.readLine()) != null)  {
                String[] row = line.split(","); 
                salesData.add(row);
            }

            System.out.println("Sales records for Employee ID " + empID + ":");
            System.out.printf("%-10s   %-30s    %-14s    %-15s\n", 
                    "Sales ID", "Date", "Car Plate", "Customer ID", "Employee ID");
            
            for (String[] row : salesData){
                if(row.length >= 5) { 
                    String salesId= row[0];
                    String dateTime = row[1];
                    String carPlate = row[2];
                    String custId = row[3];
                    String employeeId = row[4];
                
                    if(employeeId.equals(empID)){
                        System.out.printf("%-10s   %-30s    %-14s    %-15s\n", 
                                salesId, dateTime, carPlate, custId, employeeId);
                    }
                } else {
                    System.out.println("Invalid row format: " + String.join(", ", row));
                }
            }
            
        } catch (IOException | NumberFormatException e){
            System.out.println("An error occured when printing data from sales.csv file.");
            e.printStackTrace();  
        }
    }
}
