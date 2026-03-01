import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class q1 {

    static final int TYPE_LENGTH = 20; // 20 characters fixed
    static final int RECORD_SIZE = 4 + (2 * TYPE_LENGTH) + 8 + 1;
    // int(4) + 20 chars(2 bytes each) + double(8) + boolean(1)

    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("rooms.dat", "rw");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Add Room\n2.View Room\n3.Update Booking\n4.Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Room No: ");
                int roomNo = sc.nextInt();
                sc.nextLine();

                System.out.print("Room Type: ");
                String type = sc.nextLine();

                System.out.print("Price: ");
                double price = sc.nextDouble();

                raf.seek((roomNo - 1) * RECORD_SIZE);

                raf.writeInt(roomNo);

                StringBuffer sb = new StringBuffer(type);
                sb.setLength(TYPE_LENGTH);
                raf.writeChars(sb.toString());

                raf.writeDouble(price);
                raf.writeBoolean(false);

                System.out.println("Room Added.");

            } else if (choice == 2) {

                System.out.print("Room No: ");
                int roomNo = sc.nextInt();

                raf.seek((roomNo - 1) * RECORD_SIZE);

                int rNo = raf.readInt();

                char[] typeArr = new char[TYPE_LENGTH];
                for (int i = 0; i < TYPE_LENGTH; i++)
                    typeArr[i] = raf.readChar();

                double price = raf.readDouble();
                boolean booked = raf.readBoolean();

                System.out.println("Room: " + rNo);
                System.out.println("Type: " + new String(typeArr).trim());
                System.out.println("Price: " + price);
                System.out.println("Booked: " + booked);

            } else if (choice == 3) {

                System.out.print("Room No: ");
                int roomNo = sc.nextInt();

                raf.seek((roomNo - 1) * RECORD_SIZE + 4 + (2 * TYPE_LENGTH) + 8);

                boolean status = raf.readBoolean();

                raf.seek((roomNo - 1) * RECORD_SIZE + 4 + (2 * TYPE_LENGTH) + 8);
                raf.writeBoolean(!status);

                System.out.println("Booking Updated.");

            } else {
                raf.close();
                sc.close();
                break;
            }
        }
    }
}