
import java.util.*;
public class Main {
  public static void main(String[] args) {
      HotelReservationsSystem hotel = new HotelReservationsSystem();
      Scanner scanner = new Scanner(System.in);

      while (true) {
          System.out.println("1. Reserve a room");
          System.out.println("2. Cancel a reservation");
          System.out.println("3. Display all rooms");
          System.out.println("4. Exit");
          System.out.print("Choose an option: ");
          int option = scanner.nextInt();

          switch (option) {
              case 1:
                  System.out.print("Enter room number: ");
                  int roomNumber = scanner.nextInt();
                  System.out.print("Enter guest name: ");
                  String guestName = scanner.next();
                  System.out.print("Enter duration: ");
                  int duration = scanner.nextInt();
                  hotel.reserveRoom(roomNumber, guestName, duration);
                  break;
              case 2:
                  System.out.print("Enter room number: ");
                  roomNumber = scanner.nextInt();
                  hotel.cancelReservation(roomNumber);
                  break;
              case 3:
                  hotel.displayRooms();
                  break;
              case 4:
                  System.exit(0);
                  break;
              default:
                  System.out.println("Invalid option. Please choose a valid option.");
          }
      }
  }
}
