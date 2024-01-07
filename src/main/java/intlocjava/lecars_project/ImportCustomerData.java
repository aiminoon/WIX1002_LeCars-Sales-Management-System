package intlocjava.lecars_project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */

class Customer {
    
private String custId;
    private String custName;
    private String phoneNum;
    private String postCode;
    
    public Customer(String customerId, String name, String phone, String code){
        this.custId = customerId;
        this.custName = name;
        this.phoneNum = phone;
        this.postCode = code;
    }
    
    public String getCustId() {
        return custId;
    }
    
    public String getName() {
        return custName;
    }
    
    public String getPhoneNum() {
        return phoneNum;
    }
    
    public String getPostCode() {
        return postCode;
    }
    
    @Override
    public String toString(){
        return "Customer ID: " + custId + ", Names: " + custName + ", phone number: " + phoneNum + ", post code: " + postCode; 
    }

}

public class ImportCustomerData {
    // Remove the comments on the main method to import the files by running the ImportCustomerData class itself
    /*public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator + "cust.csv";
        
        System.out.println(filePath);
        
        ImportCustomerData.importCustomers();
    }*/
    
    private static List<Employees> employees = new ArrayList<>();
    
    public static String getFilePath(){
        String currentDir = System.getProperty("user.dir");
        return currentDir + File.separator + "cust.csv";
    }
   
    
    public static void importCustomers(){
        System.out.println(getFilePath());
        
        String dir = System.getProperty("user.home");
        String path = dir + File.separator + "Downloads" + File.separator + "cust.csv";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));  
            BufferedWriter bw = new BufferedWriter(new FileWriter("cust.csv"));
            
            String line;
            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                Customer cust = new Customer(data[0], data[1], data[2], data[3]);
                
                bw.write(line);
                bw.newLine();
                
            }
            br.close();
            bw.close();
        
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
  
