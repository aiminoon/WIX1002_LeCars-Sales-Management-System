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

        loop:
        while(true){
        System.out.println("Would you like to: ");
        System.out.println("1. Entering New Data");
        System.out.println("2. View Info");
        System.out.println("3. Change password");
        System.out.println("4. Logout");
        int enterSalesEmployee = scanner.nextInt();
        
        switch (enterSalesEmployee) {
            case 1:
                System.out.println("Would you like to: ");
                System.out.println("1. Entering Customer Data");
                System.out.println("2. Entering Sales Data");
                System.out.println("3. Entering Vehicle Data");
                
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
                    EnterVehicleData evd = new EnterVehicleData();
                    evd.vehicleData();
                } else {
                    System.out.println("You entered an invalid option.");
                    break;
                }
                break;
            case 2:
                System.out.println("Would you like to: ");
                System.out.println("1. View own customer data");
                System.out.println("2. View own sales records");
                System.out.println("3. View all vehicle data");
                
                int viewData = scanner.nextInt();
                
                if (viewData == 1) {
                        System.out.println("Please enter your employee ID: ");
                        String empId = scanner.nextLine();
                        SalesLevelViewCustomer vCust = new SalesLevelViewCustomer();
                        vCust.displayCustRecords(empId);

                } else if (viewData == 2) {
                        System.out.println("Please enter your employee ID: ");
                        String empId = scanner.nextLine();
                        SalesLevelViewSales vsales = new SalesLevelViewSales();
                        vsales.displaySales(empId);

                } else if (viewData == 3) {
                        ViewAllVehicle vVehicle = new ViewAllVehicle();
                        vVehicle.displayVehicles();

                } else {
                    System.out.println("You entered an invalid option.");
                    break;
                }
                break;
            case 3:
                ChangePassword cp = new ChangePassword();
                cp.change();
                break;
            case 4:
                System.out.println("Exiting LeCars Sales Management System. Goodbye!");
                break loop;
            default:
                System.out.println("You entered an invalid option.");
        }
        }
    }

    public static void ManagementEmployee(int checkES) {
        Scanner scanner = new Scanner(System.in);

        loop:
        while(true){
            System.out.println("Would you like to: ");
            System.out.println("1. Entering New Data");
            System.out.println("2. View Info");
            System.out.println("3. Add New Management Employee");
            System.out.println("4. Change password");
            System.out.println("5. Calculate Salary");
            System.out.println("6. view profit margin");
            System.out.println("7. Logout");
            
            int enterManagementEmployee = scanner.nextInt();
            
            switch (enterManagementEmployee) {
                case 1:
                    System.out.println("Would you like to: ");
                    System.out.println("1. Entering Customer Data");
                    System.out.println("2. Entering Sales Data");
                    System.out.println("3. Entering Vehicle Data");
                    
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
                        EnterVehicleData evd = new EnterVehicleData();
                        evd.vehicleData();
                    } else {
                        System.out.println("You entered an invalid option.");
                        break;
                    }
                    break;
                case 2:
                    System.out.println("Would you like to: ");
                    System.out.println("1. View all customer data");
                    System.out.println("2. View all sales records");
                    System.out.println("3. View all vehicle data");
                    System.out.println("4. View all employee data");
                    
                    int viewData = scanner.nextInt();
                    
                    if (viewData == 1) {
                        ViewManagement.viewAllInfo("cust.csv");
                                System.out.println();
                    } else if (viewData == 2) {
                        ViewManagement.viewAllInfo("sales.csv");
                                System.out.println();
                    } else if (viewData == 3) {
                        ViewManagement.viewAllInfo("vehicle.csv");
                                System.out.println();
                    } else if (viewData == 4) {
                        ViewManagement.viewAllInfo("employee.csv");
                                System.out.println();
                    } else {
                        System.out.println("You entered an invalid option.");
                        break;
                    }
                    break;
                case 3:
                    System.out.println("Please enter the name and password of new management employee: ");
                    System.out.println("Name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.println("Password: ");
                    String pwd = scanner.nextLine();
                    ImportEmployeeData importEmp = new ImportEmployeeData();
                    importEmp.addManagementEmp(name, pwd); 
                    break;
                case 4:
                    ChangePassword cp = new ChangePassword();
                    cp.change();
                    break;
                case 5:
                    System.out.println("Enter employee ID and status.");
                    System.out.println("employee ID: ");
                    scanner.nextLine();
                    String ID = scanner.nextLine();
                    System.out.print("employee status(0 for sales, 1 for management): ");
                    int employeeStatus = scanner.nextInt();
                    Salary salary = new Salary(employeeStatus);
                    salary.commisionCalculation(ID);
                    salary.displaySalary(ID);
                    break;
                case 6:
                    System.out.printf("Current Margin : %.2f\n\n", ViewProfitMargin.calculateProfitMargin());
                    break;
                case 7:
                    System.out.println("Exiting LeCars Sales Management System. Goodbye!");
                    break loop;
                default:
                    System.out.println("You entered an invalid option.");
            }
        }
    }
}
