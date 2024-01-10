package intlocjava.lecars_project;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;

public class AddNewManagementEmp {
    
    public static void addManagementEmp(String empName, String password){
        
        int rowsCount = -1;
        
        try (LineNumberReader lnr = new LineNumberReader(new FileReader("employee.csv"))) {
            lnr.skip(Long.MAX_VALUE);
            rowsCount = lnr.getLineNumber() - 1;
            System.out.println("Number of rows in the file: " + rowsCount);
            
        } catch (IOException e) {
            System.out.println("An error occured while counting number of rows in employee.csv.");
            e.printStackTrace();
        }
        
        try (FileWriter fw = new FileWriter("employee.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {

            final int EMPLOYEESTATUS = 1;
            String employeeId = null;
            
            if(rowsCount > 999){
                employeeId = "E" + (rowsCount + 1);
            } else if(rowsCount > 99) {
                employeeId = "E0" + (rowsCount + 1);
            } else if (rowsCount > 9) {
                employeeId = "E00" + (rowsCount + 1);
            }
                    
            pw.print(employeeId);
            pw.print(",");
            pw.print(empName);
            pw.print(",");
            pw.print(EMPLOYEESTATUS);
            pw.print(",");
            pw.println(password);

            System.out.println("Successfully added " + empName + " in employee.csv file.");

        } catch (IOException e) {
            System.out.println("An error occurred while storing information of new management employee in employee.csv file.");
            e.printStackTrace();
        }
    }
}
