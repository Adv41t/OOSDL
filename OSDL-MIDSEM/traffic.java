// Interface
interface TrafficOperations {
    void vehiclePass(SignalEvent event);
    void turnRed();
}

// Base Class
class TrafficController {
    protected String junctionName;
    protected int totalVehiclesPassed;
    protected boolean isRed;

    public TrafficController(String junctionName) {
        this.junctionName = junctionName;
        this.totalVehiclesPassed = 0;
        this.isRed = false; // starts green
    }
}

//Enum
enum SignalEvent {
    CAR(1),
    BUS(3),
    TRUCK(2),
    RED(0);

    private int count;

    SignalEvent(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}

//Derived Class
class SmartSignal extends TrafficController implements TrafficOperations {

    public SmartSignal(String junctionName) {
        super(junctionName);
    }

    // Vehicles passing (thread-safe)
    @Override
    public synchronized void vehiclePass(SignalEvent event) {

        if (isRed) {
            return; // no vehicles after red
        }

        totalVehiclesPassed += event.getCount();

        System.out.println("\nVehicle: " + event);
        System.out.println("Vehicles Passed: " + event.getCount());
        System.out.println("Total Vehicles So Far: " + totalVehiclesPassed);

        notifyAll(); // notify monitor
    }

    // Turning signal red (only once)
    @Override
    public synchronized void turnRed() {

        if (!isRed) {
            isRed = true;

            System.out.println("\nSignal turned RED at " + junctionName);

            notifyAll(); // wake monitor
        }
    }

    // Monitor waits here
    public synchronized void waitForRed() {
        while (!isRed) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nMonitor Report:");
        System.out.println("Final Total Vehicles Passed: " + totalVehiclesPassed);
    }

    public synchronized boolean isSignalRed() {
        return isRed;
    }
}

// Vehicle Thread
class VehicleThread extends Thread {
    private SmartSignal signal;

    public VehicleThread(SmartSignal signal) {
        this.signal = signal;
    }

    @Override
    public void run() {

        SignalEvent[] sequence = {
            SignalEvent.CAR,
            SignalEvent.BUS,
            SignalEvent.TRUCK,
            SignalEvent.CAR
        };

        for (SignalEvent event : sequence) {

            if (signal.isSignalRed())
                break;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}

            signal.vehiclePass(event);
        }
    }
}

// Signal Thread
class SignalThread extends Thread {
    private SmartSignal signal;

    public SignalThread(SmartSignal signal) {
        this.signal = signal;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2500); // turn red after 2.5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        signal.turnRed();
    }
}

// Monitor Thread
class MonitorThread extends Thread {
    private SmartSignal signal;

    public MonitorThread(SmartSignal signal) {
        this.signal = signal;
    }

    @Override
    public void run() {
        signal.waitForRed();
    }
}

// Main Class
public class traffic {

    public static void main(String[] args) {

        SmartSignal signal = new SmartSignal("Main Junction");

        VehicleThread vehicles = new VehicleThread(signal);
        SignalThread redSignal = new SignalThread(signal);
        MonitorThread monitor = new MonitorThread(signal);

        vehicles.start();
        redSignal.start();
        monitor.start();
        try {//not reqd since they are not actually simulataneous
            vehicles.join();
            redSignal.join();
            monitor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
