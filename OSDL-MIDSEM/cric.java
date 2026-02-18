//Interface
interface BattingOperations {
    void playShot(ShotType shot);
    void getOut();
}

// Base Class
class CricketPlayer {
    protected String name;
    protected int totalRuns;
    protected boolean isOut;

    public CricketPlayer(String name) {
        this.name = name;
        this.totalRuns = 0;
        this.isOut = false;
    }
}

//Enum
enum ShotType {
    SINGLE(1),
    DOUBLE(2),
    FOUR(4),
    SIX(6),
    OUT(0);

    private int runs;

    ShotType(int runs) {
        this.runs = runs;
    }

    public int getRuns() {
        return runs;
    }
}

// 3️⃣ Derived Class
class Batsman extends CricketPlayer implements BattingOperations {

    public Batsman(String name) {
        super(name);
    }

    // Play shot (thread-safe)
    @Override
    public synchronized void playShot(ShotType shot) {

        if (isOut) {
            return;
        }

        if (shot == ShotType.OUT) {
            getOut();
            return;
        }

        totalRuns += shot.getRuns();

        System.out.println("\nShot Type: " + shot);
        System.out.println("Runs Scored: " + shot.getRuns());
        System.out.println("Current Total: " + totalRuns);

        notifyAll(); // notify umpire
    }

    // Get out (thread-safe, only once)
    @Override
    public synchronized void getOut() {

        if (!isOut) {
            isOut = true;

            System.out.println("\nShot Type: OUT");
            System.out.println(name + " is OUT!");
            System.out.println("Final Score: " + totalRuns);

            notifyAll(); // wake umpire
        }
    }

    // Umpire waits here
    public synchronized void waitForDecision() {
        while (!isOut) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nUmpire: Batsman declared OUT!");
    }

    public synchronized boolean isPlayerOut() {
        return isOut;
    }
}
class BattingThread extends Thread {
    private Batsman batsman;

    public BattingThread(Batsman batsman) {
        this.batsman = batsman;
    }
    @Override
    public void run() {

        ShotType[] shots = {
            ShotType.SINGLE,
            ShotType.FOUR,
            ShotType.SIX,
            ShotType.DOUBLE
        };

        for (ShotType shot : shots) {

            if (batsman.isPlayerOut())
                break;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}

            batsman.playShot(shot);
        }
    }
}

//Fielding Thread
class FieldingThread extends Thread {
    private Batsman batsman;

    public FieldingThread(Batsman batsman) {
        this.batsman = batsman;
    }

    public void run() {
        try {
            Thread.sleep(2500); // dismissal attempt after 2.5 sec
        } catch (InterruptedException e) {}

        batsman.getOut();
    }
}

// Umpire Thread
class UmpireThread extends Thread {
    private Batsman batsman;

    public UmpireThread(Batsman batsman) {
        this.batsman = batsman;
    }

    public void run() {
        batsman.waitForDecision();
    }
}

// Main Class
public class cric {
    public static void main(String[] args) {

        Batsman player = new Batsman("Virat");

        BattingThread batting = new BattingThread(player);
        FieldingThread fielding = new FieldingThread(player);
        UmpireThread umpire = new UmpireThread(player);

        batting.start();
        fielding.start();
        umpire.start();
    }
}
