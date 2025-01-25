package quanLyPhong;

import java.util.List;

public class Model {
    private String password;

    public Model() {
        // Sử dụng DormitoryDataManager để quản lý dữ liệu ký túc xá
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


}