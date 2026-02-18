//base class
class Book {

    private int bid;
    private String name;
    private float price;
    private boolean status;

    // setter methods
    public void setBid(int bid) {
        this.bid = bid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Getter methods
    public int getBid() {
        return bid;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public boolean getStatus() {
        return status;
    }
}

// Main class
public class q1 {
    public static void main(String[] args) {
        Book b1 = new Book();

        b1.setBid(1001);
        b1.setName("Az");
        b1.setPrice(299);
        b1.setStatus(true);

        System.out.println("Book Details");
        System.out.println("----------------");
        System.out.println("Book ID  :" + b1.getBid());
        System.out.println("Author   :" + b1.getName());
        System.out.println("Price    :" + b1.getPrice());
        System.out.println("Status   :" + b1.getStatus());
    }
}