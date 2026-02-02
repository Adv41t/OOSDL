class Hotel {
    private int rooms;

    public Hotel(int totalRooms) {
        this.rooms = totalRooms;
    }

    // Booking room
    public synchronized void bookRoom(String cname) {
        while (rooms == 0) {
            try {
                System.out.println(cname + " is waiting for room");
                wait(); // release lock and wait until notified
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        rooms--;
        System.out.println(cname + " booked a room. Rooms left: " + rooms);
    }

    // Releasing room
    public synchronized void releaseRoom(String cname) {
        rooms++;
        System.out.println(cname + " released room. Rooms available: " + rooms);
        notifyAll(); // notify waiting threads (notify/notifyAll both works)
    }
}

class Customer extends Thread {
    private Hotel hotel;
    private String cname;

    public Customer(Hotel hotel, String cname) {
        this.hotel = hotel;
        this.cname = cname;
    }

    public void run() {
        hotel.bookRoom(cname);
        try {
            Thread.sleep(2000); // time the customer is staying in the room
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        hotel.releaseRoom(cname);
    }
}

public class Q1 {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(2); // just 2 rooms iun total

        // more than 2 rooms are being tried to book
        Customer c1 = new Customer(hotel, "Customer1");
        Customer c2 = new Customer(hotel, "Customer2");
        Customer c3 = new Customer(hotel, "Customer3");
        Customer c4 = new Customer(hotel, "Customer4");

        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}

/*
 * this is the code. Give me the summary of this code using useful code snippets
 * from the same code and explain the concepts used simultaneously.
 * Flow should be like:
 * Summary Concept-snippet Concept - snippet
 * Dont make it too long though - should have around 3-4 main concepts since
 * snippets are expected to be big
 */