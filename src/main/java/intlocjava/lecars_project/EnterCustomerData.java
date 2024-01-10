package intlocjava.lecars_project;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.LineNumberReader;
import java.util.Scanner;

public class EnterCustomerData {
    
    public void customerData(int checkES){
        Scanner input = new Scanner(System.in);
        
        String customerName;
        String phoneNumber;
        String postcode;
        
        System.out.print("Please enter customer name: ");
        customerName = input.nextLine();
        
        System.out.print("Please enter customer phone number: ");
        phoneNumber = input.nextLine();
        
        System.out.print("Please enter customer postcode: ");
        postcode = input.nextLine();
        
        try (FileWriter fw = new FileWriter("cust.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {
            
            try (LineNumberReader lnr = new LineNumberReader(new FileReader("cust.csv"))) {
                lnr.skip(Long.MAX_VALUE);
                int rowsCount = -1;
                rowsCount = lnr.getLineNumber();
                
                if(rowsCount == 0){
                   pw.print("custId");
                   pw.print(",");
                   pw.print("custName");
                   pw.print(",");
                   pw.print("phoneNum");
                   pw.print(",");
                   pw.print("postcode");
                   pw.println();
                }
                
            } catch (IOException e) {
                System.out.println("An error occured while printing first row in cust.csv file.");
                e.printStackTrace();
            }

            pw.print(getCustId());
            pw.print(",");
            pw.print(customerName);
            pw.print(",");
            pw.print(phoneNumber);
            pw.print(",");
            pw.print(postcode);
            pw.println();
        }
        catch(IOException e){
            System.out.println("An error occured while writing new customer data in cust.csv. ");
            e.printStackTrace();
        }
    }
    
    private String getCustId() {
        int rowsCount = -1;
        String customerId = null;

        try (LineNumberReader lnr = new LineNumberReader(new FileReader("cust.csv"))) {
            lnr.skip(Long.MAX_VALUE);
            rowsCount = lnr.getLineNumber();
            System.out.println("Number of rows in the sales.csv file before adding new data: " + rowsCount);
            
            if(rowsCount > 999){
                customerId = "C" + (rowsCount);
            } else if(rowsCount > 99) {
                customerId = "C0" + (rowsCount);
            } else if (rowsCount > 9) {
                customerId = "C00" + (rowsCount);
            } else if (rowsCount > 0){
                customerId = "C000" + (rowsCount); 
            } else if(rowsCount == 0) {
                customerId = "C000" + (rowsCount + 1);
            } else {
                System.out.println("An error occured while generating customerId in cust.csv file."); 
            }
        } catch (IOException e) {
            System.out.println("An error occured while counting number of rows in cust.csv file.");
            e.printStackTrace();
        }
        return customerId;
    }
}
