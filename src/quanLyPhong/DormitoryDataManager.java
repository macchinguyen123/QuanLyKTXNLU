package quanLyPhong;

import java.util.*;

public class DormitoryDataManager {
    private List<String> roomData;
    private Map<String, List<Room>> dormitoryData;
    private List<Room> paymentData;
    private static DormitoryDataManager instance;
    private Map<String, Boolean> roomAvailability;

    public DormitoryDataManager() {
        dormitoryData = new TreeMap<>();
        roomAvailability = new HashMap<>();
        initializeDormitoryData();
    }

    /**
     * Phương thức để lấy instance duy nhất của DormitoryDataManager
     */
    public static DormitoryDataManager getInstance() {
        if (instance == null) {
            instance = new DormitoryDataManager();
        }
        return instance;
    }



    private void initializeDormitoryData() {
        dormitoryData.put("A", List.of(
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
        dormitoryData.put("B", List.of(
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
        dormitoryData.put("C", List.of(
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
        dormitoryData.put("D", List.of(
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
        dormitoryData.put("E", List.of(
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
        dormitoryData.put("F", List.of(
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
        List<String> maleNames = List.of("Nguyễn Văn Anh", "Lê Văn Bảo", "Hoàng Văn Tài", "Phạm Văn Huy ", "Vũ Thành", "Ngô Văn Giang", "Đinh Quang Toàn");
        List<String> femaleNames = List.of("Nguyễn Thị Lan", "Trần Thị Thư", "Lê Thị Hoa", "Nguyễn Thị Kim", "Trần Thị Tuyết", "Lê Thị Thu", "Nguyễn Thị Mai");

        Random random = new Random();
        List<String> selectedMembers = new ArrayList<>();
        List<String> availableNames;

        // Kiểm tra cư xá và phân loại tên người
        if (roomNumber.startsWith("A") || roomNumber.startsWith("C") || roomNumber.startsWith("F")) {
            // Cư xá nam
            availableNames = new ArrayList<>(maleNames);
        } else {
            // Cư xá nữ
            availableNames = new ArrayList<>(femaleNames);
        }

        // Xáo trộn danh sách tên
        Collections.shuffle(availableNames, random);

        // Lấy các tên ngẫu nhiên mà không trùng lặp
        for (int i = 0; i < memberCount && i < availableNames.size(); i++) {
            selectedMembers.add(availableNames.get(i));
        }

        return selectedMembers;
    }

    public List<String> getRoomData() {
        return roomData;
    }



    public List<Room> generateRoomPaymentData(String dormitory) {
        Random random = new Random();
        paymentData = new ArrayList<>();

        List<Room> rooms = dormitoryData.get(dormitory);
        if (rooms != null) {
            for (Room room : rooms) {
                boolean isPaid = random.nextBoolean();
                room.setPaid(isPaid);
                room.setUnpaid(!isPaid);
                room.setPaymentAmount((500 + random.nextInt(301)) + "K"); // Số tiền (500K - 800K)
                paymentData.add(room);
            }
        }
        return paymentData;
    }


    /**
     * Phương thức lấy danh sách phòng theo cư xá
     */
    public List<Room> getRoomsByDormitory(String dormitory) {
        List<Room> roomList = dormitoryData.get(dormitory);
        if (roomList == null) {
            System.out.println("Không có phòng cho cư xá: " + dormitory);
        } else {
            for (Room room : roomList) {
                System.out.println("Phòng: " + room.getRoomNumber());
            }
        }
        return roomList;
    }
}
