class OrderValidation extends Thread { // creating thread class using extend thread
    int ONo;

    public OrderValidation(int ONo) {
        this.ONo = ONo;
    }

    @Override
    public void run() {
        try {
            System.out.println("Order Validation Process started for OrderNumber " + ONo);
            Thread.sleep(2500);// 2.5sec
            System.out.println("Order Validation Complete for OrderNumber " + ONo);
        } catch (InterruptedException e) {
            System.out.println("Order Validation Interrupted.");
        }
    }
}

class PaymentProcessing implements Runnable { // creating thread class by implementing runnable
    int ONo;

    public PaymentProcessing(int ONo) {
        this.ONo = ONo;
    }

    @Override
    public void run() {
        try {
            System.out.println("Payment Processing for OrderNumber " + ONo);
            Thread.sleep(1100);// 1.1sec
            System.out.println("Payment Complete for OrderNumber " + ONo);
        } catch (InterruptedException e) {
            System.out.println("Payment Interrupted.");
        }
    }
}

class OrderShipment extends Thread {
    int ONo;

    public OrderShipment(int ONo) {
        this.ONo = ONo;
    }

    @Override
    public void run() {
        try {
            System.out.println("Order Shipment Started for OrderNumber " + ONo);
            Thread.sleep(6500);// 6.5sec
            System.out.println("Order Delivered for OrderNumber " + ONo);
        } catch (InterruptedException e) {
            System.out.println("Order Shipment Interrupted.");
        }
    }
}

// Main Program
public class Q2 {
    public static void main(String[] args) {
        System.out.println("Online Order Processing System\n");
        OrderValidation orderValidation = new OrderValidation(101);
        Thread payment = new Thread(new PaymentProcessing(101)); // creating a thread object for payment class as
                                                                 // runnable interface was used
        OrderShipment Shipment = new OrderShipment(101);
        // startinng all tasks
        orderValidation.start();
        payment.start();
        Shipment.start();
        // connection all tasks as one after one
        try {
            orderValidation.join();
            payment.join();
            Shipment.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted.");
        }

        System.out.println("\nAll tasks completed.");
    }
}