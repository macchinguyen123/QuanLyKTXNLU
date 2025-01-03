package quanLyPhong;

public class Room {
    private String roomNumber;       // Số phòng
    private String roomType;         // Loại phòng (Phòng 6 người, Phòng 8 người, ...)
    private int capacity;            // Sức chứa
    private int currentOccupancy;    // Số người hiện tại trong phòng
    private boolean isPaid;          // Trạng thái đã thanh toán
    private boolean isUnpaid;        // Trạng thái chưa thanh toán
    private String paymentAmount;    // Số tiền thanh toán

    // Constructor đầy đủ
    public Room(String roomNumber, String roomType, int capacity, int currentOccupancy, boolean isPaid, boolean isUnpaid, String paymentAmount) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.capacity = capacity;
        this.currentOccupancy = currentOccupancy;
        this.isPaid = isPaid;
        this.isUnpaid = isUnpaid;
        this.paymentAmount = paymentAmount;
    }

    // Constructor không trạng thái thanh toán
    public Room(String roomNumber, String roomType, int capacity, int currentOccupancy) {
        this(roomNumber, roomType, capacity, currentOccupancy, false, false, "0K");
    }

    // Getter và Setter
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public void setCurrentOccupancy(int currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean isUnpaid() {
        return isUnpaid;
    }

    public void setUnpaid(boolean isUnpaid) {
        this.isUnpaid = isUnpaid;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    // Phương thức kiểm tra trạng thái phòng trống
    public boolean isAvailable() {
        return currentOccupancy < capacity;
    }

    // Phương thức hiển thị thông tin phòng
    public String displayRoomInfo() {
        return String.format(
                "Phòng: %s | Loại: %s | Sức chứa: %d | Hiện tại: %d | Thanh toán: %s | Số tiền: %s",
                roomNumber, roomType, capacity, currentOccupancy,
                isPaid ? "Đã thanh toán" : "Chưa thanh toán", paymentAmount
        );
    }
}
