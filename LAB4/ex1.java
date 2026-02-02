class Callme {
    synchronized void call(String msg) {
        System.out.println("[" + msg + "]");
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}

class Caller extends Thread {
    String msg;
    Callme obj;

    public Caller(Callme targ, String s) {
        msg = s;
        obj = targ;
    }

    // run method must be inside Caller
    public void run() {
        obj.call(msg);
    }
}

public class ex1 {
    public static void main(String[] args) {
        Callme target = new Callme();

        Caller c1 = new Caller(target, "Manipal");
        c1.start();

        Caller c2 = new Caller(target, "Institute");
        c2.start();

        Caller c3 = new Caller(target, "of");
        c3.start();

        new Caller(target, "Technology").start();
    }
}
// explainn output w and wo synchronization - give disadvantages of why not +
// need only entire program with output