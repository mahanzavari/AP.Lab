import java.util.InputMismatchException;
import java.util.Scanner;

public class Patient extends Person {
    public Patient(Hospital hospital, String username, String password, int money) {
        super(hospital, username, password, money);
    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("1. Reserve\n2. Charge Account\n3. Logout");
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline
                switch (choice) {
                    case 1:
                        reserve(scanner);
                        break;
                    case 2:
                        chargeAccount(scanner);
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

    public void chargeAccount(Scanner scanner) {
        try {
            System.out.println("Enter amount to charge: ");
            int amount = scanner.nextInt();
            scanner.nextLine();  // consume newline
            this.money += amount;
            System.out.println("Account charged. Current balance: " + this.money);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine();  // consume the invalid input
        } catch (Exception e) {
            System.out.println("Error while charging account: " + e.getMessage());
        }
    }

    public void reserve(Scanner scanner) {
        try {
            System.out.println("Enter doctor's username: ");
            String doctorUsername = scanner.nextLine();
            System.out.println("Enter date for reservation: ");
            String date = scanner.nextLine();
            boolean success = hospital.addReservation(doctorUsername, this, date);
            if (success) {
                System.out.println("Reservation successful.");
            } else {
                System.out.println("Reservation failed. Doctor not available or incorrect details.");
            }
        } catch (Exception e) {
            System.out.println("Error during reservation: " + e.getMessage());
        }
    }
}
