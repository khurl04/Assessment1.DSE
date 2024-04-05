package students;


public class Field {
	private final int width;
    private final int height;
    private final Item[][] field;
    
	public Field(int height, int width)
	{
		this.height = height;
        this.width = width;
        this.field = new Item[height][width];
        initializeField();
	}
	private void initializeField() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = new Soil();
            }
        }
    }

    // Perform a tick on each item in the field
    public void tick() {
        Random random = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j].tick();
                if (field[i][j] instanceof Soil && random.nextDouble() < 0.2) {
                    field[i][j] = new Weed();
                } else if (field[i][j].died()) {
                    field[i][j] = new UntilledSoil();
                }
            }
        }
    }

    // Tills the specified location and turns it into new Soil
    public void till(int row, int col) {
        field[row][col] = new Soil();
    }

    // Returns a copy of the item at the specified location
    public Item get(int row, int col) {
        return field[row][col];
    }

    // Stores a given item at a given location
    public void plant(int row, int col, Item item) {
        field[row][col] = item;
    }

    // Returns the total monetary value of each item in the field
    public double getValue() {
        double totalValue = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                totalValue += field[i][j].getValue();
            }
        }
        return totalValue;
    }

    // Returns a string representing the quantities and overall value of the field
    public String getSummary() {
        int applesCount = 0;
        int grainCount = 0;
        int soilCount = 0;
        int untilledCount = 0;
        int weedCount = 0;
        double totalValue = 0;

        // Count and calculate total values
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j] instanceof Apple) {
                    applesCount++;
                    totalValue += field[i][j].getValue();
                } else if (field[i][j] instanceof Grain) {
                    grainCount++;
                    totalValue += field[i][j].getValue();
                } else if (field[i][j] instanceof Soil) {
                    soilCount++;
                } else if (field[i][j] instanceof UntilledSoil) {
                    untilledCount++;
                } else if (field[i][j] instanceof Weed) {
                    weedCount++;
                }
            }
        }

        // Build the summary string
        StringBuilder summary = new StringBuilder();
        summary.append("Apples:       ").append(applesCount).append("\n");
        summary.append("Grain:        ").append(grainCount).append("\n");
        summary.append("Soil:         ").append(soilCount).append("\n");
        summary.append("Untilled:     ").append(untilledCount).append("\n");
        summary.append("Weed:         ").append(weedCount).append("\n");
        summary.append("For a total of $").append(totalValue).append("\n");
        summary.append("Total apples created: ").append(Apple.getGenerationCount()).append("\n");
        summary.append("Total grain created: ").append(Grain.getGenerationCount()).append("\n");

        return summary.toString();
    }

    // Overrides toString() method to print out the field
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        // Print column numbers
        result.append(" ");
        for (int i = 1; i <= width; i++) {
            result.append(" ").append(i);
        }
        result.append("\n");
        // Print row numbers and field contents
        for (int i = 0; i < height; i++) {
            result.append(i + 1).append(" ");
            for (int j = 0; j < width; j++) {
                result.append(field[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
	
}
