package controller;

import items.*;
import items.properties.*;
import people.*;
import people.features.*;
import tools.Input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Storage {

    private Employee employee = new Employee();
    private Customer customer = new Customer();
    private Album album = new Album();
    private Inventory inventory = new Inventory();
    private Rental rental = new Rental();
    private Game game = new Game();
    private Rating rating = new Rating();
    private Message message = new Message();
    private Input input = Input.getInstance();

    // "kind of" Storage

    private ArrayList<Album> albums = new ArrayList<>(Arrays.asList(
            new Album("London Calling", "The Clash", 1980, 14.99, false, null),
            new Album("Legend", "Bob Marley & The Wailers", 1984, 17.99, true, LocalDate.of(2020, 8, 23)),
            new Album("The Dark Side of the Moon", "Pink Floyd", 1973, 24.99, false, null),
            new Album("The Black Album", "Metallica", 1991, 19.99, true, LocalDate.of(2020, 8, 23)),
            new Album("Blood Sugar Sex Magik", "Red Hot Chili Peppers", 1991, 18.99, false, null)));

    private ArrayList<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("Bob", 1974, "1044 Randolph Street", 13457),
            new Employee("Jill", 1985, "3845 Rainbow Street", 14568),
            new Employee("Jack", 1934, "1453 Tilden Street", 16893),
            new Employee("Anna", 1959, "1854 Rose Avenue", 13578),
            new Employee("Sam", 1993, "1784 Sunrise Blvd", 12385),
            new Employee("Emanuel", 1992, "1039 Surfer's Paradise Lane", 12547)));

    private ArrayList<Game> games = new ArrayList<>(Arrays.asList(
            new Game("Sonic: The Hedgehog", "Explore", 18.99, 1857, false, null),
            new Game("Crash Bandicoot", "Racing", 17.59, 1957, false, null),
            new Game("The Legend of Zelda", "Explore", 12.29, 1874, true, LocalDate.of(2020, 8, 20)),
            new Game("Prince of Persia", "Impossible", 15.39, 1984, false, null),
            new Game("Super Mario", "Classic", 18.99, 1999, false, null),
            new Game("Street Fighter", "Fighting", 11.99, 1991, true, LocalDate.of(2020, 8, 20)),
            new Game("Tekken", "Fighting", 17.99, 1932, false, null)));

    private ArrayList<Customer> customerList = new ArrayList<>(Arrays.asList(
            new Customer("Vernita", new Membership("Silver", 0)),
            new Customer("Navya", new Membership(null, 0)),
            new Customer("Drake", new Membership(null, 0)),
            new Customer("Altan", new Membership("Silver", 0)),
            new Customer("Karen", new Membership(null, 0)),
            new Customer("Axel", new Membership("Gold", 0))));

    private ArrayList<Rental> rentalHistory = new ArrayList<>(Arrays.asList(
            new Rental("test1", "test1", 1756.34),
            new Rental("test2", "test2", 1546.65),
            new Rental("test3", "test3", 2247.93),
            new Rental("test4", "test4", 1966.28)
    ));

    public ArrayList<Rental> getRentalHistory() {
        return rentalHistory;
    }

    void itemsByProfit() {
        rentalHistory.sort(Comparator.comparingDouble(Rental::getRentExpense));
        Collections.reverse(rentalHistory);
        rentalHistory.forEach(System.out::println);
    }

    void albumsByFrequency() {
        albums.sort(Comparator.comparingInt(Inventory::getRentalFrequency));
        Collections.reverse(albums);
        System.out.println(input.ANSI_PURPLE + "> Albums" + input.ANSI_RESET);
        int i = 1;
        for (Album album : albums) {
            System.out.print(" " + i + ") " + album.getTitle() + " by " + album.getArtist() + " - Rented ");
            System.out.print(album.getRentalFrequency() + " times." + input.EOL);
            i++;
        }
    }

    void gamesByFrequency() {
        games.sort(Comparator.comparingInt(Inventory::getRentalFrequency));
        Collections.reverse(games);
        System.out.println(input.ANSI_PURPLE + "> Games" + input.ANSI_RESET);
        int i = 1;
        for (Game game : games) {
            System.out.print(" " + i + ") " + game.getTitle() + " - Rented ");
            System.out.print(album.getRentalFrequency() + " times." + input.EOL);
            i++;
        }
    }

    void customerByProfit() {

    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Customer> getCustomers() {
        return customerList;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer() {
        this.customer = customer;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public void rentGame() {
        String maxRentals = "You currently have too many rentals for your membership! That's awesome that you enjoy our products so much!";
        String user = input.getInput("Please enter your name: ");
        for (Customer customer : customerList) {
            if (user.equalsIgnoreCase(getCustomer().getName())) {
                switch (customer.getMembershipType()) {
                    case "Silver":
                        if (customer.getMaxNumberOfRentals() < 4) {
                            rental.rentGame(getGames());
                            customer.incrementMaxNumberOfRentals();
                        } else {
                            System.out.println(maxRentals);
                        }
                        break;
                    case "Gold":
                        if (customer.getMaxNumberOfRentals() < 6) {
                            rental.rentGame(getGames());
                            customer.incrementMaxNumberOfRentals();
                        } else {
                            System.out.println(maxRentals);
                        }
                        break;
                    case "Platinum":
                        if (customer.getMaxNumberOfRentals() < 8) {
                            rental.rentGame(getGames());
                            customer.incrementMaxNumberOfRentals();
                        } else {
                            System.out.println(maxRentals);
                        }
                        break;
                    default:
                        if (customer.getMaxNumberOfRentals() < 2) {
                            rental.rentGame(getGames());
                            customer.incrementMaxNumberOfRentals();
                        } else {
                            System.out.println(maxRentals);
                        }
                        break;
                }
            }
        }
        rental.rentGame(getGames());
    }

    public void returnGame() {
        String name = input.getInput("Hiya! What is your name, customer? ");
        boolean contains = false;
        for (Customer customer : customerList) {
            if (customer.getName().equalsIgnoreCase(name)) {
                contains = true;
                viewGames();
                Rental newTransaction = rental.returnGame(customer.getMembership().getCredits(), customer.getMembershipType(), customer.getId(), getGames());
                getRentalHistory().add(newTransaction);
            }
        }
        if (!contains) {
            System.out.println("That customer doesn't exist on our database (maybe not at all!?), please try again.");
            returnGame();
        }
    }

    public void viewTransactions() {
        for (Rental rental : getRentalHistory()) {
            System.out.println(rental);
        }
    }

    public void totalProfit() {
        double profit = rental.getRentalIncome();
        System.out.println("The total profit is " + profit + "SEK");
    }

    public void ratingAverage() {
        int average = 0;
        System.out.println("The average rating is " + average);
    }

    public void addCustomer() {
        this.customerList.add(customer.addCustomer());
        System.out.println(customerList.toString());
    }

    public void removeCustomer() {
        int removeId = input.getInt("Enter the ID of the customer you want to remove. " + input.EOL + "ID: ");
        this.customerList.removeIf(customer -> customer.getId().equals(removeId));
        viewCustomer();
    }

    public void viewCustomer() {
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
    }

    // public ArrayList<Membership> addMembership(){
    //     return this.customer.addMembership(getCustomers());
    // }
    public ArrayList<Membership> addMembership() {
        return this.customer.addMembership(getCustomers());
    }

    public ArrayList<Membership> upgradeMembership() {
        return this.customer.upgradeMembership(getCustomers());
    }

    public void addEmployee() {
        this.employees.add(employee.addEmployee());
    }

    public void removeEmployee() {
        String removeID = input.getInput("Enter the ID of the employee you want to remove." + input.EOL + "Employee ID: ");
        this.employees.removeIf(employee -> employee.getId().equals(removeID));
        System.out.println("Employee has been deleted of the face of the earth!" + input.EOL);
    }

    public void viewEmployee() {
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    public void addAlbum() {
        this.albums.add(album.addAlbum());
    }

    public void removeAlbum() {
        String removeID = input.getInput("Remove." + input.EOL + "Album ID: ");
        this.albums.removeIf(album -> album.getID().equals(removeID));
        System.out.println("Album has been sent to the moon and is no longer retrievable!" + input.EOL);
    }


    public void rentAlbum() {
        String maxRentals = "Sorry you need to return an item to able rent a new one, you've reached your memberships maximum rentals. Let the other customers enjoy stuff also!";
        String user = input.getInput("Please enter your name: ");
        for (Customer customer : customerList) {
            if (user.equalsIgnoreCase(getCustomer().getName())) {
                switch (customer.getMembershipType()) {
                    case "Silver":
                        if (customer.getMembership().getCredits() >= 5) {
                            customer.getMembership().setCredits(0);
                        }
                        if (customer.getMembership().getCredits() >= 0) {
                            customer.getMembership().incrementCredit();
                        }
                        break;
                    case "Gold":
                        if (customer.getMembership().getCredits() >= 5) {
                            customer.getMembership().setCredits(0);
                        }
                        if (customer.getMembership().getCredits() >= 0) {
                            for (int i = 1; i < 2; i++) {
                                customer.getMembership().incrementCredit();
                            }
                        }
                        break;
                    case "Platinum":
                        if (customer.getMembership().getCredits() >= 5) {
                            customer.getMembership().setCredits(0);
                        }
                        if (customer.getMembership().getCredits() >= 0) {
                            for (int i = 1; i < 3; i++) {
                                customer.getMembership().incrementCredit();
                            }
                        }
                        break;
                    default:
                        break;

                }
            }
        }
        rental.rentAlbum(getAlbums());
    }

    public void returnAlbum() {
        String name = input.getInput("Type your name to begin a lengthy and tedious return process: ");
        boolean contains = false;
        for (Customer customer : customerList) {
            if (customer.getName().equalsIgnoreCase(name)) {
                contains = true;
                Rental newTransaction = rental.returnAlbum(customer.getMembership().getCredits(), customer.getMembershipType(), customer.getId(), getAlbums());
                switch (customer.getMembershipType()) {
                    case "Silver":
                        if (customer.getMembership().getCredits() >= 5) {
                            customer.getMembership().setCredits(0);
                        }
                        if (customer.getMembership().getCredits() >= 0) {
                            customer.getMembership().incrementCredit();
                        }
                        break;
                    case "Gold":
                        if (customer.getMembership().getCredits() >= 5) {
                            customer.getMembership().setCredits(0);
                        }
                        if (customer.getMembership().getCredits() >= 0) {
                            for (int i = 0; i < 2; i++) {
                                customer.getMembership().incrementCredit();
                            }
                        }
                        break;
                    case "Platinum":
                        if (customer.getMembership().getCredits() >= 5) {
                            customer.getMembership().setCredits(0);
                        }
                        if (customer.getMembership().getCredits() >= 0) {
                            for (int i = 0; i < 3; i++) {
                                customer.getMembership().incrementCredit();
                            }
                        }
                        break;
                    default:
                        break;

                }
                getRentalHistory().add(newTransaction);
            }
        }
        if (!contains) {
            System.out.println("That customer doesn't exist within the void that we store our customer information, please try again.");
            returnAlbum();
        }
    }

    public void viewAlbums() {
        albums.sort(Comparator.comparingInt(Album::getYear));
        Collections.reverse(albums);
        albums.forEach(System.out::println);
    }

    public void searchAlbums() {
        int google = input.getInt("Album Search" + input.EOL + "Year: ");
        for (Album album : albums) {
            if (album.getYear() == google) {
                System.out.println(album.toString());
            }
        }
    }

    public void viewAlbumsByRating() {
        // sorts albums by comparing the rating value
        // the :: (invokes the method getRating from the album class. compares albums ratings as a double)
        albums.sort(Comparator.comparingDouble(Album::getRating));
        Collections.reverse(albums);
        albums.forEach(System.out::println); // using the :: operator to invoke the println function for each album
    }

    public void addNewGame() {
        int countArray = games.size();
        System.out.print("Title:  ");
        String newGameTitle = input.input.nextLine();

        System.out.print("Genre:  ");
        String newGameGenre = input.input.nextLine();

        System.out.print("Year:  ");
        int newGameYear = input.input.nextInt();

        System.out.print("Daily Rent Fee:  ");
        double newGameRentCost = input.input.nextDouble();
        input.input.nextLine();

        games.add(new Game(newGameTitle, newGameGenre, newGameRentCost, newGameYear));
        System.out.println("Game Added Successfully : " + games.toString());

        System.out.println("1) Add another game" + input.EOL + "2) View all games" + input.EOL + "3) Employee Menu");
        int userChoice = input.input.nextInt();
        if (userChoice == 1) {
            addNewGame();
        } else if (userChoice == 2) {
            viewGames();
        }
    }

    public void removeGame() {
        viewGames();
        System.out.print("Which game should be removed? Enter the Game ID: ");
        String gameId = input.input.nextLine();
        boolean contains = false;
        if (games.contains(gameId)) {
            contains = true;
            System.out.println("Are you sure you want to remove this game from the directory?" + input.EOL + games.toString() + input.EOL + "(Y/N)");
            String doubleCheck = input.input.nextLine();
            if (doubleCheck.equalsIgnoreCase("y")) {
                games.remove(gameId);
                System.out.println("Game fed to a bunch of alpaca's theres no retrieving it anymore...");
            } else {
                System.out.println("Okay, no problem. ");
            }
        } else {
            System.out.println("That game doesn't seem to be in the directory.");
        }
        viewGames();
    }

    public void viewGames() {
        games.sort(Comparator.comparingInt(Game::getYear));
        Collections.reverse(games);
        games.forEach(System.out::println);
    }

    public void searchGames() {
        String google = input.getInput("Game Search" + input.EOL + "Genre: ");
        for (Game game : games) {
            if (game.getGenre().equalsIgnoreCase(google)) {
                System.out.println(game.toString());
            }
        }
    }

    public void viewGamesByRating() {
        games.sort(Comparator.comparingDouble(Game::getRating));
        Collections.reverse(games);
        games.forEach(System.out::println);
    }


    private ArrayList<Message> customerMessages = new ArrayList<>();


    public void sendMessage() {
        viewCustomer();
        String recipientId = input.getInput(input.EOL + "Enter the customer ID of the person you want to communicate with (for those times were communication is necessary with other humans...): ");
        for (Customer customer : customerList) {
            if (customer.getId().equalsIgnoreCase(recipientId)) {
                String senderID = input.getInput("Type your ID: ");
                String senderName = input.getInput("Type your Name: ");
                String subject = input.getInput("Type your Title: ");
                String body = input.getInput("Type your message: ");
                Message newMessage = new Message(subject, body, senderID, senderName);
                System.out.println("Your message has been delivered to our own personal flying T-rex for prompt delivery (we breed them specifically for speed and agility).");

                customer.getInbox().add(newMessage);
            }
        }
    }

    public void viewMessages() {
        String name = input.getInput("Type your name to view your inbox (we know you don't want to see all those unread messages! but you've got to sort it out sometime!): ");
        for (Customer reader : customerList) {
            if (reader.getName().equalsIgnoreCase(name) && reader.getInbox().size() != 0) {
                Collections.reverse(reader.getInbox());
                System.out.println(input.EOL + ">> List of messages in order received <<");
                System.lineSeparator();
                customer.viewMessages(reader);
                Collections.reverse(reader.getInbox());
            } else if (reader.getName().equalsIgnoreCase(name) && reader.getInbox().size() == 0) {
                System.out.println(input.EOL + "No messages to view (guess no one likes you...).");
            }
        }
    }

    public void removeMessages() {
        viewMessages();
        String removeMessage = input.getInput("Enter the message ID you want to delete: ");
        for (Customer customer : customerList) {
            customer.getInbox().removeIf(message -> message.getMessageId().equalsIgnoreCase(removeMessage));
        }
        System.out.println("The message has been deleted.");
        viewMessages();
    }
}