/*
    Anchen Kruger, u25073703
    Caleb Jennings, u25173805
    Chloe Larsen, u25004141
*/

package locks;

public abstract class Lock {
    protected final boolean lockId;

    protected Lock(boolean lockId) {
        this.lockId = lockId;
    }

    public abstract void lock();

    public abstract void unlock();
}
