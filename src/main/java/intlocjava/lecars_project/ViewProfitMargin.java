package intlocjava.lecars_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ViewProfitMargin {
        
    public static double calculateProfitMargin() {
        int totalAquirePrice = 0, totalSalesPrice = 0;
        double totalSalaryEmployee = 0;
        try {
            
            BufferedReader br = new BufferedReader(new FileReader("sales.csv"));
            String line = "";          
            List<String> carPlates = new ArrayList<>();
            int i = 0;
            LocalDateTime date = LocalDateTime.now();
            int currentMonth = date.getMonthValue();
            

            br.readLine();//skip first line
            while ((line = br.readLine()) != null) {
                String[] sales = line.split(",");
                String currentTimeDate = sales[1];
                LocalDateTime dateCheck = LocalDateTime.parse(currentTimeDate, DateTimeFormatter.ISO_DATE_TIME);
                int monthCheck = dateCheck.getMonthValue();
                if (monthCheck == currentMonth) {
                    carPlates.add(sales[2]);
                }
            }
            br.close();
            BufferedReader brvehicle = new BufferedReader(new FileReader("vehicle.csv"));
            brvehicle.readLine();
            while((line = brvehicle.readLine()) != null) {
                String[] row = line.split(",");
                if(carPlates.contains(row[0])) {
                    totalAquirePrice =totalAquirePrice + Integer.parseInt(row[2]);
                    totalSalesPrice = totalSalesPrice + Integer.parseInt(row[4]);
                }
                
            }
            brvehicle.close();
            
            
            
            BufferedReader brEmployee = new BufferedReader(new FileReader("employee.csv"));
            line = brEmployee.readLine();
            while((line = brEmployee.readLine()) != null)  {
                String[] row = line.split(",");
 
                Salary salary = new Salary(Integer.parseInt(row[2]));
                salary.commisionCalculation(row[0]);
                totalSalaryEmployee = totalSalaryEmployee + salary.totalSalary();
            }
            brEmployee.close();
 
 
 
        } catch (IOException | NumberFormatException e){
            e.printStackTrace();  
        }
 
        // System.out.println(totalSalaryEmployee);
        double tax = (totalSalesPrice - totalAquirePrice - totalSalaryEmployee) * 0.10;
        double rent = 1000;
        double utility = 100;
        return totalSalesPrice - totalAquirePrice - totalSalaryEmployee - rent - utility;
    }
    

    
}
