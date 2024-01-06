public class SalesInsights {
    /*public static void main(String[] args) {

        String employeeIDToSearch = "E0001"; // Example employee ID to search

        calculateSalesByMonth(employeeIDToSearch);
    }*/

    public static void calculateSalesByMonth(String employeeID) {
        Map<Month, Integer> monthlySalesMap = new HashMap<>();
        Map<Month, Integer> monthlyCountMap = new HashMap<>();

        try (BufferedReader vehicleReader = new BufferedReader(new FileReader("vehicle.csv"));
             BufferedReader salesReader = new BufferedReader(new FileReader("sales.csv"))) {

            Map<String, Integer> carPlateToSalesPrice = new HashMap<>();

            String line;
            while ((line = vehicleReader.readLine()) != null) {
                String[] vehicleData = line.split(",");
                if (vehicleData.length >= 5) {
                    String carPlate = vehicleData[0];
                    String salesPriceStr = vehicleData[4];

                    if (!salesPriceStr.isEmpty() && !salesPriceStr.equalsIgnoreCase("null")) {
                        try {
                            int salesPrice = Integer.parseInt(salesPriceStr);
                            carPlateToSalesPrice.put(carPlate, salesPrice);
                        } catch (NumberFormatException e) {
                            System.out.println("");
                        }
                    }
                }
            }

            while ((line = salesReader.readLine()) != null) {
                String[] salesData = line.split(",");
                if (salesData.length >= 5) {
                    String dateTimeStr = salesData[1]; 
                    String carPlate = salesData[2]; 
                    String empID = salesData[4]; 

                    
                    if (empID.equals(employeeID) && carPlateToSalesPrice.containsKey(carPlate)) {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME);
                        Month month = dateTime.getMonth();

                        int salesPrice = carPlateToSalesPrice.get(carPlate);

                        
                        monthlySalesMap.put(month, monthlySalesMap.getOrDefault(month, 0) + salesPrice);
                        monthlyCountMap.put(month, monthlyCountMap.getOrDefault(month, 0) + 1);
                    }
                }
            }

            
            for (Map.Entry<Month, Integer> entry : monthlySalesMap.entrySet()) {
                Month month = entry.getKey();
                int totalSales = entry.getValue();
                int salesCount = monthlyCountMap.getOrDefault(month, 0);
                double averageSales = (double) totalSales / salesCount;

                System.out.println("Month: " + month);
                System.out.println("Total Sales: " + totalSales);
                System.out.println("Average Sales: " + averageSales);
                System.out.println("-----------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 
