class Task1 implements Runnable {
    public void run() {
        synchronized (ex3.lock1) {
            System.out.println("Thread1:Holding lock1");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (ex3.lock1) {
                System.out.println("Thread1:Holding lock1");
            }
        }
    }
}

class Task2 implements Runnable {
    public void run() {
        synchronized (ex3.lock2) {
            System.out.println("Thread2:Holding lock2");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (ex3.lock2) {
                System.out.println("Thread2:Holding lock2");
            }
        }
    }
}

class ex3 {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task1());
        Thread t2 = new Thread(new Task2());

        t1.start();
        t2.start();
    }

}

// entire program + output