package intlocjava.lecars_project;

import java.io.*; 

class Vehicles {
    private String carPlate;
    private String carModel;
    private int acquirePrice;
    private int carStatus;
    private int salesPrice;
    
    public Vehicles(String plate, String mod, int acPri, int stat, int salePri){
        this.carPlate = plate;
        this.carModel = mod;
        this.acquirePrice = acPri;
        this.carStatus = stat;
        this.salesPrice = salePri;
    }
    
    public String getCarPlate() {
        return carPlate;
    }
    
    public String getModel() {
        return carModel;
    }
    
    public int getAcPrice() {
        return acquirePrice;
    }
    
    public int getCarStatus() {
        return carStatus;
    }
    
    public int getSalesPrice() {
        return salesPrice; 
    }
    
    @Override
    public String toString(){
        return "Car Plate: " + carPlate + ", Car Model: " + carModel + ", Acquire Price: " + acquirePrice + ", Car Status: " + carStatus + ", Sales Price: " + salesPrice; 
    }

}

class VehicleData {
    public static void importAndExportVehicle() {
        String dir = System.getProperty("user.home");
        String path = dir + File.separator + "Downloads" + File.separator + "vehicle.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path));
             BufferedWriter bw = new BufferedWriter(new FileWriter("vehicle.csv"))) {

            br.readLine(); // Skip the header line (assuming 1st line is a header)
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    Vehicles vehicle = new Vehicles(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                    String csvRepresentation = vehicle.toString(); // or use a toCSVFormat method if you have one
                    bw.write(csvRepresentation);
                    bw.newLine();
                } else {
                    System.out.println("Incomplete data for a vehicle: " + line);
                }
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

}

public class ImportVehicleData {
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator + "vehicle.csv";
        
        System.out.println(filePath);
        
        VehicleData.importAndExportVehicle();
    }
}
