package com.mycompany.app;

import java.util.Scanner;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ChangePassword {
    public void change() {
        Scanner scan = new Scanner(System.in);
        
        String oldPassword, newPassword;
        
        System.out.print("Enter old password: ");
        oldPassword = scan.nextLine();

        try {
        RandomAccessFile raf = new RandomAccessFile("employee.csv", "rw");
        
        while (raf.getFilePointer() < raf.length()) {
            long currentPosition = raf.getFilePointer();
            String line = raf.readLine();
            String[] employeeData = line.split(",");
            
            if (oldPassword.equals(employeeData[3])) {
                System.out.print("Enter new password: ");
                newPassword = scan.nextLine();
                
                String updatedLine = employeeData[0] + ","
                        + employeeData[1] + "," 
                        + employeeData[2] + "," 
                        + newPassword ;

                raf.seek(currentPosition);

                raf.writeBytes(String.format("%-" + line.length() + "s", updatedLine));

                raf.seek(currentPosition + line.length());

                raf.close();
                break;
            }
        }
        System.out.println("Successfully updated password in employee.csv file.");
        
        } catch (IOException e) {
        System.out.println("An error occurred while updating password in employee.csv file.");
        e.printStackTrace();
        }
    }
}
