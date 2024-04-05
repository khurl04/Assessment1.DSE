package students.items;

public class Item {
	private int age;
    private int maturationAge;
    private int deathAge;
    private double monetaryValue;

    public Item(int maturationAge, int deathAge, double monetaryValue) {
        this.age = 0;
        this.maturationAge = maturationAge;
        this.deathAge = deathAge;
        this.monetaryValue = monetaryValue;
    }

    public void tick() {
        age++;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean died() {
        return age > deathAge;
    }

    public double getValue() {
        if (age >= maturationAge)
            return monetaryValue;
        else
            return 0; // Returns 0 if not matured yet
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Item))
            return false;
        Item other = (Item) obj;
        return this.age == other.age &&
               this.deathAge == other.deathAge &&
               this.maturationAge == other.maturationAge &&
               this.monetaryValue == other.monetaryValue;
    }

    public abstract String toString();
	
}
