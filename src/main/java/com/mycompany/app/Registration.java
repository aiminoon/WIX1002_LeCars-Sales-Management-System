package com.mycompany.app;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Registration {
    public void Register() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter secret key: ");
        String secretKey = sc.nextLine();
        String secretKeyAns = "WIX1002";

        if (secretKey.equals(secretKeyAns)) {
            System.out.println("Verified secret key.");
        } else {
            System.out.println("Invalid secret key.");
            return;
        }

        System.out.print("Enter your full name: ");
        String employeeName = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        int rowsCount = -1;
        try (LineNumberReader lnr = new LineNumberReader(new FileReader("employee.csv"))) {
            lnr.skip(Long.MAX_VALUE);
            rowsCount = lnr.getLineNumber() - 1;
            System.out.println("Number of rows in the file: " + rowsCount);
        } catch (IOException e) {
            System.out.println("An error occured while counting number of rows.");
            e.printStackTrace();
        }
        
        try (FileWriter fw = new FileWriter("employee.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {

            final int EMPLOYEESTATUS = 0;
            String employeeId = null;
            
            if(rowsCount>999){
                employeeId="E" + (rowsCount+1);
            } else if(rowsCount>99) {
                employeeId="E0" + (rowsCount+1);
            } else if (rowsCount>9) {
                employeeId="E00" + (rowsCount+1);
            }
                    
            pw.print(employeeId);
            pw.print(",");
            pw.print(employeeName);
            pw.print(",");
            pw.print(EMPLOYEESTATUS);
            pw.print(",");
            pw.println(password);

            System.out.println("Successfully registered.");

        } catch (IOException e) {
            System.out.println("An error occurred while storing information  of new user in file.");
            e.printStackTrace();
        }
        System.out.println("Registration successful. You can now log in.");
    }
}
