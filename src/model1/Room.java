package model1;

public class Room {
    private String roomNumber;       // Số phòng
    private String roomType;         // Loại phòng (Phòng 6 người, Phòng 8 người, ...)
    private int capacity;            // Sức chứa
    private int currentOccupancy;    // Số người hiện tại trong phòng
    private boolean isPaid;          // Trạng thái đã thanh toán
    private boolean isUnpaid;        // Trạng thái chưa thanh toán
    private int electricityIndex; // Chỉ số điện nước đã nhập
    private int paymentAmount; // Số tiền điện nước

    // Constructor đầy đủ
    public Room(String roomNumber, String roomType, int capacity, int currentOccupancy, boolean isPaid, boolean isUnpaid, String paymentAmount) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.capacity = capacity;
        this.currentOccupancy = currentOccupancy;
        this.isPaid = isPaid;
        this.isUnpaid = isUnpaid;
    }


    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setElectricityIndex(int electricityIndex) {
        this.electricityIndex = electricityIndex;
    }

    // Constructor không trạng thái thanh toán
    public Room(String roomNumber, String roomType, int capacity, int currentOccupancy) {
        this(roomNumber, roomType, capacity, currentOccupancy, false, false, "0K");
    }

    // Getter và Setter
    public String getRoomNumber() {
        return roomNumber;
    }


    public String getRoomType() {
        return roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }


}
