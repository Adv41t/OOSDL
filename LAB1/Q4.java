//base class
abstract class Roomz {
    protected int roomno;
    protected double bprice;

    public Roomz(int roomno, double bprice) {
        this.roomno = roomno;
        this.bprice = bprice;
    }

    public abstract double calculateTariff();

    public void dispDetails() {
        System.out.println("Room Number: " + roomno);
        System.out.println("Base Price: " + bprice);
    }
}

// ii)-amenities
interface Amenities {
    void provideWifi();

    void provideBreakfast();
}

// i)-derived classes
class SStandardRoom extends Roomz implements Amenities {
    public SStandardRoom(int roomno, double bprice) {
        super(roomno, bprice);
    }

    // override method from base class to modify tariff and then method from
    // ameneties to modify respectively
    @Override
    public double calculateTariff() {
        return bprice + (bprice * 0.420);// 42%+
    }

    @Override
    public void provideWifi() {
        System.out.println("Standard Room: WiFi Provided.");
    }

    @Override
    public void provideBreakfast() {
        System.out.println("Standard Room: Breakfast not provided.");
    }
}

class LLuxuryRoom extends Roomz implements Amenities {
    public LLuxuryRoom(int roomno, double bprice) {
        super(roomno, bprice);
    }

    // override method from base class to modify tariff
    @Override
    public double calculateTariff() {
        return bprice + (bprice * 0.67); // 67%+
    }

    // override methods from amenities to modify wifi and brekast
    @Override
    public void provideWifi() {
        System.out.println("Luxury Room: WiFi provided.");
    }

    @Override
    public void provideBreakfast() {
        System.out.println("Luxury Room: Breakfast provided.");
    }
}

public class Q4 {
    // iii)
    public static void main(String args[]) {
        Roomz r1 = new SStandardRoom(101, 2000.0);
        Roomz r2 = new LLuxuryRoom(202, 5000.0);
        r1.dispDetails();
        Amenities a1 = (Amenities) r1; // cast to interface
        a1.provideWifi();
        a1.provideBreakfast();
        System.out.println("Tariff: " + r1.calculateTariff());
        System.out.println("----------------------------");
        r2.dispDetails();
        Amenities a2 = (Amenities) r2;
        a2.provideWifi();
        a2.provideBreakfast();
        System.out.println("Tariff: " + r2.calculateTariff());
    }
}
