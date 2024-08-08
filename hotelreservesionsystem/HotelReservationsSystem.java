import java.util.ArrayList;
import java.util.List;

class HotelRoom {
    private int roomNumber;
    private boolean isReserved;
    private String guestName;
    private int duration;

    public HotelRoom(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isReserved = false;
        this.guestName = "";
        this.duration = 0;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getDuration() {
        return duration;
    }

    public void reserve(String guestName, int duration) {
        if (!isReserved) {
            this.guestName = guestName;
            this.duration = duration;
            isReserved = true;
            System.out.println("Room " + roomNumber + " reserved successfully for " + duration + " nights by " + guestName + ".");
        } else {
            System.out.println("Room " + roomNumber + " is already reserved.");
        }
    }

    public void cancelReservation() {
        if (isReserved) {
            isReserved = false;
            guestName = "";
            duration = 0;
            System.out.println("Reservation for room " + roomNumber + " cancelled successfully.");
        } else {
            System.out.println("Room " + roomNumber + " is not reserved.");
        }
    }
}

public class HotelReservationsSystem {
    private List<HotelRoom> rooms;

    public HotelReservationsSystem() {
        rooms = new ArrayList<>();
        // Initialize rooms
        for (int i = 1; i <= 100; i++) {
            rooms.add(new HotelRoom(i));
        }
    }

    public void reserveRoom(int roomNumber, String guestName, int duration) {
        HotelRoom room = findRoomByNumber(roomNumber);
        if (room!= null) {
            room.reserve(guestName, duration);
        } else {
            System.out.println("Room " + roomNumber + " not found.");
        }
    }

    public void cancelReservation(int roomNumber) {
        HotelRoom room = findRoomByNumber(roomNumber);
        if (room!= null) {
            room.cancelReservation();
        } else {
            System.out.println("Room " + roomNumber + " not found.");
        }
    }

    private HotelRoom findRoomByNumber(int roomNumber) {
        for (HotelRoom room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public void displayRooms() {
        for (HotelRoom room : rooms) {
            System.out.println("Room " + room.getRoomNumber() + " - " + (room.isReserved()? "Reserved by " + room.getGuestName() + " for " + room.getDuration() + " nights" : "Available"));
        }
    }
}
