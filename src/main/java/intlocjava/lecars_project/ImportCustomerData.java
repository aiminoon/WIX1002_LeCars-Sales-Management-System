package intlocjava.lecars_project;

import java.io.*;

public class ImportCustomerData {
    
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
                bw.write(line);
                bw.newLine();
            }
            
            br.close();
            bw.close();
            
            try (LineNumberReader lnr = new LineNumberReader(new FileReader("cust.csv"))) {
                lnr.skip(Long.MAX_VALUE);
                int rowsCount = -1;
                rowsCount = lnr.getLineNumber();
                System.out.println("Number of rows in cust.csv file is " + rowsCount);
                
            } catch (IOException e) {
                System.out.println("An error occured while counting number of rows in cust.csv file.");
                e.printStackTrace();
            }
            
        } catch (IOException e){
            System.out.println("An error occured when importing customer data from cust.csv");
            e.printStackTrace();
        }
    }
}
