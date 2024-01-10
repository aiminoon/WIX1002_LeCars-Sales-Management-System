package intlocjava.lecars_project;

import java.io.*; 

public class ImportVehicleData {
    
    public static String getFilePath(){
        String currentDir = System.getProperty("user.dir");
        
        return currentDir + File.separator + "vehicle.csv";
    }

    public static void importVehicle() {
        System.out.println(getFilePath());
        
        String dir = System.getProperty("user.home");
        String path = dir + File.separator + "Downloads" + File.separator + "vehicle.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path));
             BufferedWriter bw = new BufferedWriter(new FileWriter("vehicle.csv"))) {

            String line;
            
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            
            br.close();
            bw.close();
            
        } catch (IOException e) {
            System.out.println("An error occured when importing vehicle data from vehicle.csv");
            e.printStackTrace();
        }
    }
}
