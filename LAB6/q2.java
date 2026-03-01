import java.io.*;
import java.util.*;
import java.io.Serializable;

class Room implements Serializable {
    int roomNumber;
    String roomType;
    double price;
    boolean isBooked;
    String guestName;

    Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isBooked = false;
        this.guestName = "";
    }
}
public class q2 {

    static final String FILE_NAME = "rooms.ser";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Add Room\n2.Display All\n3.Search Room\n4.Update Booking\n5.Exit");
            int choice = sc.nextInt();

            try {
                List<Room> rooms = readRooms();

                if (choice == 1) {
                    System.out.print("Room No: ");
                    int no = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Type: ");
                    String type = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    rooms.add(new Room(no, type, price));
                    writeRooms(rooms);
                    System.out.println("Room Added.");

                } else if (choice == 2) {

                    for (Room r : rooms) {
                        System.out.println(r.roomNumber + " " + r.roomType + " " +
                                r.price + " Booked: " + r.isBooked +
                                " Guest: " + r.guestName);
                    }

                } else if (choice == 3) {

                    System.out.print("Room No: ");
                    int no = sc.nextInt();

                    for (Room r : rooms)
                        if (r.roomNumber == no)
                            System.out.println(r.roomNumber + " " + r.roomType +
                                    " " + r.price + " Booked: " + r.isBooked);

                } else if (choice == 4) {

                    System.out.print("Room No: ");
                    int no = sc.nextInt();
                    sc.nextLine();

                    for (Room r : rooms) {
                        if (r.roomNumber == no) {
                            r.isBooked = !r.isBooked;
                            if (r.isBooked) {
                                System.out.print("Guest Name: ");
                                r.guestName = sc.nextLine();
                            } else {
                                r.guestName = "";
                            }
                        }
                    }

                    writeRooms(rooms);
                    System.out.println("Booking Updated.");

                } else {
                    break;
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }

    static void writeRooms(List<Room> rooms) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        oos.writeObject(rooms);
        oos.close();
    }

    @SuppressWarnings("unchecked")
    static List<Room> readRooms() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return new ArrayList<>();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
        List<Room> rooms = (List<Room>) ois.readObject();
        ois.close();
        return rooms;
    }
}