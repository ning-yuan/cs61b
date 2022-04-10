package gh2;

// TODO: uncomment the following import once you're ready to start this portion
import deque.Deque;
import deque.LinkedListDeque;
// TODO: maybe more imports

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */

    private final Deque<Double> buffer;
    // private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new LinkedListDeque<>();
        int capacity = (int) Math.round(SR/frequency);
        for(int i=0;i<capacity;i++){
            buffer.addLast(0.0);
        }
;
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        for (int i=0;i< buffer.size();i++){
            buffer.removeFirst();
            double r = Math.random() -0.5;
            buffer.addLast(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {

        double frontSample = buffer.removeFirst();
        double secondSample = buffer.get(0);
        double newSample = DECAY*0.5*(frontSample + secondSample);
        buffer.addLast(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.get(0);
    }
}

