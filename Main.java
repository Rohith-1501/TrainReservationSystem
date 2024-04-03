package RailwayBookingSystem;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Booking booking = new Booking();
        Scanner sc = new Scanner(System.in);
        final String ANSI_BRIGHT_WHITE = "\u001B[97m";
        final String ANSI_RED = "\u001B[31m";

        while (true) {
            System.out.println(ANSI_BRIGHT_WHITE+"\nRailway Ticket Reservation System");
            System.out.println(ANSI_BRIGHT_WHITE+"1. Book Ticket");
            System.out.println(ANSI_BRIGHT_WHITE+"2. Cancel Ticket");
            System.out.println(ANSI_BRIGHT_WHITE+"3. Print Booked Tickets");
            System.out.println(ANSI_BRIGHT_WHITE+"4. Print Available Tickets");
            System.out.println(ANSI_BRIGHT_WHITE+"5. Exit");

            System.out.println("Enter your choice (1-5): ");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println("-----------------------");
            switch (choice) {
                case 1:
                    booking.bookTicket();
                    break;

                case 2:
                    booking.cancelTicket();
                    break;

                case 3:
                    booking.printTicketBooked();
                    break;

                case 4:
                    booking.printAvailableTickets();
                    break;

                case 5:
                    System.out.println("Exiting the application. Thank you!");
                    System.exit(0);
                    break;

                default:
                    System.out.println(ANSI_RED+"Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
