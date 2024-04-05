package students.items;

public class Grain{
	 private static final int MATURATION_AGE = 2;
	    private static final int DEATH_AGE = 6;
	    private static final double MONETARY_VALUE = 2;
	    private static final double BUY_COST = 1;

	    private static int generationCount = 0;

	   
	    public Grain() {
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
	            return "g"; 
	        } else {
	            return "G"; 
	        }
	    }
	
}
