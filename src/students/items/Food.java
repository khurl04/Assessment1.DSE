package students.items;

public class Food {
    public Food(int maturationAge, int deathAge, double monetaryValue) {
        super(maturationAge, deathAge, monetaryValue);
    }

    @Override
    public abstract String toString();

}
