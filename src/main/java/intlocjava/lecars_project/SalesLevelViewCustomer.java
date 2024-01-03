package LeCarsProject.ViewInfo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */
public class SalesLevelViewCustomer {

    public static List<String[]> readCustCSV() {
        List<String[]> data = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("cust.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return data; 
    }
    
    public static List<String[]> readSalesCSV() {
        List<String[]> data = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("sales.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return data; 
    }
    
    public static void displayCustRecords(String empId) {
        System.out.println("Customer records for Employee " + empId + ":");
        System.out.printf("%-12s %-30s %-15s %-10s%n", "Customer ID", "Customer Name", "Phone Number", "Postcode");
        
        List<String[]> sales = readSalesCSV(); 
        List<String[]> cust = readCustCSV();
        
        for (String[] sale : sales){
            if (sale.length >= 5) {
                if(sale[4].equals(empId)) {
                    String custId = sale[3]; 
                    for (String[] customer : cust) {
                        if(customer[0].equals(custId)) {
                            //System.out.println("Customer ID: " + customer[0] + ", Name: " + customer[1] + ", Phone Number: " + customer[2] + ", Postcode: " + customer[3]);
                            System.out.printf("%-10s   %-30s    %-14s    %-15s\n", customer[0], customer[1], customer[2], customer[3]);
                        }
                    }
                }
            } else {
                System.out.println("Invalid row format: " + String.join(", ", sale));
            }    
        }
    } 
}
