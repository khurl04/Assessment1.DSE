package students;


public class Field {
	private final int width;
    private final int height;
    private final Item[][] field;
    private int[][] cropGrid;
    
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
	
	private void initializeCropGrid() {
        Random random = new Random();
        for (int i = 0; i < cropGrid.length; i++) {
            for (int j = 0; j < cropGrid[0].length; j++) {
                // Randomly assign crop types (0: Apple, 1: Grain, 2: Weed)
                cropGrid[i][j] = random.nextInt(3);
            }
        }
    }


    public void tick() {
    	applyCropCompatibility();
    	
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
    
	    private void applyCropCompatibility() {
	        for (int i = 0; i < cropGrid.length; i++) {
	            for (int j = 0; j < cropGrid[0].length; j++) {
	                int compatibilitySum = 0;
	                int neighborCount = 0;
	                for (int x = i - 1; x <= i + 1; x++) {
	                    for (int y = j - 1; y <= j + 1; y++) {
	                        if (x >= 0 && x < cropGrid.length && y >= 0 && y < cropGrid[0].length && !(x == i && y == j)) {
	                            compatibilitySum += CropCompatibility.getCompatibility(cropGrid[i][j], cropGrid[x][y]);
	                            neighborCount++;
	                        }
	                    }
	                }
	                cropGrid[i][j] += compatibilitySum / neighborCount;
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
