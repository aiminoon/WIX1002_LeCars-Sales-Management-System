package intlocjava.lecars_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Salary {
    Random rand = new Random();
    private double baseSalary, allowance, commision; 
    
    public Salary(int employeeStatus){
        if (employeeStatus == 0){
            baseSalary = 1200;
            allowance = rand.nextDouble() * 250;
        }
        else if (employeeStatus == 1){
            baseSalary = 2200;
            allowance = rand.nextDouble() * 350;
        }
        else
            System.out.println("Error in calculating salary.");
    }
    
    
    public void commisionCalculation(String ID){
        String employeeID = ID;
        String line = "";
        String splitBy = ",";
        Scanner input = new Scanner(System.in);
        LocalDateTime date = LocalDateTime.now();
        int currentMonth = date.getMonthValue();
        String[] carPlates = new String[10];
        int i = 0;
        double commisionCalculated = 0;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("sales.csv"));
            boolean found = false;

            br.readLine();//skip first line
            while ((line = br.readLine()) != null) {
                String[] sales = line.split(splitBy);
                String currentTimeDate = sales[1];
                LocalDateTime dateCheck = LocalDateTime.parse(currentTimeDate, DateTimeFormatter.ISO_DATE_TIME);
                int monthCheck = dateCheck.getMonthValue();
                if (employeeID.equals(sales[4]) && monthCheck == 6) {
                    carPlates[i] = sales[2];
                    i++;
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No sales has been made this month");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while searching car sales commision");
            e.printStackTrace();
        }
        
        if(carPlates != null){
        try {
            BufferedReader br = new BufferedReader(new FileReader("vehicle.csv"));

            br.readLine();//skip first line
            while ((line = br.readLine()) != null) {
                String[] vehicles = line.split(splitBy);
                for(int j = 0; j<carPlates.length; j++){
                    if(vehicles[0].equals(carPlates[j])){
                        commisionCalculated += Double.parseDouble(vehicles[4])*0.01;
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while calculating commision");
            e.printStackTrace();
        }
        }
        
        commision =commisionCalculated;
    }
    
    
    public double totalSalary(){
        return baseSalary+allowance+commision;
    }
    
    public void displaySalary(String ID){
        System.out.println("EmployeeID: "+ ID);
        System.out.println("Total salary: "+ totalSalary());
    }
}
