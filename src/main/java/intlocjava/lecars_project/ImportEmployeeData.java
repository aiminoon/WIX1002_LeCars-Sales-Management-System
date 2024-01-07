package intlocjava.lecars_project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Employees {
    private String employeeId;
    private String employeeName;
    private int employeeStatus;
    private String password;
    
    public Employees(String id, String name, int stat, String pwd){
        this.employeeId = id;
        this.employeeName = name;
        this.employeeStatus = stat;
        this.password = pwd;
    }
    
    public String getId() {
        return employeeId;
    }
    
    public String getName() {
        return employeeName;
    }
    
    public int getStatus() {
        return employeeStatus;
    }
    
    public String getPwd() {
        return password;
    }
    
    @Override
    public String toString(){
        String status = (employeeStatus == 0) ? "Sales Employee" : "Management Level Employee";
        return "Employee Id: " + employeeId + ", Employee Name: " + employeeName + ", Employee Status: " + status + ", Password: " + password;
    }

}

class ImportEmployeeData { 
    private static List<Employees> employees = new ArrayList<>();
    public static String getFilePath(){
        String currentDir = System.getProperty("user.dir");
        return currentDir + File.separator + "employee.csv";
    }
    
    public static void importEmployee(){
        System.out.println(getFilePath());
        
        String dir = System.getProperty("user.home");
        String path = dir + File.separator + "Downloads" + File.separator + "employee.csv";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            BufferedWriter bw = new BufferedWriter(new FileWriter("employee.csv"));
            
            String header = br.readLine(); 
            bw.write(header);
            String line;
            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                Employees emp = new Employees(data[0], data[1], Integer.parseInt(data[2]), data[3]);
                employees.add(emp); 
                
                bw.newLine();
                bw.write(line);
            }
            
            br.close();
            bw.close();
            
        } catch (IOException e){
            e.printStackTrace();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void addManagementEmp(String empName, String password){
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

            final int EMPLOYEESTATUS = 1;
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
            pw.print(empName);
            pw.print(",");
            pw.print(EMPLOYEESTATUS);
            pw.print(",");
            pw.println(password);

            System.out.println("Successfully added.");

        } catch (IOException e) {
            System.out.println("An error occurred while storing information  of new user in file.");
            e.printStackTrace();
        }
    }
    
    public static List<Employees> getEmployees(){
        return employees; 
    }
}

// Remove the comments on the main method to import the files by running the ImportCustomerData class itself
/*public class ImportEmployeeData {
    public static void main(String[] args) {
        ImportEmployee.importEmployee();
        
        ImportEmployee.addManagementEmp("Han Yoonseo", "qi820djw");
        
        List<Employees> allEmp = ImportEmployee.getEmployees();
        System.out.println("\nAll Employees:");
        for (Employees emp : allEmp) {
            System.out.println(emp);
        } 
    } 
} */ 


