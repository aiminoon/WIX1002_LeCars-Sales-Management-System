package intlocjava.lecars_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class Salary {
    Random rand = new Random();
    private double baseSalary, allowance, commision, bonus; 
    
    public Salary(int employeeStatus){
        if (employeeStatus == 0){
            baseSalary = 1200;
            allowance = rand.nextDouble(251);
        }
        else if (employeeStatus == 1){
            baseSalary = 2200;
            allowance = rand.nextDouble(351);
        }
        else
            System.out.println("Error in calculating salary.");
    }
    
    public Salary(int employeeStatus, String ID){
        if (employeeStatus == 0){
            baseSalary = 1200;
            allowance = rand.nextDouble(251);
            
            Bonus b = new Bonus();
            bonus = b.SalesBonus(ID);
        } else if (employeeStatus == 1){
            baseSalary = 2200;
            allowance = rand.nextDouble(351);
            
            Bonus b = new Bonus();
            bonus = b.ManagementBonus(ID);
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
        int currentYear = date.getYear();
        
        String[] carPlates = new String[10];
        int i = 0;
        double commisionCalculated = 0;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("sales.csv"));
            boolean found = false;

            br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] sales = line.split(splitBy);
                String currentTimeDate = sales[1];
                LocalDateTime dateCheck = LocalDateTime.parse(currentTimeDate, DateTimeFormatter.ISO_DATE_TIME);
                int monthCheck = dateCheck.getMonthValue();
                int yearCheck = dateCheck.getYear();
                
                if (employeeID.equals(sales[4]) && monthCheck == currentMonth && yearCheck == currentYear) {
                    carPlates[i] = sales[2];
                    i++;
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No sales has been made this month by " + ID);
            }
            
        } catch (IOException e) {
            System.out.println("An error occurred while searching car sales commision");
            e.printStackTrace();
        }
        
        if(carPlates != null){
            try {
                BufferedReader br = new BufferedReader(new FileReader("vehicle.csv"));

                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] vehicles = line.split(splitBy);
                    
                    for(int j = 0; j<carPlates.length; j++){
                        if(vehicles[0].equals(carPlates[j])){
                            commisionCalculated += Double.parseDouble(vehicles[4]) * 0.01;
                            break;
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("An error occurred while calculating commision");
                e.printStackTrace();
            }
        }

        commision = commisionCalculated;
    }
    
    public double totalSalary(){
        return baseSalary + allowance + commision + bonus;
    }
    
    public void displaySalary(String ID){
        DecimalFormat df = new DecimalFormat ("0.00");
        System.out.println("EmployeeID: " + ID);
        System.out.println("Total salary: RM" + df.format(totalSalary()));
    }
}
