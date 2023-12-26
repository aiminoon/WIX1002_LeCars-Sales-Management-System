package com.mycompany.app;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.LineNumberReader;
import java.util.Scanner;

public class EnterCustomerData {
    
    public static void customerData(){
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
        int rowsCount = -1;
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
    } 
}
