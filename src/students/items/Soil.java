package students.items;

public class Soil {
	private static final int MATURATION_AGE = Integer.MAX_VALUE;
    private static final int DEATH_AGE = Integer.MAX_VALUE; 
    private static final double MONETARY_VALUE = 0; 

    public Soil() {
        super(MATURATION_AGE, DEATH_AGE, MONETARY_VALUE);
    }


    @Override
    public String toString() {
        return ".";
    }
}
