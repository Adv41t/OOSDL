//base class
class Room {
    private int roomNumber;
    private String roomType;
    private double basePrice;

    // i) and ii) - two types of constructors based on the inputs
    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.basePrice = 0.0;
    }

    public Room(int roomNumber, String roomType, double basePrice) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.basePrice = basePrice;
    }

    public void displayDetails() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + roomType);
        System.out.println("Base Price: " + basePrice);
    }
}

// iii)-derived class
class DeluxeRoom extends Room {
    private boolean freeWifi;
    private boolean Breakfast;

    public DeluxeRoom(int roomNumber, String roomType, boolean freeWifi, boolean Breakfast) {
        super(roomNumber, roomType);
        this.freeWifi = freeWifi;
        this.Breakfast = Breakfast;
    }

    public DeluxeRoom(int roomNumber, String roomType, double basePrice, boolean freeWifi, boolean Breakfast) {
        super(roomNumber, roomType, basePrice);
        this.freeWifi = freeWifi;
        this.Breakfast = Breakfast;
    }

    @Override
    public void displayDetails() {
        super.displayDetails(); // call base class method
        // using conditional operator to intialize boolean value of breakfast and wifi
        System.out.println("Free Wi-Fi: " + (freeWifi ? "Yes" : "No"));
        System.out.println("Complimentary Breakfast: " + (Breakfast ? "Yes" : "No"));
    }
}

public class Q2 {
    public static void main(String[] args) {
        // iv)-main class
        Room room1 = new Room(101, "Normal");
        Room room2 = new Room(102, "Normal", 2500.0);
        DeluxeRoom deluxe1 = new DeluxeRoom(201, "Deluxe", true, true);
        DeluxeRoom deluxe2 = new DeluxeRoom(202, "Deluxe", 5000.0, true, false);

        System.out.println("Room 1 Details: ");
        room1.displayDetails();
        System.out.println("\nRoom 2 Details:");
        room2.displayDetails();
        System.out.println("\nDeluxe Room 1 Details:");
        deluxe1.displayDetails();
        System.out.println("\nDeluxe Room 2 Details:");
        deluxe2.displayDetails();
    }
}
