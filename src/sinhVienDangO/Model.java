package sinhVienDangO;

import java.util.ArrayList;
import java.util.List;

public class Model {
    // Properties for PasswordModel
    private String password;
    private List<String> roomData;

    public Model() {
        roomData = new ArrayList<>();
        roomData.add("1. Cư xá A: 7 phòng trống");
        roomData.add("2. Cư xá B: 5 phòng trống");
        roomData.add("3. Cư xá C: không còn phòng trống");
        roomData.add("4. Cư xá D: 1 phòng trống");
        roomData.add("5. Cư xá E: 2 phòng trống");
        roomData.add("6. Cư xá F: không còn phòng trống");
    }
    // Methods for Model
    public String getExit() {
        return "Exit";
    }

    public String getManage() {
        return "Quản Lý Sinh Viên";
    }

    public String getRoomManage() {
        return "Quản Lý Phòng";
    }

    // Methods for PasswordModel
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidPassword() {
        return password != null && password.length() == 6;
    }

    // Placeholder for StudentModel
    // Add any properties or methods for StudentModel here in the future.

    public List<String> getRoomData() {
        return roomData;
    }
//    public void addRoom(String roomInfo) {
//        roomData.add(roomInfo);
//    }
//
//    public void updateRoom(int index, String newRoomInfo) {
//        if (index >= 0 && index < roomData.size()) {
//            roomData.set(index, newRoomInfo);
//        }
//    }
//
//    public void removeRoom(int index) {
//        if (index >= 0 && index < roomData.size()) {
//            roomData.remove(index);
//        }
//    }
}
