/*
    Anchen Kruger, u25073703
    Caleb Jennings, u25173805
    Chloe Larsen, u25004141
*/

package locks;

public class LockOne extends Lock {
    
    private static final boolean[] flag = new boolean[2];

    private final int lockIndex;
    private final int otherLockIndex;

    public LockOne(boolean lockId) {
        super(lockId);
        lockIndex = lockId ? 1 : 0;
        otherLockIndex = lockId ? 0 : 1;
    }

    @Override
    public void lock() {
        flag[lockIndex] = true;
        while (flag[otherLockIndex]) {
            // wait
        }
    }

    @Override
    public void unlock() {
        flag[lockIndex] = false;
    }
}