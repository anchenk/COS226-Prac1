# Practical 1

To develop an understanding of mutual exclusion algorithms by implementing locking algorithms, demonstrating their behaviour using two concurrent threads and explaining the concepts behind each algorithm. 

Key takeaways: 
- The purpose of mutual exclusion
- Implement software locking algorithms 
- Demonstrate behaviour of concurrent threads 
- Explain why certain algorithms fail 
- Explain how Peterson's Lock resolves these failures 

## Authors

- [@Anchen Kruger - u25073703](https://github.com/anchenk)

- [@Caleb Jennings - u25173805](https://github.com/cablexd)

- [@Chloe Larsen - u25004141](https://github.com/Chloe-Larsen)

### Work distributions:

- Lock one will be completed by Chloe 
- Lock two will be completed by Anchen 
- Peterson's lock will be completed by Caleb

# Lock Explanations

## Lock One

The flag variable was implemented using an `AtomicIntegerArray`, with `0` representing `false` and `1` representing `true`. Using an atomic array was necessary to ensure that each index in the array is volatile, providing thread-safe, atomic access and updates.

The logic behind LockOne is:
- When a thread wants to enter the critical section, it sets it flag to `1` (`true`), indicating that the thread has interest in using the critical section.
- If then enters a while loop, continuously checking if the other threads flag is not set to `1` (`true`)
- If the other thead's flag is `0` (`false`), then the current thread processed to the critical section.
- If the other thead's flag is `1` (`true`), then the current thread waits indefinitely until the the other thread has released its flag. 

Why LockOne fails:
- Both threads can raise their flags to `1` (`true`) simultaneously, this can cause deadlock as thread A will acknowledge B's flag and thread B will acknowledge A's flag leading both threads to wait for the others flag to be set to `0` (`false`) leading to deadlock and starvation as neither will release their flags until the other releases theirs. 

## Lock Two

TODO

## Peterson Lock

The flag variables was implemented with an `AtomicIntegerArray`, using a `0` as `false` and `1` as `true`. Using an atomic array was necessary to have each index in the array be volatile. If a value is not volatile in a concurrent environment, it gets cached by a thread which can cause race conditions and other issues in a concurrent environment.

The logic behind the Peterson lock is as follows:

- If one thread requests the lock, it sees that no other thread interested in using the critical section and so it is allowed through.
- If two threads request the lock, the last thread that requests the lock becomes the victim and allows the first thread to access the critical section first. Then, once the first thread releases the lock, the second thread sees that the other thread is no longer interested in using the critical section and so it is allowed to proceed.

# Our Demonstration

We set up a basic main and a simple integer variable acting as a critical section. Two threads are created and they take turns requesting the lock, incrementing the critical section (integer value), and then releasing the lock in a loop. This setup demonstrates the use of a lock, and can be used to check which locks work properly and which allow deadlock or starvation.