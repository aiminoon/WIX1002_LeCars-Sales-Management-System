package com.mycompany.app;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EnterSalesData {
    public void SalesData(String employeeId) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter car plate: ");
        String carPlate = scanner.nextLine();
        
        System.out.print("Enter customer Id: ");
        String custId = scanner.nextLine();
        
        try (FileWriter fw = new FileWriter("Sales.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {
                    
            pw.print(getSalesId());
            pw.print(",");
            pw.print(getCurrentDateTime());
            pw.print(",");
            pw.print(carPlate);
            pw.print(",");
            pw.print(custId);
            pw.print(",");
            pw.print(employeeId);

            System.out.println("Successfully saved the sales data in Sales.csv file.");

        } catch (IOException e) {
            System.out.println("An error occurred while storing sales data in Sales.csv file.");
            e.printStackTrace();
        }
    }
       
    private String getCurrentDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
        
    private String getSalesId() {
        int rowsCount = -1;
        String salesId = null;

        try (LineNumberReader lnr = new LineNumberReader(new FileReader("sales.csv"))) {
            lnr.skip(Long.MAX_VALUE);
            rowsCount = lnr.getLineNumber() - 1;
            System.out.println("Number of rows in the Sales.csv file: " + rowsCount);
            if(rowsCount>999){
                salesId="A" + (rowsCount+1);
            } else if(rowsCount>99) {
                salesId="A0" + (rowsCount+1);
            } 
        } catch (IOException e) {
            System.out.println("An error occured while counting number of rows in Sales.csv file.");
            e.printStackTrace();
        }
        return salesId;
    }
}
