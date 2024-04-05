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
        this.cropGrid = new int[height][width];
        initializeField();
        initializeCropGrid();
	}
	private void initializeField() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = new Soil();
            }
        }
    }
	


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


    public void till(int row, int col) {
        field[row][col] = new Soil();
    }

    public Item get(int row, int col) {
        return field[row][col];
    }

    public void plant(int row, int col, Item item) {
        field[row][col] = item;
    }

    public double getValue() {
        double totalValue = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                totalValue += field[i][j].getValue();
            }
        }
        return totalValue;
    }

    public String getSummary() {
        int applesCount = 0;
        int cornCount = 0;
        int grainCount = 0;
        int soilCount = 0;
        int untilledCount = 0;
        int weedCount = 0;
        double totalValue = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
            	if (field[i][j] instanceof Apple) {
            	    applesCount++;
            	    totalValue += field[i][j].getValue();
            	} else if (field[i][j] instanceof Grain) {
            	    grainCount++;
            	    totalValue += field[i][j].getValue();
            	} else if (field[i][j] instanceof Corn) { // Include Corn in the summary
            	    cornCount++;
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

        StringBuilder summary = new StringBuilder();
        summary.append("Apples:       ").append(applesCount).append("\n");
        summary.append("Grain:        ").append(grainCount).append("\n");
        summary.append("Soil:         ").append(soilCount).append("\n");
        summary.append("Untilled:     ").append(untilledCount).append("\n");
        summary.append("Weed:         ").append(weedCount).append("\n");
        summary.append("For a total of $").append(totalValue).append("\n");
        summary.append("Total apples created: ").append(Apples.getGenerationCount()).append("\n");
        summary.append("Total grain created: ").append(Grain.getGenerationCount()).append("\n");

        return summary.toString();
    }

   
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(" ");
        for (int i = 1; i <= width; i++) {
            result.append(" ").append(i);
        }
        result.append("\n");
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
