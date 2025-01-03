package quanLyPhong;

public class Room {
    private final String roomNumber; // Số phòng
    private final String roomType;   // Loại phòng
    private final int totalSlots;    // Tổng số chỗ
    private int occupiedSlots;       // Số chỗ đã được sử dụng
    private boolean paid;            // Trạng thái thanh toán

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

    public void setOccupiedSlots(int occupiedSlots) {
        this.occupiedSlots = occupiedSlots;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
