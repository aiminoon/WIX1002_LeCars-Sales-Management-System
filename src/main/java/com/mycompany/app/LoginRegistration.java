/*----------------------------------------------------------------------------------------
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *---------------------------------------------------------------------------------------*/

package com.mycompany.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LoginRegistration {
        public static void main(String[] args) {
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to LeCars Sales Management System");
            System.out.println("-----------------------------------------");
            System.out.println("Would you like to: ");
            System.out.println("1. Login");
            System.out.println("2. Register(Sales level only)");
            System.out.println("3. Exit program");
            int enterSystem = input.nextInt();
            
            if (enterSystem == 3)
                break;
            System.out.println("-----------------------------------------");
            
            switch (enterSystem) {
                case 1:
                    Login();
                    break;
                case 2:
                    Register();
                    break;
            }
        }
    }
        
    public static void Login() {
        String line = "";
        String splitBy = ",";
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter employee name:");
        String name = input.nextLine();
        System.out.println("Enter password:");
        String password = input.nextLine();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("employee.csv"));
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String[] employee = line.split(splitBy);
                if (name.equals(employee[1]) && password.equals(employee[3])) {
                    System.out.println("Welcome " + name + "!");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Wrong employee name or password!");
                System.out.println("Please try again!\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while login.");
            e.printStackTrace();
        }
    }
    
    private static void Register() {
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
            
            if(rowsCount>=999){
                employeeId="E" + (rowsCount+1);
            } else if(rowsCount>=99) {
                employeeId="E0" + (rowsCount+1);
            } else if (rowsCount>=9) {
                employeeId="E00" + (rowsCount+1);
            } else if (rowsCount>=0) {
                    employeeId="E000" + (rowsCount+1);
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
