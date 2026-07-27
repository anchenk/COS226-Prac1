import locks.Lock;
import locks.PetersonLock;
import locks.LockOne;

public class Main {
    private enum LockType {
        LOCK_ONE,
        LOCK_TWO,
        PETERSON
    }

    // set lock type here
    private static final LockType LOCK_TYPE = LockType.LOCK_ONE;

    private static int criticalSection = 0;

    private static Lock createLock(boolean lockId) {
        return switch (LOCK_TYPE) {
            case LOCK_ONE -> new LockOne(lockId);
            case LOCK_TWO -> throw new Error("Unimplemented");
            case PETERSON -> new PetersonLock(lockId);
        };
    }

    private static void startThread(boolean lockId) {
        Thread thread = new Thread(() -> {
            Lock lock = createLock(lockId);

            while (criticalSection < 25) {
                try {
                    Thread.sleep(1000 + (int) (Math.random() * 2000)); // wait 1 to 3 seconds
                } catch (InterruptedException e) {
                    // empty
                }

                lock.lock();

                System.out.println(
                        "Thread ID: " + Thread.currentThread().threadId()
                                + ". Incremeneting critical section, current value: " + criticalSection);

                // access critical section
                criticalSection++;

                System.out.println(
                        "Incremented critical section, new value: " + criticalSection);

                lock.unlock();
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        startThread(false);
        startThread(true);
    }
}
