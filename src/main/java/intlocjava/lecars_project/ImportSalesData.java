package intlocjava.lecars_project;

import java.io.*;

class Sales {
    
    private String salesId;
    private String dateTime;
    private String carPlate;
    private String custId;
    private String employeeId;
    
    public Sales(String sales, String date, String plate, String cust, String employ){
        this.salesId = sales;
        this.dateTime = date;
        this.carPlate = plate;
        this.custId = cust;
        this.employeeId = employ;
    }
    
    public String getSalesId() {
        return salesId;
    }
    
    public String getDate() {
        return dateTime;
    }
    
    public String getCarPlate() {
        return carPlate;
    }
    
    public String getCustId() {
        return custId;
    }
    
    public String getEmployeeId() {
        return employeeId; 
    }
    
    @Override
    public String toString(){
        return "Sales ID: " + salesId + ", Date: " + dateTime + ", Car Plate: " + carPlate + ", Customer Id: " + custId + ", Employee Id: " + employeeId; 
    }

    /**
     * @return
     */
    public String toCSVFormat() {
        return salesId + "," + dateTime + "," + carPlate + "," + custId + "," + employeeId;
    }

}

class SalesData {
    private static List<Sales> sales = new ArrayList<>();

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
                String[] data = line.split(",");
                if (data.length == 5) {
                    Sales sale = new Sales(data[0], data[1], data[2], data[3], data[4]);
                    // Assuming you have a method in Sales to get CSV representation
                    String csvRepresentation = sale.toCSVFormat();
                    bw.write(csvRepresentation);
                    bw.newLine();
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class ImportSalesData {
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator + "sales.csv";
        
        System.out.println(filePath);
        SalesData.importSales();
        }
    }
    
}
