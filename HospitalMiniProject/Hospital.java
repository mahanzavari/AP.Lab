import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hospital {
    private HashMap<String, Person> persons = new HashMap<>();
    private HashMap<String, ArrayList<Reserve>> reservations = new HashMap<>();
    private HashMap<String, ArrayList<String>> doctorAvailability = new HashMap<>();

    public void addAvailability(String doctorUsername, ArrayList<String> dates) {
        doctorAvailability.put(doctorUsername, dates);
    }

    public boolean addReservation(String doctorUsername, Patient patient, String date) {
        ArrayList<String> availableDates = doctorAvailability.get(doctorUsername);
        if (availableDates != null && availableDates.contains(date)) {
            Reserve reserve = new Reserve(patient, (Doctor) persons.get(doctorUsername), date);
            reservations.computeIfAbsent(doctorUsername, k -> new ArrayList<>()).add(reserve);
            return true;
        }
        return false;
    }

    private void login(Scanner scanner) {
        try {
            System.out.println("Enter username: ");
            String username = scanner.nextLine();
            System.out.println("Enter password: ");
            String password = scanner.nextLine();
            String key = username + " " + password;
            if (persons.containsKey(key)) {
                persons.get(key).menu();
            } else {
                System.out.println("Invalid login credentials.");
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    private void signUpStaff(Scanner scanner) {
        try {
            System.out.println("Enter username: ");
            String username = scanner.nextLine();
            System.out.println("Enter password: ");
            String password = scanner.nextLine();
            System.out.println("Enter initial money: ");
            int money = scanner.nextInt();
            scanner.nextLine();  // consume newline
            Doctor doctor = new Doctor(this, username, password, money);
            persons.put(username + " " + password, doctor);
            System.out.println("Doctor registered successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid details.");
            scanner.nextLine();  // consume the invalid input
        } catch (Exception e) {
            System.out.println("Error during staff sign-up: " + e.getMessage());
        }
    }

    private void signUpPatient(Scanner scanner) {
        try {
            System.out.println("Enter username: ");
            String username = scanner.nextLine();
            System.out.println("Enter password: ");
            String password = scanner.nextLine();
            System.out.println("Enter initial money: ");
            int money = scanner.nextInt();
            scanner.nextLine();  // consume newline
            Patient patient = new Patient(this, username, password, money);
            persons.put(username + " " + password, patient);
            System.out.println("Patient registered successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid details.");
            scanner.nextLine();  // consume the invalid input
        } catch (Exception e) {
            System.out.println("Error during patient sign-up: " + e.getMessage());
        }
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("1. Login\n2. Sign-up Staff\n3. Sign-up Patient\n4. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline
                switch (choice) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        signUpStaff(scanner);
                        break;
                    case 3:
                        signUpPatient(scanner);
                        break;
                    case 4:
                        scanner.close();
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

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        hospital.displayMenu();
    }

    public ArrayList<Reserve> getReservations(String doctorUsername) {
        return reservations.get(doctorUsername);
    }
}
