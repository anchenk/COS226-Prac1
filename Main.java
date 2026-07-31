import locks.Lock;
import locks.LockOne;
import locks.LockTwo;
import locks.PetersonLock;

public class Main {
    private enum LockType {
        LOCK_ONE,
        LOCK_TWO,
        PETERSON
    }

    // set lock type here
    private static final LockType LOCK_TYPE = LockType.PETERSON;

    private static int criticalSection = 0;

    private static Lock createLock(boolean lockId) {
        return switch (LOCK_TYPE) {
            case LOCK_ONE -> new LockOne(lockId);
            case LOCK_TWO -> new LockTwo(lockId);
            case PETERSON -> new PetersonLock(lockId);
        };
    }

    private static void startThread(boolean lockId) {
        Thread thread = new Thread(() -> {
            Lock lock = createLock(lockId);

            for (int i = 0; i < 20; i++) { // run 20 times
                try {
                    Thread.sleep(1000); // wait 1 second
                } catch (InterruptedException e) {
                    // empty
                }

                System.out.println("Thread ID: " + Thread.currentThread().threadId()
                        + " | Locking");
                lock.lock();

                System.out.println(
                        "Thread ID: " + Thread.currentThread().threadId()
                                + " | Incrementing critical section, current value: " + criticalSection);

                // access critical section
                criticalSection++;

                System.out.println(
                        "Thread ID: " + Thread.currentThread().threadId()
                                + " | Incremented critical section, new value: "
                                + criticalSection + ". Unlocking.");

                lock.unlock();
                System.out.println("Thread ID: " + Thread.currentThread().threadId()
                        + " | Unlocked");
            }
        });

        thread.start();
    }

    public static void main(String[] args) {
        System.out.println("Starting threads...");

        startThread(false);
        startThread(true);
    }
}
