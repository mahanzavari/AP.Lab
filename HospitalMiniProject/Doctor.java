//mahan zavari 40231027 Spring of 2024 Ap Lab
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Doctor extends Person {
    private ArrayList<String> availableDates = new ArrayList<>();

    public Doctor(Hospital hospital, String username, String password, int money) {
        super(hospital, username, password, money);
    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("1. Visit\n2. Fill Table\n3. Logout");
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline
                switch (choice) {
                    case 1:
                        visit(scanner);
                        break;
                    case 2:
                        fillTable(scanner);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();  // consume the invalid input
            }
        }
    }

    public void fillTable(Scanner scanner) {
        try {
            System.out.println("Enter available dates (comma-separated): ");
            String[] dates = scanner.nextLine().split(",");
            for (String date : dates) {
                availableDates.add(date.trim());
            }
            hospital.addAvailability(this.username, availableDates);
            System.out.println("Availability updated.");
        } catch (Exception e) {
            System.out.println("Error while updating availability: " + e.getMessage());
        }
    }

    public void visit(Scanner scanner) {
        try {
            System.out.println("Enter the date for the visit: ");
            String date = scanner.nextLine();
            ArrayList<Reserve> reservations = hospital.getReservations(username);
            if (reservations == null) {
                System.out.println("No reservations found for this date.");
                return;
            }
            for (Reserve reserve : reservations) {
                if (reserve.getDate().equals(date)) {
                    System.out.println("Visiting patient: " + reserve.getPatient().getUsername());
                    return;
                }
            }
            System.out.println("No reservations found for this date.");
        } catch (Exception e) {
            System.out.println("Error during visit: " + e.getMessage());
        }
    }
}
