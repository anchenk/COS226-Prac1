import locks.Lock;
import locks.LockOne;
import locks.LockTwo;
import locks.PetersonLock;

public class Main {
    private enum LockType {
        LockOne,
        LockTwo,
        PetersonLock
    }

    private static int criticalSection = 0;
    private static LockType type;

    private static Lock createLock(boolean lockId, String lockType) {
        lockType = lockType.toUpperCase();

        switch (lockType) {
            case "ONE":
                type = LockType.LockOne;
                return new LockOne(lockId);                
            case "TWO":
                type = LockType.LockTwo;
                return new LockTwo(lockId);//will be two when two is coded
            case "PETERSON":
                type = LockType.PetersonLock;
                return new PetersonLock(lockId);                
            default:
                return null;                
        }        
    }

    private static void startThread(boolean lockId, String lockType) {
        Thread thread = new Thread(() -> {
            Lock lock = createLock(lockId, lockType);
            if(lock == null){
                System.err.println("ERROR: Invalid lock type: " + lockType);
                return;
            }
            criticalSection = 0;
            while (criticalSection < 25) {
                try {
                    Thread.sleep(1000 + (int)(Math.random() * 2000)); // 1 to 3 seconds
                } catch (InterruptedException e) {
                    // empty
                }

                lock.lock();

                // access critical section
                ++criticalSection;
                System.out.println("Using " + type + " Thread " + (lockId ? "1" : "0") + " - Critical Section Count: " + criticalSection);                
                lock.unlock();
            }
        });
        thread.start();
    }

    public static void main(String[] args) {        
        //Peterson
        // startThread(false, "Peterson");
        // startThread(true, "Peterson");
        
        //Lock One
        // startThread(true, "one");
        // startThread(false, "one");

        //Lock Two
        // startThread(true, "two");
        // startThread(false, "two");
    }
}
