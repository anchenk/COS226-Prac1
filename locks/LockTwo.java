/*
    Anchen Kruger, u25073703
    Caleb Jennings, u25173805
    Chloe Larsen, u25004141
*/

package locks;

public class LockTwo extends Lock {

    private static volatile boolean turn = false;


    public LockTwo(boolean lockId) {
        super(lockId);
    }

    @Override
    public void lock() {
        turn = !lockId;

        while (turn != lockId)
        {
            //wait
        }
    }

    @Override
    public void unlock() {
        // intentionally left empty because nothing needs to be released

        //comes from another thread calling lock()
    }

}
