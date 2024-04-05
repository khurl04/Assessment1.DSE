package students.items;

public class Weed
{

    private static final int MATURATION_AGE = Integer.MAX_VALUE; 
    private static final int DEATH_AGE = Integer.MAX_VALUE; 
    private static final double MONETARY_VALUE = -1; 

   
    public Weed() {
        super(MATURATION_AGE, DEATH_AGE, MONETARY_VALUE);
    }

    
    @Override
    public String toString() {
        return "#"; 
    }

}
