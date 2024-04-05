package students.items;

public class Corn {
    private static final int MATURATION_AGE = 4;
    private static final int DEATH_AGE = 6;
    private static final double MONETARY_VALUE = 4;
    private static final double BUY_COST = 3;

    private static int generationCount = 0;

    public Corn() {
        super(MATURATION_AGE, DEATH_AGE, MONETARY_VALUE);
        generationCount++;
    }

    public static int getGenerationCount() {
        return generationCount;
    }

    public static double getBuyCost() {
        return BUY_COST;
    }

    @Override
    public String toString() {
        if (getAge() < getMaturationAge()) {
            return "c"; // Representing young corn with lowercase 'c'
        } else {
            return "C"; // Representing mature corn with uppercase 'C'
        }
    }
}
