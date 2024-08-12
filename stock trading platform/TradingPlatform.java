import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;
// Class representing a User
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}

// Class to manage user registration and login
class UserManager {
    private Map<String, User> users = new HashMap<>();

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // User already exists
        }
        users.put(username, new User(username, password));
        return true;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }
}

// Class representing a Stock
class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

// Class to manage market data (mocked with simple data)
class StockMarket {
    private Map<String, Stock> stocks = new HashMap<>();

    public StockMarket() {
        // Mock data
        stocks.put("AAPL", new Stock("AAPL", 150.00));
        stocks.put("GOOG", new Stock("GOOG", 2700.00));
        stocks.put("MSFT", new Stock("MSFT", 300.00));
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    public void displayMarket() {
        System.out.println("Current Market Data:");
        for (Stock stock : stocks.values()) {
            System.out.println("Stock: " + stock.getSymbol() + ", Price: $" + stock.getPrice());
        }
    }
}

// Class representing the user's portfolio
class Portfolio {
    private User user;
    private Map<String, Integer> stocks = new HashMap<>();

    public Portfolio(User user) {
        this.user = user;
    }

    public void buyStock(Stock stock, int quantity) {
        stocks.put(stock.getSymbol(), stocks.getOrDefault(stock.getSymbol(), 0) + quantity);
        System.out.println("Bought " + quantity + " shares of " + stock.getSymbol() + ".");
    }

    public void sellStock(Stock stock, int quantity) {
        if (stocks.containsKey(stock.getSymbol())) {
            int currentQty = stocks.get(stock.getSymbol());
            if (currentQty <= quantity) {
                stocks.remove(stock.getSymbol());
                System.out.println("Sold all shares of " + stock.getSymbol() + ".");
            } else {
                stocks.put(stock.getSymbol(), currentQty - quantity);
                System.out.println("Sold " + quantity + " shares of " + stock.getSymbol() + ".");
            }
        } else {
            System.out.println("You don't own any shares of " + stock.getSymbol() + ".");
        }
    }

    public void displayPortfolio() {
        System.out.println("Portfolio for " + user.getUsername() + ":");
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            System.out.println("Stock: " + entry.getKey() + ", Quantity: " + entry.getValue());
        }
    }
}

// Main class to simulate the trading platform
public class TradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        StockMarket stockMarket = new StockMarket();

        // Register and login a user
        System.out.print("Register username: ");
        String username = scanner.nextLine();
        System.out.print("Register password: ");
        String password = scanner.nextLine();
        userManager.register(username, password);

        System.out.print("Login username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Login password: ");
        String loginPassword = scanner.nextLine();
        User user = userManager.login(loginUsername, loginPassword);

        if (user != null) {
            Portfolio portfolio = new Portfolio(user);
            System.out.println("Login successful!");

            // Simple loop for buying/selling stocks
            boolean running = true;
            while (running) {
                System.out.println("\n1. View Market Data\n2. Buy Stock\n3. Sell Stock\n4. View Portfolio\n5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        stockMarket.displayMarket();
                        break;
                    case 2:
                        System.out.print("Enter stock symbol to buy: ");
                        String buySymbol = scanner.nextLine();
                        System.out.print("Enter quantity to buy: ");
                        int buyQuantity = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        Stock stockToBuy = stockMarket.getStock(buySymbol);
                        if (stockToBuy != null) {
                            portfolio.buyStock(stockToBuy, buyQuantity);
                        } else {
                            System.out.println("Stock not found!");
                        }
                        break;
                    case 3:
                        System.out.print("Enter stock symbol to sell: ");
                        String sellSymbol = scanner.nextLine();
                        System.out.print("Enter quantity to sell: ");
                        int sellQuantity = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        Stock stockToSell = stockMarket.getStock(sellSymbol);
                        if (stockToSell != null) {
                            portfolio.sellStock(stockToSell, sellQuantity);
                        } else {
                            System.out.println("Stock not found!");
                        }
                        break;
                    case 4:
                        portfolio.displayPortfolio();
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Login failed!");
        }

        scanner.close();
    }
}
