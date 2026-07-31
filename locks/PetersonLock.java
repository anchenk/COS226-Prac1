/*
    Anchen Kruger, u25073703
    Caleb Jennings, u25173805
    Chloe Larsen, u25004141
*/

package locks;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class PetersonLock extends Lock {

    /*
     * Using AtomicIntegerArray so that each index acts like a volatile boolean
     * variable.
     * A regular boolean array does not have volatile indexes.
     * 0: false, 1: true
     */
    private static final AtomicIntegerArray flag = new AtomicIntegerArray(2);
    private static volatile boolean turn = false; // volatile prevents each thread from caching its value

    private final int lockIndex;
    private final int otherLockIndex;

    public PetersonLock(boolean lockId) {
        super(lockId);
        lockIndex = lockId ? 1 : 0;
        otherLockIndex = lockId ? 0 : 1;
    }

    @Override
    public void lock() {
        flag.set(lockIndex, 1);
        turn = !lockId;

        while (turn != lockId && flag.get(otherLockIndex) == 1) {
            // wait
        }
    }

    public void unlock() {
        flag.set(lockIndex, 0);
    }
}