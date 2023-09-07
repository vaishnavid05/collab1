import java.util.ArrayList;
import java.util.Scanner;

abstract class Pizza {
    String name;
    String dough;
    String sauce;
    ArrayList<String> toppings = new ArrayList<>();

    abstract void prepare();

    void bake() {
        System.out.println("Pizza is baking...");
    }

    void cut() {
        System.out.println("Cutting pizza into slices...");
    }

    void box() {
        System.out.println("Packing the pizza");
    }

    public String getName() {
        return name;
    }
}

class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";
        toppings.add("Grated Reggiano Cheese");
    }

    @Override
    void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Dough: " + dough);
        System.out.println("Sauce: " + sauce);
        System.out.println("Toppings: " + String.join(", ", toppings));
    }
}

class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Deep Dish Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
        toppings.add("Shredded Mozzarella Cheese");
    }

    @Override
    void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Dough: " + dough);
        System.out.println("Sauce: " + sauce);
        System.out.println("Toppings: " + String.join(", ", toppings));
    }

    @Override
    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}

abstract class PizzaStore {
    abstract Pizza createPizza(String type);

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        if (pizza != null) {
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            System.out.println("Enjoy your " + pizza.getName() + "!");
        } else {
            System.out.println("Sorry, we don't have that type of pizza.");
        }
        return pizza;
    }
}

class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else {
            return null;
        }
    }
}

class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        } else {
            return null;
        }
    }
}

public class PizzaTestDrive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Pizza Store!");
        System.out.println("Please select the type of pizza you want:");
        System.out.println("1. NY Style Cheese Pizza");
        System.out.println("2. Chicago Style Cheese Pizza");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        PizzaStore pizzaStore;
        String pizzaType;

        if (choice == 1) {
            pizzaStore = new NYPizzaStore();
            pizzaType = "cheese";
        } else if (choice == 2) {
            pizzaStore = new ChicagoPizzaStore();
            pizzaType = "cheese";
        } else {
            System.out.println("Invalid choice. Exiting.");
            return;
        }

        pizzaStore.orderPizza(pizzaType);
        scanner.close();
    }
}

