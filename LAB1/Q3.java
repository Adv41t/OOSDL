//base class
class Rooms {
    int roomno;
    double baseTariff;

    public Rooms(int roomno, double baseTariff) {
        this.roomno = roomno;
        this.baseTariff = baseTariff;
    }

    public double calculateTariff() {
        return baseTariff;
    }

    public void displayDetails() {
        System.out.println("Room Number: " + roomno);
        System.out.println("Base Tariff: " + baseTariff);
    }
}

// ii)-derived class
class StandardRoom extends Rooms {
    private boolean AC;

    public StandardRoom(int roomno, double baseTariff, boolean AC) {
        super(roomno, baseTariff);
        this.AC = AC;
    }

    @Override
    public double calculateTariff() {
        if (AC) {
            return baseTariff + 500;
        }
        return baseTariff;
    }
}

class LuxuryRoom extends Rooms {
    private boolean premiumServices;
    private boolean extraAmenities;

    public LuxuryRoom(int roomno, double baseTariff, boolean premiumServices, boolean extraAmenities) {
        super(roomno, baseTariff);
        this.premiumServices = premiumServices;
        this.extraAmenities = extraAmenities;
    }

    @Override
    public double calculateTariff() {
        double tariff = baseTariff;
        if (premiumServices) { // will add extra tariff based on the services provided
            tariff += 2000;
        }
        if (extraAmenities) {
            tariff += 1000;
        }
        return tariff;
    }
}

// iii)-main class w runtime
public class Q3 {
    public static void main(String[] args) {
        Rooms r1 = new Rooms(101, 400.0);
        r1.displayDetails();
        System.out.println("Final Tariff: " + r1.calculateTariff());

        Rooms room2 = new LuxuryRoom(202, 5000, true, true);
        room2.displayDetails();
        System.out.println("Final Tariff: " + room2.calculateTariff());

        Rooms room3 = new StandardRoom(103, 2800, true);
        room3.displayDetails();
        System.out.println("Final Tariff: " + room3.calculateTariff());
    }
}
