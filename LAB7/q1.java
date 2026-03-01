class Room<T, U> {
    private T roomId;
    private U roomAttribute;

    public Room(T roomId, U roomAttribute) {
        this.roomId = roomId;
        this.roomAttribute = roomAttribute;
    }

    public void display() {
        System.out.println("Room ID: " + roomId);
        System.out.println("Room Attribute: " + roomAttribute);
        System.out.println("----------------------");
    }
}

public class q1 {
    public static void main(String[] args) {

        Room<Integer, String> r1 =
                new Room<>(101, "Deluxe");

        Room<String, Double> r2 =
                new Room<>("R-202", 3500.50);

        r1.display();
        r2.display();
    }
}