package quanLyPhong;

import java.util.*;

public class DormitoryDataManager {
    private List<String> roomData;
    private Map<String, List<Room>> dormitoryData;

    public DormitoryDataManager() {
        dormitoryData = new TreeMap<>();
        initializeDormitoryData();
        initializeData();
    }

    private void initializeDormitoryData() {
        dormitoryData.put("Cư Xá A", List.of(
                new Room("A01", "Phòng 6 người", 6, 1),
                new Room("A02", "Phòng 8 người", 8, 4),
                new Room("A03", "Phòng 8 người", 8, 4),
                new Room("A04", "Phòng 6 người", 6, 3),
                new Room("A05", "Phòng 6 người", 6, 5),
                new Room("A06", "Phòng 6 người", 6, 4),
                new Room("A07", "Phòng 8 người", 8, 4),
                new Room("A08", "Phòng 8 người", 8, 2),
                new Room("A09", "Phòng 8 người", 8, 1),
                new Room("A10", "Phòng 8 người", 8, 7),
                new Room("A11", "Phòng 8 người", 8, 7),
                new Room("A12", "Phòng 8 người", 8, 7),
                new Room("A13", "Phòng 8 người", 8, 7),
                new Room("A14", "Phòng 8 người", 8, 7),
                new Room("A15", "Phòng 8 người", 8, 6)
        ));
        dormitoryData.put("Cư Xá B", List.of(
                new Room("B01", "Phòng 8 người", 8, 0),
                new Room("B02", "Phòng 8 người", 8, 1),
                new Room("B03", "Phòng 8 người", 8, 3),
                new Room("B04", "Phòng 8 người", 8, 4),
                new Room("B05", "Phòng 8 người", 8, 3),
                new Room("B06", "Phòng 8 người", 8, 4),
                new Room("B07", "Phòng 8 người", 8, 7),
                new Room("B08", "Phòng 8 người", 8, 6),
                new Room("B09", "Phòng 8 người", 8, 4),
                new Room("B10", "Phòng 8 người", 8, 5),
                new Room("B11", "Phòng 8 người", 8, 6),
                new Room("B12", "Phòng 8 người", 8, 6),
                new Room("B13", "Phòng 8 người", 8, 6),
                new Room("B14", "Phòng 8 người", 8, 6),
                new Room("B15", "Phòng 8 người", 8, 6),
                new Room("B16", "Phòng 8 người", 8, 6),
                new Room("B17", "Phòng 8 người", 8, 6),
                new Room("B18", "Phòng 8 người", 8, 8)
        ));
        dormitoryData.put("Cư Xá C", List.of(
                new Room("C01", "Phòng 8 người", 8, 0),
                new Room("C02", "Phòng 8 người", 8, 1),
                new Room("C03", "Phòng 6 người", 6, 3),
                new Room("C04", "Phòng 6 người", 6, 4),
                new Room("C05", "Phòng 6 người", 6, 3),
                new Room("C06", "Phòng 6 người", 6, 4),
                new Room("C07", "Phòng 8 người", 8, 7),
                new Room("C08", "Phòng 8 người", 8, 6),
                new Room("C09", "Phòng 8 người", 8, 6),
                new Room("C10", "Phòng 8 người", 8, 6),
                new Room("C11", "Phòng 8 người", 8, 6),
                new Room("C12", "Phòng 8 người", 8, 6),
                new Room("C13", "Phòng 8 người", 8, 6),
                new Room("C14", "Phòng 8 người", 8, 6),
                new Room("C15", "Phòng 8 người", 8, 6),
                new Room("C16", "Phòng 8 người", 8, 6)
        ));
        dormitoryData.put("Cư Xá D", List.of(
                new Room("D01", "Phòng 6 người", 6, 3),
                new Room("D02", "Phòng 6 người", 6, 1),
                new Room("D03", "Phòng 6 người", 6, 3),
                new Room("D04", "Phòng 6 người", 6, 4),
                new Room("D05", "Phòng 6 người", 6, 3),
                new Room("D06", "Phòng 6 người", 6, 4),
                new Room("D07", "Phòng 8 người", 8, 7),
                new Room("D08", "Phòng 8 người", 8, 6),
                new Room("D09", "Phòng 8 người", 8, 6),
                new Room("D10", "Phòng 8 người", 8, 6),
                new Room("D11", "Phòng 8 người", 8, 6),
                new Room("D12", "Phòng 8 người", 8, 6),
                new Room("D13", "Phòng 8 người", 8, 6),
                new Room("D14", "Phòng 8 người", 8, 6)
        ));
        dormitoryData.put("Cư Xá E", List.of(
                new Room("E01", "Phòng 6 người", 6, 3),
                new Room("E02", "Phòng 6 người", 6, 1),
                new Room("E03", "Phòng 6 người", 6, 3),
                new Room("E04", "Phòng 6 người", 6, 4),
                new Room("E05", "Phòng 6 người", 6, 3),
                new Room("E06", "Phòng 6 người", 6, 4),
                new Room("E07", "Phòng 8 người", 8, 7),
                new Room("E08", "Phòng 8 người", 8, 6),
                new Room("E09", "Phòng 8 người", 8, 6),
                new Room("E10", "Phòng 8 người", 8, 6),
                new Room("E11", "Phòng 8 người", 8, 6),
                new Room("E12", "Phòng 8 người", 8, 6)
        ));
        dormitoryData.put("Cư Xá F", List.of(
                new Room("F01", "Phòng 6 người", 6, 3),
                new Room("F02", "Phòng 6 người", 6, 1),
                new Room("F03", "Phòng 6 người", 6, 3),
                new Room("F04", "Phòng 6 người", 6, 4),
                new Room("F05", "Phòng 6 người", 6, 3),
                new Room("F06", "Phòng 6 người", 6, 4),
                new Room("F07", "Phòng 8 người", 8, 7),
                new Room("F08", "Phòng 8 người", 8, 6),
                new Room("F09", "Phòng 8 người", 8, 6),
                new Room("F10", "Phòng 8 người", 8, 6)
        ));
    }

    public Map<String, List<Room>> getDormitoryData() {
        return dormitoryData;
    }
    public List<String> getRoomMembers(String roomNumber, int memberCount) {
        return List.of("Nguyễn Văn A", "Trần Thị B", "Lê Văn C", "Hoàng Thị D", "Phạm Văn E", "Vũ Thị F", "Ngô Văn G", "Đinh Thị H")
                .subList(0, Math.min(memberCount, 8));
    }
    public List<String> getRoomData() {
        return roomData;
    }
    private void initializeData() {
        roomData = List.of(
                "1. Cư xá A: 7 phòng trống",
                "2. Cư xá B: 5 phòng trống",
                "3. Cư xá C: không còn phòng trống",
                "4. Cư xá D: 1 phòng trống",
                "5. Cư xá E: 2 phòng trống",
                "6. Cư xá F: không còn phòng trống"
        );
    }
    public List<RoomPaymentData> generateRoomPaymentData(String dormitory) {
        Random random = new Random();
        List<RoomPaymentData> paymentData = new ArrayList<>();

        List<Room> rooms = dormitoryData.get(dormitory);
        if (rooms != null) {
            for (Room room : rooms) {
                boolean isPaid = random.nextBoolean();
                paymentData.add(new RoomPaymentData(
                        room.getRoomNumber(),
                        room.getRoomType(),
                        isPaid,
                        !isPaid,
                        (500 + random.nextInt(301)) + "K" // Số Tiền (500K - 800K)
                ));
            }
        }
        return paymentData;
    }
}
