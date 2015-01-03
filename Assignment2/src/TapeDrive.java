import java.util.Random;

/**
 * Simulates a tape drive
 */
public class TapeDrive {

    private int[] tape;
    private int currentPos = 0;

    public TapeDrive(int capacity) {
        tape = new int[capacity];
    }

    public void write(int i) {
        tape[currentPos] = i;
        currentPos = (currentPos + 1) % tape.length;
    }

    public int read() {
        int i = tape[currentPos];
        currentPos = (currentPos + 1) % tape.length;
        return i;
    }

    public void reset() {
        currentPos = 0;
    }

    public static TapeDrive generateRandomTape(int capacity) {
       TapeDrive tape = new TapeDrive(capacity);
       Random random = new Random();
    	for( int i = 0; i < capacity; i++){
        tape.write(random.nextInt(10));
       }
    	return tape;
    }
}