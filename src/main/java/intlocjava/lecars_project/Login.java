package intlocjava.lecars_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    public static int Login() {
        int employeeStatus = -1;
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
                    employeeStatus = Integer.valueOf(employee[2]);
                    System.out.println("Welcome " + name + "!\n");
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
        return employeeStatus;
    }
}
