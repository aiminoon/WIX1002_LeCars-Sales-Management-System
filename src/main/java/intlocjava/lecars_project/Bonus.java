package intlocjava.lecars_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Bonus {
    private double bonus;
    
    public double SalesBonus(String ID) {
        int salesCount = getSalesCount(ID);
        double salesAmount = getSalesAmount(ID);

        if (salesCount > 15 || salesAmount > 1000000) {
            bonus = 500;  
        } else {
            System.out.println("You failed to have more than 15 car sales records,"
                               + " or more than RM1 million sales amount within a calendar month. "
                               + "No bonus will be given.");
            bonus = 0;
        }
        System.out.println("Your bonus for this month is " + bonus);
        return bonus;
    }
    
    public double ManagementBonus(String ID) {
        double salesAmount = getSalesAmount(ID);

        if (salesAmount >= 2500000.01) {
            bonus = salesAmount * (1.35/100);
        } else if (salesAmount >= 2500000.01) {
            bonus = salesAmount * (1.25/100);
        } else if (salesAmount >= 800000.01) {
            bonus = salesAmount * (1.15/100);
        } else if (salesAmount <= 800000.00) {
            bonus = salesAmount * (1.00/100);
        } else {
            System.out.println("Error in calculating management employee bonus.");
        }
        System.out.println("Your bonus for this month is " + bonus);
        return bonus;
    }
    
    private int getSalesCount(String ID) {
        int salesCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("sales.csv"))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] sales = line.split(",");
                
                if (ID.equals(sales[4])) {
                    salesCount++;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occured when counting sales records in sales.csv.");
            e.printStackTrace();
        }

        return salesCount;
    }
    
    private double getSalesAmount(String ID) {
        int i = 0;
        double salesAmount = 0;
        
        String employeeID = ID;
        String line = "";
        String splitBy = ",";
        String[] carPlates = new String[10];
        
        Scanner input = new Scanner(System.in);
        
        LocalDateTime date = LocalDateTime.now();
        int currentMonth = date.getMonthValue();

        try {
            BufferedReader br = new BufferedReader(new FileReader("sales.csv"));
            boolean found = false;

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] sales = line.split(splitBy);
                String currentTimeDate = sales[1];
                
                LocalDateTime dateCheck = LocalDateTime.parse(currentTimeDate, DateTimeFormatter.ISO_DATE_TIME);
                int monthCheck = dateCheck.getMonthValue();
                
                if (employeeID.equals(sales[4]) && monthCheck == currentMonth) {
                    carPlates[i] = sales[2];
                    i++;
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No sales has been made this month");
            }
            
        } catch (IOException e) {
            System.out.println("An error occurred while searching car plate in sales.csv");
            e.printStackTrace();
        }
        
        if(carPlates != null){
            try {
                BufferedReader br = new BufferedReader(new FileReader("vehicle.csv"));

                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] vehicles = line.split(splitBy);
                    
                    for(int j = 0; j < carPlates.length; j++){
                        if(vehicles[0].equals(carPlates[j])){
                            salesAmount += Double.parseDouble(vehicles[4]);
                            break;
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("An error occurred while counting sales amount.");
                e.printStackTrace();
            }
        }
        return salesAmount;
    }
}
