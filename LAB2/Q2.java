import java.util.Scanner;

public class Q2 {

    // i) defined enum, constructor and method respectively
    enum RoomType {
        STANDARD(2000),
        DELUXE(3000),
        SUITE(5000);

        private double BTariff;

        RoomType(double BTariff) {
            this.BTariff = BTariff;
        }

        public double getBTariff() {
            return BTariff;
        }

        public double calcTotalCost(int days) {
            return BTariff * days;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. STANDARD");
        System.out.println("2. DELUXE");
        System.out.println("3. SUITE");
        System.out.println("Select Room Type:");

        int choice = scanner.nextInt();
        RoomType select;

        switch (choice) {
            case 1:
                select = RoomType.STANDARD;
                break;
            case 2:
                select = RoomType.DELUXE;
                break;
            case 3:
                select = RoomType.SUITE;
                break;
            default:
                System.out.println("Invalid");
                return;
        }

        System.out.print("Enter number of days of stay: ");
        int days = scanner.nextInt();
        double total = select.calcTotalCost(days); // enum methods asked in ii)
        // displaying all details
        System.out.println("Room Type: " + select);
        System.out.println("Base Tariff per Day: " + select.getBTariff());
        System.out.println("Number of Days: " + days);
        System.out.println("Total Room Cost: " + total);
    }
}
