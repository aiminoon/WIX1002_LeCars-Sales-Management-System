package intlocjava.lecars_project;

import java.io.*;

public class ImportSalesData {

    public static String getFilePath(){
        String currentDir = System.getProperty("user.dir");
        
        return currentDir + File.separator + "sales.csv";
    }
    
    public static void importSales() {
        System.out.println(getFilePath()); 
        
        String dir = System.getProperty("user.home");
        String path = dir + File.separator + "Downloads" + File.separator + "sales.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path));
             BufferedWriter bw = new BufferedWriter(new FileWriter("sales.csv"))) {

            String line;
            
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine(); 
            }

            br.close();
            bw.close();

            try (LineNumberReader lnr = new LineNumberReader(new FileReader("sales.csv"))) {
                lnr.skip(Long.MAX_VALUE);
                int rowsCount = -1;
                rowsCount = lnr.getLineNumber();
                System.out.println("Number of rows in sales.csv file is " + rowsCount);
                
            } catch (IOException e) {
                System.out.println("An error occured while counting number of rows in sales.csv file.");
                e.printStackTrace();
            }
            
        } catch (IOException e) {
            System.out.println("An error occured when importing sales data from sales.csv");
            e.printStackTrace();
        }
    } 
}
