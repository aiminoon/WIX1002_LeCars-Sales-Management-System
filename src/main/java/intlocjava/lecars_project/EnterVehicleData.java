package intlocjava.lecars_project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EnterVehicleData {
    public static void vehicleData(){
        
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to: ");
        System.out.println("1. Enter New Vehicle Data");
        System.out.println("2. Update Vehicle Data");
        int checkVehicle = input.nextInt();
        
        switch(checkVehicle){
            case 1:
                newVehicleData();
                break;
            case 2:
                updateVehicleData();
                break;
            default:
                System.out.println("You entered an invalid option.");
        }
        input.close();
    }
        
    public static void newVehicleData() {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Please enter car number plate: ");
        String carPlate = input.nextLine();
        
        System.out.print("Please enter car model: ");
        String carModel = input.nextLine();
        
        System.out.print("Please enter car acquired price: ");
        String AcquiredPrice = input.nextLine();

        try (FileWriter fw = new FileWriter("vehicle.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {
            
            String soldPrice = null;
            final int CARSTATUS = 1;
            
            pw.print(carPlate);
            pw.print(",");
            pw.print(carModel);
            pw.print(",");
            pw.print(AcquiredPrice);
            pw.print(",");
            pw.print(CARSTATUS);
            pw.println();
            pw.print(soldPrice);
            pw.println();
            
            System.out.println("Successfully saved the vehicle data in vehicle.csv file.");
            
        } catch(IOException e){
            System.out.println("An error occured while storing vehicle data in vehicle.csv file.");
            e.printStackTrace();
        }
        input.close();
    }
    
    public static void updateVehicleData() {
        Scanner input = new Scanner(System.in);
    
        System.out.print("Please enter car number plate: ");
        String carPlate = input.nextLine();

        try {
            RandomAccessFile raf = new RandomAccessFile("vehicle.csv", "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
                String line = raf.readLine();
                String[] vehicleData = line.split(",");
                if (carPlate.equals(vehicleData[0])) {
                    found = true;
                    break;
                }
            }

            if (found == true) {
                File tmpFile = new File("temp.txt");
                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

                raf.seek(0);
                while (raf.getFilePointer() < raf.length()) {
                    String updatedLine = raf.readLine();
                    String[] vehicleData = updatedLine.split(",");
                    if (carPlate.equals(vehicleData[0])) {
                        final int CARSTATUS = 0;

                        System.out.print("Please enter car sold price: ");
                        String soldPrice = input.nextLine();

                        updatedLine  = carPlate + ","
                        + vehicleData[1] + ","
                        + vehicleData[2] + ","
                        + CARSTATUS + ","
                        + soldPrice;
                    }                                                                                                              
                    tmpraf.writeBytes(updatedLine);                                
                    tmpraf.writeBytes(System.lineSeparator());
                }

                raf.seek(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }
                raf.setLength(tmpraf.length());

                tmpraf.close();
                raf.close();

                tmpFile.delete();
            }                     
            System.out.println("Successfully updated the vehicle data in vehicle.csv file.");

        } catch (IOException e) {
            System.out.println("An error occurred while updating vehicle data in vehicle.csv file.");
            e.printStackTrace();
        }
        input.close();
    }
}
