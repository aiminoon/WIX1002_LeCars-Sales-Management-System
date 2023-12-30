/*----------------------------------------------------------------------------------------
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *---------------------------------------------------------------------------------------*/

package intlocjava.lecars_project;        //Will change all the class to this package name.

import java.util.Scanner;

public class main {
        public static void main(String[] args) {
            
            int checkES = -1;
            
            while(true) {
                Scanner input = new Scanner(System.in);
                
                System.out.println("\nWelcome to LeCars Sales Management System");
                System.out.println("-----------------------------------------");
                System.out.println("Would you like to: ");
                System.out.println("1. Login");
                System.out.println("2. Register(Sales level only)");
                System.out.println("3. Exit program");
                System.out.println("-----------------------------------------");
                
                int enterSystem = input.nextInt();

                switch (enterSystem) {
                    case 1:
                        Login login = new Login();
                        checkES = login.Login();
                        break;
                    case 2:
                        Registration register = new Registration();
                        register.Register();
                        break;
                    case 3:
                        System.out.println("Exiting LeCars Sales Management System. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("You entered an invalid option.");
                }

                switch (checkES) {
                    case 0:
                        SalesEmployee(checkES);
                        break;
                    case 1:
                        ManagementEmployee(checkES);
                        break;
                    case -1:
                        System.out.println("Error in checking employee status.");
                        break;
                }
            }
        }
    
    public static void SalesEmployee(int checkES) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Would you like to: ");
        System.out.println("1. Entering New Data");
        System.out.println("2. View Info");
        System.out.println("3. Change password");
        System.out.println("4. Exit program");
        int enterSalesEmployee = scanner.nextInt();
        
        switch (enterSalesEmployee) {
            case 1:
                System.out.println("Would you like to: ");
                System.out.println("1. Entering Customer Data");
                System.out.println("2. Entering Sales Data");
                System.out.println("3. Entering Vechile Data");
                
                int enterData = scanner.nextInt();
                
                if (enterData == 1) {
                    EnterCustomerData ecd = new EnterCustomerData();
                    ecd.customerData(checkES);
                } else if (enterData == 2) {
                    System.out.println("Please enter your employee ID: ");
                    String employeeId = scanner.nextLine();
                    EnterSalesData esd = new EnterSalesData();
                    esd.SalesData(employeeId, checkES);
                } else if (enterData == 3) {
//                    EnterVechileData evd = new EnterVechileData();     *To be implemented by Dennis & Imran*
//                    evd.VechileData();
                } else {
                    System.out.println("You entered an invalid option.");
                    break;
                }
                break;
            case 2:
                System.out.println("Would you like to: ");
                System.out.println("1. View own customer data");
                System.out.println("2. View own sales records");
                System.out.println("3. View all vechile data");
                
                int viewData = scanner.nextInt();
                
                if (viewData == 1) {
                    System.out.println("Please enter your name: ");
                    String employeeName = scanner.nextLine();

                } else if (viewData == 2) {

                } else if (viewData == 3) {

                } else {
                    System.out.println("You entered an invalid option.");
                    break;
                }
                break;
            case 3:
//                ChangePassword cp = new ChangePassword();        *To be implemented by Dennis & Imran*
//                cp.change();
                break;
            case 4:
                System.out.println("Exiting LeCars Sales Management System. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("You entered an invalid option.");
        }
    }

    public static void ManagementEmployee(int checkES) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Would you like to: ");
        System.out.println("1. Entering New Data");
        System.out.println("2. View Info");
        System.out.println("3. Import Data");
        System.out.println("4. Change password");
        System.out.println("5. Exit program");
        
        int enterManagementEmployee = scanner.nextInt();
        
        switch (enterManagementEmployee) {
            case 1:
                System.out.println("Would you like to: ");
                System.out.println("1. Entering Customer Data");
                System.out.println("2. Entering Sales Data");
                System.out.println("3. Entering Vechile Data");
                
                int enterData = scanner.nextInt();
                
                if (enterData == 1) {
                    EnterCustomerData ecd = new EnterCustomerData();
                    ecd.customerData(checkES);
                } else if (enterData == 2) {
                    System.out.println("Please enter your employee ID: ");
                    String employeeId = scanner.nextLine();
                    EnterSalesData esd = new EnterSalesData();
                    esd.SalesData(employeeId, checkES);
                } else if (enterData == 3) {
//                    EnterVechileData evd = new EnterVechileData();        *To be implemented by Dennis & Imran*
//                    evd.VechileData();
                } else {
                    System.out.println("You entered an invalid option.");
                    break;
                }
                break;
            case 2:
                System.out.println("Would you like to: ");
                System.out.println("1. View all customer data");
                System.out.println("2. View all sales records");
                System.out.println("3. View all vechile data");
                System.out.println("4. View all employee data");
                
                int viewData = scanner.nextInt();
                
                if (viewData == 1) {
                    
                } else if (viewData == 2) {

                } else if (viewData == 3) {

                } else if (viewData == 4) {

                } else {
                    System.out.println("You entered an invalid option.");
                    break;
                }
                break;
            case 3:

                break;
            case 4:
//                ChangePassword cp = new ChangePassword();        *To be implemented by Dennis & Imran*
//                cp.change();
                break;
            case 5:
                System.out.println("Exiting LeCars Sales Management System. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("You entered an invalid option.");
        }
    }
}
