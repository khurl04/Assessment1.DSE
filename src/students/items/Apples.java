package students.items;

public class Apples {
	private static final int MATURATION_AGE = 3;
    private static final int DEATH_AGE = 5;
    private static final double MONETARY_VALUE = 3;
    private static final double BUY_COST = 2;

    private static int generationCount = 0;

   
    public Apples() {
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
            return "a";
        } else {
            return "A";
        }
    }
}
