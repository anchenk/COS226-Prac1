import locks.Lock;
import locks.PetersonLock;

public class Main {

    private static int criticalSection = 0;

    private static Lock createLock(boolean lockId) {
        // set the type of lock being used here
        return new PetersonLock(lockId);
    }

    private static void startThread(boolean lockId) {
        new Thread(() -> {
            Lock lock = createLock(lockId);

            while (true) {
                try {
                    Thread.sleep(1000 + ((int) Math.random() * 2000)); // 1 to 3 seconds
                } catch (InterruptedException e) {
                    // empty
                }

                lock.lock();

                // access critical section
                criticalSection++;

                lock.unlock();
            }
        });
    }

    public static void main(String[] args) {
        startThread(false);
        startThread(true);
    }
}
