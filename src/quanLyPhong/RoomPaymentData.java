package quanLyPhong;

public class RoomPaymentData {
    private String roomNumber;
    private String roomType;
    private boolean isPaid;
    private boolean isUnpaid;
    private String amount;

    public RoomPaymentData(String roomNumber, String roomType, boolean isPaid, boolean isUnpaid, String amount) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isPaid = isPaid;
        this.isUnpaid = isUnpaid;
        this.amount = amount;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public boolean isUnpaid() {
        return isUnpaid;
    }

    public String getAmount() {
        return amount;
    }
}
