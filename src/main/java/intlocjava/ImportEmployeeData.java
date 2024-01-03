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

class EmployeeData { 
    private static List<Employees> employees = new ArrayList<>();
    public static String getFilePath(){
        String currentDir = System.getProperty("user.dir");
        return currentDir + File.separator + "employee.csv";
    }
    
    public static void importEmployee(){
        System.out.println(getFilePath());
        try {
            BufferedReader br = new BufferedReader(new FileReader("employee.csv"));
            String header = br.readLine(); //Skip the header line (assuming 1st line is a header)
            String line;
            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                Employees emp = new Employees(data[0], data[1], Integer.parseInt(data[2]), data[3]);
                employees.add(emp); 
            }
        
        } catch (IOException e){
            e.printStackTrace();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void addManagementEmp(String employeeId, String employeeName, String password){
        Employees newEmp = new Employees(employeeId, employeeName, 1, password);
        employees.add(newEmp);
        System.out.println("Added Management Level Employee: " + newEmp);
    }
    
    public static List<Employees> getEmployees(){
        return employees; 
    }
}

public class ImportEmployeeData {
    public static void main(String[] args) {
        EmployeeData.importEmployee();
        
        //EmployeeData.addManagementEmp("E0016", "Han Yoonseo", "qi820djw");
        
    /*    List<Employees> allEmp = EmployeeData.getEmployees();
        System.out.println("\nAll Employees:");
        for (Employees emp : allEmp) {
            System.out.println(emp);
        } */
    }
}
