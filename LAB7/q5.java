class Pair<T, U> {

    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public void display() {
        System.out.println("Room Number: " + first);
        System.out.println("Guest Name: " + second);
        System.out.println("-------------------");
    }
}

public class BookingModule {
    public static void main(String[] args) {

        Pair<Integer, String> booking1 =
                new Pair<>(101, "Ravi");

        Pair<Integer, String> booking2 =
                new Pair<>(202, "Anita");

        booking1.display();
        booking2.display();
    }
}