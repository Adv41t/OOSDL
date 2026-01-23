class RoomCleaning extends Thread { // creatig thread class by extending to thread
    private int rno;

    public RoomCleaning(int rno) {
        this.rno = rno;
    }

    @Override
    public void run() {
        try {
            System.out.println("Room Cleaning started for Room " + rno);
            Thread.sleep(2000); // 2sec
            System.out.println("Room Cleaning completed for Room " + rno);
        } catch (InterruptedException e) {
            System.out.println("Room Cleaning interrupted for Room " + rno);
        }
    }
}

class FoodDelivery implements Runnable {// creating to runnable class by implenting runnable interface
    private int rno;

    public FoodDelivery(int rno) {
        this.rno = rno;
    }

    @Override
    public void run() {
        try {
            System.out.println("Food Delivery started for Room " + rno);
            Thread.sleep(3000); // 3sec
            System.out.println("Food Delivery completed for Room " + rno);
        } catch (InterruptedException e) {
            System.out.println("Food Delivery interrupted for Room " + rno);
        }
    }
}

class Maintenance extends Thread {
    private int rno;

    public Maintenance(int rno) {
        this.rno = rno;
    }

    @Override
    public void run() {
        try {
            System.out.println("Maintenance started for Room " + rno);
            Thread.sleep(4000); // 4sec
            System.out.println("Maintenance completed for Room " + rno);
        } catch (InterruptedException e) {
            System.out.println("Maintenance interrupted for Room " + rno);
        }
    }
}

// Main Program
public class Q1 {
    public static void main(String[] args) {
        System.out.println("Hotel Room Service Management System\n");
        RoomCleaning cleaning = new RoomCleaning(101);
        Thread foodDelivery = new Thread(new FoodDelivery(102));
        Maintenance maintenance = new Maintenance(103);
        // start all threads simultaneously
        cleaning.start();
        foodDelivery.start();
        maintenance.start();

        System.out.println("\nAll service tasks completed.");
    }
}
