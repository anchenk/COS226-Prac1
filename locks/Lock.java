package locks;

public abstract class Lock {
    protected final boolean lockId;

    protected Lock(boolean lockId) {
        this.lockId = lockId;
    }

    public abstract void lock();

    public abstract void unlock();
}
