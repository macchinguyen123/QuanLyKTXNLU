package gop1;

public class Room {
    private String roomNumber; // Số phòng
    private String roomType;   // Loại phòng
    private int totalSlots;    // Tổng số chỗ
    private int occupiedSlots; // Số chỗ đã được sử dụng
    private boolean paid;

    public Room(String roomNumber, String roomType, int totalSlots, int occupiedSlots) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.totalSlots = totalSlots;
        this.occupiedSlots = occupiedSlots;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public int getAvailableSlots() {
        return totalSlots - occupiedSlots;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isPaid() {
        return paid;
    }
}
