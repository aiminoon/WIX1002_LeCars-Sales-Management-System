package intlocjava.lecars_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewProfitMargin {
        
    public static double calculateProfitMargin() {
        int totalAquirePrice = 0, totalSalesPrice = 0;
        double totalSalaryEmployee = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("vehicle.csv"));
            String line;
            int i = 1;
            while((line = br.readLine()) != null)  {
                String[] row = line.split(","); //split CSV line by comma
 
                if(i != 1 && Integer.parseInt(row[3]) == 0) { 
                    totalAquirePrice =totalAquirePrice + Integer.parseInt(row[2]);
                    if(row.length == 5) {
                        totalSalesPrice = totalSalesPrice + Integer.parseInt(row[4]);  
                    }
                }
                i++;
            }
 
 
            BufferedReader brEmployee = new BufferedReader(new FileReader("employee.csv"));
            line = brEmployee.readLine();
            while((line = brEmployee.readLine()) != null)  {
                String[] row = line.split(",");
 
                Salary salary = new Salary(Integer.parseInt(row[2]));
                salary.commisionCalculation(row[0]);
                totalSalaryEmployee = totalSalaryEmployee + salary.totalSalary();
            }
 
 
 
 
        } catch (IOException | NumberFormatException e){
            e.printStackTrace();  
        }
 
        // System.out.println(totalSalaryEmployee);
        double tax = (totalSalesPrice - totalAquirePrice - totalSalaryEmployee) * 0.10;
        double rent = 1000;
        double utility = 100;
        return totalSalesPrice - totalAquirePrice - totalSalaryEmployee - tax - rent - utility;
    }
}
