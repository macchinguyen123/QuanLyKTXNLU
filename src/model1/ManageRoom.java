package model1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ManageRoom {
    private Map<String, List<Room>> dormitoryData;
    private List<Map<String, Object>> savedData ;

    public ManageRoom() {
        dormitoryData = new TreeMap<>();
    }


    public Map<String, List<Room>> getDormitoryData() {
        return dormitoryData;
    }

    public List<Room> getRoomsByDormitory(String dormitory) {
        return dormitoryData.getOrDefault(dormitory, new ArrayList<>());
    }


    public void saveData(List<Map<String, Object>> data) {
        savedData.clear();
        savedData.addAll(data);
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


}
