public class Q1 {

    public static void main(String[] args) {
        // initialize using primitive
        double roomTariff = 2500.75;
        int days = 3;
        double service = 1200.50;

        // i)converting to wrapper class from primtive by autoboxing
        Double roomTariffObj = roomTariff;
        Integer daysObj = days;
        Double serviceObj = service;

        // ii) calcs by unboxed value(primtive)
        double totalcharge = roomTariffObj * daysObj;
        double totalBill = totalcharge + serviceObj;

        // iii)display
        System.out.println("Room Tariff): " + roomTariffObj);
        System.out.println("Number of Days Stayed: " + daysObj);
        System.out.println("Service Charges: " + serviceObj);
        System.out.println();
        System.out.println("Total Hotel Bill: " + totalBill);
    }
}
