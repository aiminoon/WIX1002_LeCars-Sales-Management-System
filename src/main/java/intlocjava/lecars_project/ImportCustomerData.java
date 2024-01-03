package intlocjava.lecars_project;

import java.io.*;
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
    public static void main(String[] args) {
            String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator + "cust.csv";
        
        System.out.println(filePath);
        
        ImportCustomerData.importCustomers();
    }
   
    
    public static void importCustomers(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("cust.csv"));
            String line;
            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                Customer cust = new Customer(data[0], data[1], data[2], data[3]);
                
            }
            br.close();
        
        } catch (IOException e){
            e.printStackTrace();
            }
    }
}
  
