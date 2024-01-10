package intlocjava.lecars_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ImportEmployeeData {

    public static String getFilePath(){
        String currentDir = System.getProperty("user.dir");
        
        return currentDir + File.separator + "employee.csv";
    }
    
    public static void importEmployee() {
        System.out.println(getFilePath()); 
        
        String dir = System.getProperty("user.home");
        String path = dir + File.separator + "Downloads" + File.separator + "employee.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path));
             BufferedWriter bw = new BufferedWriter(new FileWriter("employee.csv"))) {

            String line;
            
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine(); 
            }

            br.close();
            bw.close();
            
        } catch (IOException e) {
            System.out.println("An error occured when importing sales data from sales.csv");
            e.printStackTrace();
        }
    } 
}

