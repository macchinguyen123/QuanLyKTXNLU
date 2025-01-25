package model1;

import java.util.*;

public class ManageRoom {
    private Map<String, List<Room>> dormitoryData;
    private List<Map<String, Object>> savedData;
    private List<Student> students = new ArrayList<>();

    public ManageRoom() {
        dormitoryData = new TreeMap<>();
    }



    public static void sortRoomsByAmount(List<Object[]> roomData) {
        // Sắp xếp danh sách phòng theo số tiền thanh toán giảm dần
        roomData.sort((o1, o2) -> (int) o2[2] - (int) o1[2]);
    }

    public static List<Object[]> filterRoomsByPaymentStatus(List<Object[]> roomData, boolean showPaid) {
        List<Object[]> filteredRooms = new ArrayList<>();

        // Lọc các phòng theo trạng thái thanh toán
        for (Object[] row : roomData) {
            boolean isPaid = (Boolean) row[2]; // "Đã Thanh Toán"
            if (isPaid == showPaid) {
                // Thêm phòng vào danh sách nếu trùng trạng thái thanh toán
                filteredRooms.add(new Object[]{row[0], row[1], Integer.parseInt(row[4].toString().replace("K", ""))});
            }
        }

        return filteredRooms;
    }

    public List<String> getRoomMembers(String roomNumber, int memberCount) {
        List<String> maleNames = List.of("Nguyễn Văn Anh", "Lê Văn Bảo", "Hoàng Văn Tài", "Phạm Văn Huy ", "Vũ Thành",
                "Ngô Văn Giang", "Đinh Quang Toàn", "Nguyễn Văn Thanh");
        List<String> femaleNames = List.of("Nguyễn Thị Lan", "Trần Thị Thư", "Lê Thị Hoa", "Nguyễn Thị Kim",
                "Trần Thị Tuyết", "Lê Thị Thu", "Nguyễn Thị Mai", "Phạm Mai Phương");

        Random random = new Random();

        List<String> selectedMembers = new ArrayList<>();
        List<String> availableNames;
        students = StayedStudent.getStudents();

        // Lọc sinh viên theo mã phòng
        for (Student student : students) {
            if (student.getPhong().equalsIgnoreCase(roomNumber)) {
                selectedMembers.add(student.getTen());
            }
        }
        if (selectedMembers.size() > memberCount) {
            selectedMembers = selectedMembers.subList(0, memberCount);
        }

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
}


