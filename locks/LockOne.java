/*
    Anchen Kruger, u25073703
    Caleb Jennings, u25173805
    Chloe Larsen, u25004141
*/

package locks;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class LockOne extends Lock {
    
    private static final AtomicIntegerArray flag = new AtomicIntegerArray(2);

    private final int lockIndex;
    private final int otherLockIndex;

    public LockOne(boolean lockId) {
        super(lockId);
        lockIndex = lockId ? 1 : 0;
        otherLockIndex = lockId ? 0 : 1;
    }

    @Override
    public void lock() {
        flag.set(lockIndex, 1);
        while (flag.get(otherLockIndex) == 1) {
            // wait
        }
    }

    @Override
    public void unlock() {
        flag.set(lockIndex, 0);
    }
}