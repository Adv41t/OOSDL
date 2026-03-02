import java.util.*;

class Room {
    private int roomNumber;
    private String roomType;
    private double pricePerDay;
    private boolean isAvailable;

    public Room(int roomNumber, String roomType, double pricePerDay) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerDay() { return pricePerDay; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean status) { this.isAvailable = status; }

    @Override
    public String toString() {
        return "Room{" + "No=" + roomNumber + ", Type=" + roomType +
               ", Price=" + pricePerDay + ", Available=" + isAvailable + '}';
    }
}

class Customer {
    private int customerId;
    private String name;
    private String contactNumber;
    private int roomNumberAllocated;

    public Customer(int customerId, String name, String contactNumber) {
        this.customerId = customerId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.roomNumberAllocated = -1; // not allocated initially
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getContactNumber() { return contactNumber; }
    public int getRoomNumberAllocated() { return roomNumberAllocated; }
    public void setRoomNumberAllocated(int roomNumber) { this.roomNumberAllocated = roomNumber; }

    @Override
    public String toString() {
        return "Customer{" + "ID=" + customerId + ", Name=" + name +
               ", Contact=" + contactNumber + ", Room=" + roomNumberAllocated + '}';
    }
}

public class q1 {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static HashMap<Integer, Customer> bookings = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Hotel Management Menu ---");
            System.out.println("1. Add Room");
            System.out.println("2. Display Available Rooms");
            System.out.println("3. Add Customer");
            System.out.println("4. Book Room");
            System.out.println("5. Checkout Customer");
            System.out.println("6. Display All Customers");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addRoom(); break;
                case 2: displayAvailableRooms(); break;
                case 3: addCustomer(); break;
                case 4: bookRoom(); break;
                case 5: checkoutCustomer(); break;
                case 6: displayAllCustomers(); break;
                case 7: System.exit(0);
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private static void addRoom() {
        System.out.print("Enter Room Number: ");
        int num = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Room Type: ");
        String type = sc.nextLine();
        System.out.print("Enter Price per Day: ");
        double price = sc.nextDouble();
        rooms.add(new Room(num, type, price));
        System.out.println("Room added successfully!");
    }

    private static void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room r : rooms) {
            if (r.isAvailable()) System.out.println(r);
        }
    }

    private static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = sc.nextLine();
        customers.add(new Customer(id, name, contact));
        System.out.println("Customer added successfully!");
    }

    private static void bookRoom() {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        Customer cust = customers.stream()
                .filter(c -> c.getCustomerId() == id)
                .findFirst().orElse(null);

        if (cust == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter Room Number: ");
        int roomNum = sc.nextInt();
        Room room = rooms.stream()
                .filter(r -> r.getRoomNumber() == roomNum)
                .findFirst().orElse(null);

        if (room == null || !room.isAvailable()) {
            System.out.println("Room not available!");
            return;
        }

        room.setAvailable(false);
        cust.setRoomNumberAllocated(roomNum);
        bookings.put(roomNum, cust);
        System.out.println("Room booked successfully!");
    }

    private static void checkoutCustomer() {
        System.out.print("Enter Room Number: ");
        int roomNum = sc.nextInt();
        if (bookings.containsKey(roomNum)) {
            Customer cust = bookings.remove(roomNum);
            cust.setRoomNumberAllocated(-1);
            rooms.stream()
                 .filter(r -> r.getRoomNumber() == roomNum)
                 .findFirst().ifPresent(r -> r.setAvailable(true));
            System.out.println("Checkout successful!");
        } else {
            System.out.println("No booking found for this room!");
        }
    }

    private static void displayAllCustomers() {
        System.out.println("Customer Records:");
        for (Customer c : customers) {
            System.out.println(c);
        }
    }
}
