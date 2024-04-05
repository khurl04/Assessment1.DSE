package students;

public class Farm {
	 private final Field field;
	 private double bankBalance;
	    
	public Farm(int fieldWidth, int fieldHeight, int startingFunds)
	{
		 this.field = new Field(fieldHeight, fieldWidth);
	        this.bankBalance = startingFunds;
	}
	
	public void run()
	{  Scanner scanner = new Scanner(System.in);
    boolean quit = false;

    while (!quit) {
       
        System.out.println(field);
        System.out.println("Bank balance: $" + bankBalance);
        System.out.println("Enter your next action:");
        System.out.println("  t x y: till");
        System.out.println("  h x y: harvest");
        System.out.println("  p x y: plant");
        System.out.println("  s: field summary");
        System.out.println("  w: wait");
        System.out.println("  q: quit");

       
        String action = scanner.nextLine().trim().toLowerCase();

      
        switch (action) {
            case "t":
                till(scanner);
                break;
            case "h":
                harvest(scanner);
                break;
            case "p":
                plant(scanner);
                break;
            case "s":
                System.out.println(field.getSummary());
                break;
            case "w":
                field.tick();
                break;
            case "q":
                quit = true;
                break;
            default:
                System.out.println("Invalid action. Please try again.");
        }
    }
    scanner.close();
}


private void till(Scanner scanner) {
    System.out.println("Enter the location to till (x y):");
    int x = scanner.nextInt();
    int y = scanner.nextInt();
    scanner.nextLine(); 
    if (isValidLocation(x, y)) {
        field.till(y - 1, x - 1);
        System.out.println("Soil tilled at location " + x + ", " + y);
    } else {
        System.out.println("Invalid location. Please try again.");
    }
}


private void harvest(Scanner scanner) {
    System.out.println("Enter the location to harvest (x y):");
    int x = scanner.nextInt();
    int y = scanner.nextInt();
    scanner.nextLine(); 
    if (isValidLocation(x, y)) {
        Item item = field.get(y - 1, x - 1);
        if (item instanceof Food && item.getValue() > 0) {
            bankBalance += item.getValue();
            field.plant(y - 1, x - 1, new Soil());
            System.out.println("Harvested and earned $" + item.getValue());
        } else {
            System.out.println("No harvestable food at location " + x + ", " + y);
        }
    } else {
        System.out.println("Invalid location. Please try again.");
    }
}


private void plant(Scanner scanner) {
    System.out.println("Enter the location to plant (x y):");
    int x = scanner.nextInt();
    int y = scanner.nextInt();
    scanner.nextLine(); 
    if (!isValidLocation(x, y)) {
        System.out.println("Invalid location. Please try again.");
        return;
    }

    if (field.get(y - 1, x - 1) instanceof Soil) {
        System.out.println("Enter:");
        System.out.println(" - 'a' to buy an apple for $" + Apple.getBuyCost());
        System.out.println(" - 'g' to buy grain for $" + Grain.getBuyCost());
        String choice = scanner.nextLine().trim().toLowerCase();

        switch (choice) {
            case "a":
                if (bankBalance >= Apple.getBuyCost()) {
                    field.plant(y - 1, x - 1, new Apple());
                    bankBalance -= Apple.getBuyCost();
                    System.out.println("Apple planted at location " + x + ", " + y);
                } else {
                    System.out.println("You don't have enough funds to buy an apple.");
                }
                break;
            case "g":
                if (bankBalance >= Grain.getBuyCost()) {
                    field.plant(y - 1, x - 1, new Grain());
                    bankBalance -= Grain.getBuyCost();
                    System.out.println("Grain planted at location " + x + ", " + y);
                } else {
                    System.out.println("You don't have enough funds to buy grain.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    } else {
        System.out.println("Cannot plant in non-soil location.");
    }
}

	
	private boolean isValidLocation(int x, int y) {
	    return x >= 1 && x <= field.getWidth() && y >= 1 && y <=
	}

}






