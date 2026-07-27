package locks;

public class PetersonLock extends Lock {

    private static final boolean[] flag = new boolean[2];
    private static boolean turn = false;

    private final int lockIndex;
    private final int otherLockIndex;

    public PetersonLock(boolean lockId) {
        super(lockId);
        lockIndex = lockId ? 1 : 0;
        otherLockIndex = lockId ? 0 : 1;
    }

    @Override
    public void lock() {
        flag[lockIndex] = true;
        turn = !lockId;

        while (turn != lockId && flag[otherLockIndex]) {
            // wait
        }
    }

    public void unlock() {
        flag[lockIndex] = false;
    }
}