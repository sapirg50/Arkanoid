package game;

/**
 * The type Counter.
 */
public class Counter {
    private Integer value;

    /**
     * Instantiates a new Counter.
     *
     * @param num the num
     */
    public Counter(Integer num) {
        this.value = num;
    }

    /**
     * Sets value.
     *
     * @param value1 the new value
     */
    public void setValue(Integer value1) {
        this.value = value1;
    }

    /**
     * Increase.
     *
     * @param number - add number to current count.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decrease.
     * <p>
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Gets value.
     *
     * @return the value - the current count.
     */
    public Integer getValue() {
        return this.value;
    }

    /**
     * Equals boolean.
     *
     * @param value1 the value 1
     * @return the boolean
     */
    public boolean equals(Integer value1) {
        if (this.value.equals(value1)) {
            return true;
        }
        return false;
    }
}