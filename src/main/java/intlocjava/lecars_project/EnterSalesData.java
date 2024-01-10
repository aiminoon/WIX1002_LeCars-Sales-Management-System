package intlocjava.lecars_project;

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
    
    public void SalesData(String employeeId, int checkES) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter car plate: ");
        String carPlate = scanner.nextLine();
        
        System.out.print("Enter customer Id: ");
        String custId = scanner.nextLine();
        
        try (FileWriter fw = new FileWriter("sales.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {
            
            try (LineNumberReader lnr = new LineNumberReader(new FileReader("sales.csv"))) {
                lnr.skip(Long.MAX_VALUE);
                int rowsCount = -1;
                rowsCount = lnr.getLineNumber();

                if(rowsCount == 0){
                   pw.print("salesId");
                   pw.print(",");
                   pw.print("dateTime");
                   pw.print(",");
                   pw.print("carPlate");
                   pw.print(",");
                   pw.print("custId");
                   pw.print(",");
                   pw.print("employeeId");
                   pw.println();
                }
                
            } catch (IOException e) {
                System.out.println("An error occured while printing first row in sales.csv file.");
                e.printStackTrace();
            }
            
            pw.print(getSalesId());
            pw.print(",");
            pw.print(getCurrentDateTime());
            pw.print(",");
            pw.print(carPlate);
            pw.print(",");
            pw.print(custId);
            pw.print(",");
            pw.print(employeeId);
            pw.println();
            
            System.out.println("Successfully saved the sales data in sales.csv file.");

        } catch (IOException e) {
            System.out.println("An error occurred while storing sales data in sales.csv file.");
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
            rowsCount = lnr.getLineNumber();
            System.out.println("Number of rows in the sales.csv file before adding new data: " + rowsCount);
            
            if(rowsCount > 999){
                salesId = "A" + (rowsCount);
            } else if(rowsCount > 99) {
                salesId = "A0" + (rowsCount);
            } else if(rowsCount > 9) {
                salesId = "A00" + (rowsCount);
            } else if(rowsCount > 0) {
                salesId = "A000" + (rowsCount);
            } else if(rowsCount == 0) {
                salesId = "A000" + (rowsCount + 1);
            } else {
                System.out.println("An error occured while generating salesId in sales.csv file."); 
            }
            
        } catch (IOException e) {
            System.out.println("An error occured while counting number of rows in sales.csv file.");
            e.printStackTrace();
        }
        
        return salesId;
    }
}
