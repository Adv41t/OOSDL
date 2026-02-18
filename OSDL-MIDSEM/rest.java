interface OrderOperations{
    void placeOrder(String item);
    void prepareOrder();
    void collectOrder();
}

class Restaurant{
    String currentOrder;
    boolean orderPlaced;
    boolean orderReady;
    public Restaurant(String currentOrder){
        this.currentOrder = currentOrder;
        this.orderPlaced = false;
        this.orderReady = false;
    }
}

class SmartRestaurant extends Restaurant implements OrderOperations{
    public SmartRestaurant(String currentOrder){
        super(currentOrder);
    }
    @Override
public synchronized void placeOrder(String item) {

    while (orderPlaced) {
        try {
            wait();   // wait if previous order not processed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    currentOrder = item;
    orderPlaced = true;
    orderReady = false;

    System.out.println("Customer placed order: " + item);

    notifyAll();   // wake chef
}
@Override
public synchronized void prepareOrder() {

    while (!orderPlaced) {
        try {
            wait();   // wait until customer places order
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    System.out.println("Chef is preparing: " + currentOrder);

    try {
        Thread.sleep(2000); // simulate preparation time
    } catch (InterruptedException e) {}

    orderReady = true;

    System.out.println("Chef prepared: " + currentOrder);

    notifyAll();   // wake customer & manager
}
@Override
public synchronized void collectOrder() {

    while (!orderReady) {
        try {
            wait();   // wait until chef prepares
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    System.out.println("Customer collected order: " + currentOrder);

    orderPlaced = false;
    orderReady = false;

    notifyAll();   // allow next order
    }
}
class CustomerThread extends Thread {
    SmartRestaurant restaurant;
    String item;

    public CustomerThread(SmartRestaurant restaurant, String item) {
        this.restaurant = restaurant;
        this.item = item;
    }
    @Override
    public void run() {
        restaurant.placeOrder(item);
        restaurant.collectOrder();
    }
}
class ChefThread extends Thread {
    SmartRestaurant restaurant;

    public ChefThread(SmartRestaurant restaurant) {
        this.restaurant = restaurant;
    }
    @Override
    public void run() {
        while (true) {
            restaurant.prepareOrder();
        }
    }
}
class ManagerThread extends Thread {
    SmartRestaurant restaurant;

    public ManagerThread(SmartRestaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (restaurant) {
                while (!restaurant.orderReady) {
                    try {
                        restaurant.wait();
                    } catch (InterruptedException e) {}
                }

                System.out.println("Manager: Order ready for delivery!");

                try {
                    restaurant.wait();  // wait until reset happens
                } catch (InterruptedException e) {}
            }
        }
    }
}

public class rest {
    public static void main(String[] args) {

        SmartRestaurant restaurant = new SmartRestaurant("");

        ChefThread chef = new ChefThread(restaurant);
        ManagerThread manager = new ManagerThread(restaurant);

        chef.start();
        manager.start();

        CustomerThread c1 = new CustomerThread(restaurant, "Pizza");
        CustomerThread c2 = new CustomerThread(restaurant, "Burger");

        c1.start();
        c2.start();
        chef.start();
        manager.start();
        try{
            c1.join();
            c2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}









