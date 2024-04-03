package RailwayBookingSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Booking {

    private int totalBerths = 3;
    private int lowerBerths = 1;
    private int middleBerths = 1;
    private int upperBerths = 1;
    private int totalRACBerths = 1;
    private int totalWaitingListTickets = 1;
    private ArrayList<Passenger> bookedTickets = new ArrayList<>();
    private ArrayList<Passenger> racTickets = new ArrayList<>();
    private ArrayList<Passenger> waitingListTickets = new ArrayList<>();

    // ANSI escape codes for colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_NAVY = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_YELLOW = "\u001B[33m";


    public void bookTicket() {
        Scanner scanner = new Scanner(System.in);

        if (totalWaitingListTickets == 0) {
            System.out.println(ANSI_RED + "No tickets available." + ANSI_RESET);
            System.out.println("---------------------");
            return;
        }

        System.out.print(ANSI_WHITE+"Enter passenger name: ");
        String name = scanner.nextLine();
        System.out.print(ANSI_WHITE+"Enter passenger age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()
        System.out.print(ANSI_WHITE+"Enter passenger gender (M/F): ");
        String gender = scanner.nextLine().toUpperCase();
        System.out.print(ANSI_WHITE+"Enter berth preference (Lower/Middle/Upper): ");
        String berthPreference = scanner.nextLine().toLowerCase();

        if (age < 5) {
            System.out.println(ANSI_RED + "Children below 5 years are not allowed to book tickets." + ANSI_RESET);
            return;
        }

        if (berthPreference.equals("lower")) {
            if (lowerBerths > 0) {
                lowerBerths--;
            } else {
                System.out.println(ANSI_RED + "Lower berth not available. Suggesting available berths:" + ANSI_RESET);
                suggestAvailableBerths(name, age, gender);
                return;
            }
        } else if (berthPreference.equals("middle")) {
            if (middleBerths > 0) {
                middleBerths--;
            } else {
                System.out.println(ANSI_RED + "Middle berth not available. Suggesting available berths:" + ANSI_RESET);
                suggestAvailableBerths(name, age, gender);
                return;
            }
        } else if (berthPreference.equals("upper")) {
            if (upperBerths > 0) {
                upperBerths--;
            } else {
                System.out.println(ANSI_RED + "Upper berth not available. Suggesting available berths:" + ANSI_RESET);
                suggestAvailableBerths(name, age, gender);
                return;
            }
        } else {
            System.out.println(ANSI_RED + "Invalid input" + ANSI_RESET);
            return;
        }

        if (age > 5) {
            Passenger passenger = new Passenger(name, age, gender, berthPreference);

            if (totalBerths > 0) {
                bookedTickets.add(passenger);
                totalBerths--;
                System.out.println(ANSI_PURPLE + "Ticket booked successfully!" + ANSI_RESET);
            } else if (totalRACBerths > 0) {
                racTickets.add(passenger);
                totalRACBerths--;
                System.out.println(ANSI_PURPLE + "Ticket booked successfully!" + ANSI_RESET);
            } else {
                waitingListTickets.add(passenger);
                totalWaitingListTickets--;
                System.out.println(ANSI_PURPLE + "Ticket booked successfully!" + ANSI_RESET);
            }
        }
    }

    private void suggestAvailableBerths(String name, int age, String gender) {
        System.out.println(ANSI_CYAN + "------------------------------------------" + ANSI_RESET);
        if (lowerBerths > 0) {
            System.out.println(ANSI_YELLOW+"1. Lower");
        }
        if (middleBerths > 0) {
            System.out.println(ANSI_YELLOW+"2. Middle");
        }
        if (upperBerths > 0) {
            System.out.println(ANSI_YELLOW+"3. Upper");
        }

        if (lowerBerths == 0 && middleBerths == 0 && upperBerths == 0) {
            System.out.println(ANSI_YELLOW+"4. Book on RAC");
        }

        System.out.print("Enter your choice (1-4): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                lowerBerths--;
                bookedTickets.add(new Passenger(name, age, gender, "lower"));
                totalBerths--;
                System.out.println("---------------------------------");
                System.out.println(ANSI_PURPLE + "Ticket booked successfully!" + ANSI_RESET);
                System.out.println("---------------------------------");
                break;
            case 2:
                middleBerths--;
                bookedTickets.add(new Passenger(name, age, gender, "middle"));
                totalBerths--;
                System.out.println("---------------------------------");
                System.out.println(ANSI_PURPLE + "Ticket booked successfully!" + ANSI_RESET);
                System.out.println("---------------------------------");
                break;
            case 3:
                upperBerths--;
                bookedTickets.add(new Passenger(name, age, gender, "upper"));
                totalBerths--;
                System.out.println("---------------------------------");
                System.out.println(ANSI_PURPLE + "Ticket booked successfully!" + ANSI_RESET);
                System.out.println("---------------------------------");
                break;
            case 4:
                if (totalRACBerths > 0) {
                    racTickets.add(new Passenger(name, age, gender, "side lower"));
                    totalRACBerths--;
                    System.out.println("---------------------------------");
                    System.out.println(ANSI_PURPLE + "Ticket booked successfully! (RAC)" + ANSI_RESET);
                    System.out.println("---------------------------------");
                    break;
                } else {
                    System.out.println(ANSI_RED + "RAC NOT Available, you can book on Waiting List" + ANSI_RESET);
                    System.out.println(ANSI_NAVY+"Say Yes to book on Waiting List");
                    String reply = scanner.next().toLowerCase();
                    if (reply.equals("yes")) {
                        waitingListTickets.add(new Passenger(name, age, gender, "NA"));
                        totalWaitingListTickets--;
                        System.out.println(ANSI_PURPLE + "Ticket booked successfully! (Waiting List)" + ANSI_RESET);
                    }
                    break;
                }
            default:
                System.out.println(ANSI_RED + "Invalid choice." + ANSI_RESET);
                break;
        }
    }

    public void cancelTicket() {
        Scanner scanner = new Scanner(System.in);

        if (bookedTickets.isEmpty()) {
            System.out.println("No booked tickets to cancel.");
            return;
        }

        System.out.print(ANSI_YELLOW+"Enter passenger name to cancel the ticket: ");
        String canceledPassengerName = scanner.nextLine();

        Passenger canceledTicket = null;
        for (Passenger ticket : bookedTickets) {
            if (ticket.getName().equalsIgnoreCase(canceledPassengerName)) {
                canceledTicket = ticket;
                break;
            }
        }
        if (canceledTicket == null) {
            System.out.println(ANSI_RED+"Ticket with the provided name not found.");
            return;
        }

        bookedTickets.remove(canceledTicket);
        totalBerths++;

        if (!racTickets.isEmpty()) {
            Passenger confirmedTicket = racTickets.remove(0);
            totalRACBerths++;
            bookedTickets.add(new Passenger(confirmedTicket.getName(), confirmedTicket.getAge(), confirmedTicket.getGender(), canceledTicket.getBerthPreference()));
            System.out.println(ANSI_PURPLE + "Ticket canceled successfully for " + canceledTicket.getName() + ANSI_RESET);
            totalBerths--;
        } else {
            System.out.println(ANSI_PURPLE + "Ticket canceled successfully for " + canceledTicket.getName() + ". No RAC passenger available to move." + ANSI_RESET);
        }

        if (!waitingListTickets.isEmpty()) {
            Passenger racCandidate = waitingListTickets.remove(0);
            racTickets.add(new Passenger(racCandidate.getName(), racCandidate.getAge(), racCandidate.getGender(), "side lower"));
            System.out.println(ANSI_CYAN+"Waiting list passenger moved to RAC.");
            totalRACBerths--;
            totalWaitingListTickets++;
        }
        System.out.println("-----------------------------------------");
    }

    public void printTicketBooked() {
        if (bookedTickets.isEmpty()) {
            System.out.println(ANSI_PURPLE + "No booked tickets available." + ANSI_RESET);
            System.out.println("----------------------");
        } else {
            System.out.println(ANSI_PURPLE + "Booked Tickets:" + ANSI_RESET);
            System.out.println("---------------");
            for (int i = 0; i < bookedTickets.size(); i++) {
                Passenger ticket = bookedTickets.get(i);
                System.out.println((i + 1) + ". Name: " + ticket.getName() + "\n   Age: " + ticket.getAge() +
                        "\n   Gender: " + ticket.getGender() + "\n   Berth Preference: " + ticket.getBerthPreference());
            }
            System.out.println("----------------------");
            System.out.println(ANSI_PURPLE + "RAC Tickets:" + ANSI_RESET);
            System.out.println("----------------------");
            for (int i = 0; i < racTickets.size(); i++) {
                Passenger ticket = racTickets.get(i);
                System.out.println((i + 1) + ". Name: " + ticket.getName() + "\n   Age: " + ticket.getAge() +
                        "\n   Gender: " + ticket.getGender() + "\n   Berth Preference: " + ticket.getBerthPreference());
            }
            System.out.println("----------------------");
            System.out.println(ANSI_PURPLE + "Waiting List Tickets:" + ANSI_RESET);
            System.out.println("----------------------");
            for (int i = 0; i < waitingListTickets.size(); i++) {
                Passenger ticket = waitingListTickets.get(i);
                System.out.println((i + 1) + ". Name: " + ticket.getName() + "\n   Age: " + ticket.getAge() +
                        "\n   Gender: " + ticket.getGender() + "\n   Berth Preference: " + ticket.getBerthPreference());
            }
            System.out.println("----------------------");
            System.out.println(ANSI_NAVY+"Total Booked Tickets: " + bookedTickets.size());
            System.out.println(ANSI_NAVY+"Total RAC_Booked Tickets: " + racTickets.size());
            System.out.println(ANSI_NAVY+"Total Waiting_List Tickets: " + waitingListTickets.size());
            System.out.println("----------------------------");
        }
    }

    public void printAvailableTickets() {
        System.out.println(ANSI_PURPLE + "Available Tickets:" + ANSI_RESET);
        System.out.println("----------------------");
        System.out.println(ANSI_CYAN+"Available Tickets: " + (totalBerths));
        System.out.println(ANSI_CYAN+"RAC Available Tickets: " + (totalRACBerths));
        System.out.println(ANSI_CYAN+"Waiting List Available Tickets: " + (totalWaitingListTickets));
        System.out.println(ANSI_CYAN+"Total Available Tickets: " + (totalBerths + totalRACBerths + totalWaitingListTickets));
    }
}
