class HotelDisplay {

    public static <T> void display(T data) {
        System.out.println("Data: " + data);
    }

    public static void main(String[] args) {

        display(101);          // Integer
        display("Deluxe");     // String
        display(4500.75);      // Double
        display(true);         // Boolean
    }
}