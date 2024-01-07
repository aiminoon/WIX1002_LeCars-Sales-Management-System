package intlocjava.lecars_project;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.LineNumberReader;
import java.util.Scanner;

public class EnterCustomerData {
    
    public static void customerData(int checkES){
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
        int rowsCount = -1, rowsCountOwn = 0;
        try (LineNumberReader lnr = new LineNumberReader(new FileReader("cust.csv"))) {
            lnr.skip(Long.MAX_VALUE);
            rowsCount = lnr.getLineNumber() - 1;
            System.out.println("Number of rows in the file: " + rowsCount);
        } catch (IOException e) {
            System.out.println("An error occured while counting number of rows.");
            e.printStackTrace();
        }
        
        try (FileWriter fw = new FileWriter("cust.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {
            
            String customerId = null;
            
            if(rowsCount>=999){
                customerId="C" + (rowsCount+1);
            } else if(rowsCount>=99) {
                customerId="C0" + (rowsCount+1);
            } else if (rowsCount>=9) {
                customerId="C00" + (rowsCount+1);
            } else if (rowsCount>=0){
                customerId="C000" + (rowsCount+1); 
            }
            
            pw.print(customerId);
            pw.print(",");
            pw.print(customerName);
            pw.print(",");
            pw.print(phoneNumber);
            pw.print(",");
            pw.print(postcode);
            pw.println();
    }
        catch(IOException e){
            e.printStackTrace();
        }
        
        if(checkES == 0){
            
            String employeeID;
            System.out.print("Enter your employee ID: ");
            employeeID = input.nextLine();
            try (FileWriter fw = new FileWriter(employeeID + "cust.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {
                try (LineNumberReader lineNum = new LineNumberReader(new FileReader(employeeID + "cust.csv"))) {
                lineNum.skip(Long.MAX_VALUE);
                rowsCountOwn = lineNum.getLineNumber();
                System.out.println("Number of rows in the file: " + rowsCountOwn);
                if(rowsCountOwn == 0){
                    pw.print("Customer ID");
                    pw.print(",");
                    pw.print("Customer Name");
                    pw.print(",");
                    pw.print("Phone Number");
                    pw.print(",");
                    pw.print("Postcode");
                    pw.println();
                    
                }
                } catch (IOException e) {
                    System.out.println("An error occured while counting number of rows.");
                    e.printStackTrace();
                }
            
                String customerId = null;

                if(rowsCount>=999){
                    customerId="C" + (rowsCount+1);
                } else if(rowsCount>=99) {
                    customerId="C0" + (rowsCount+1);
                } else if (rowsCount>=9) {
                    customerId="C00" + (rowsCount+1);
                } else if (rowsCount>=0){
                    customerId="C000" + (rowsCount+1); 
                }

                pw.print(customerId);
                pw.print(",");
                pw.print(customerName);
                pw.print(",");
                pw.print(phoneNumber);
                pw.print(",");
                pw.print(postcode);
                pw.println();
            }
        catch(IOException e){
            e.printStackTrace();
        }
        }
        input.close();
    }
}
