package settings;

/**
 * The type Counter.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return this.counter;
    }
}
