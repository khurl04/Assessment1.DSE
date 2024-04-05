package students.items;

public class UntilledSoil {
	
    private static final int MATURATION_AGE = Integer.MAX_VALUE; 
    private static final int DEATH_AGE = Integer.MAX_VALUE; 
    private static final double MONETARY_VALUE = -1; 

   
    public UntilledSoil() {
        super(MATURATION_AGE, DEATH_AGE, MONETARY_VALUE);
    }

    
    @Override
    public String toString() {
        return "/"; 
    }


}
