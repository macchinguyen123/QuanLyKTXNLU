package sinhVienDangKy;

import sinhVienDangO.Student;

import java.util.ArrayList;
import java.util.List;

public class TakeData {
    private static TakeData instance;
    private List<Student> sVLuu;

    private TakeData() {
        sVLuu = new ArrayList<>();
    }

    public static TakeData getInstances() {
        if (instance == null) {
            instance = new TakeData();
        }
        return instance;
    }

    public void addStudent(Student data) {
        if (data == null) {
            System.out.println("Dữ liệu đầu vào là null.");
            return;
        }
        String name = data.getTen();
        String gender = data.getGioiTinh();
        String birthYear = data.getNamSinh(); // Giả sử birthYear là một số nguyên
        String MSSV = data.getMssv();
        String SĐT = data.getSđt();
        String HKTT = data.getDiaChi();
        String Khoa = data.getKhoa();
        String CuXa = data.getCuXa();
        String Phong = data.getPhong();
        String cMND = data.getIdCCCD();
        String danToc = data.getDanToc();
        String dienChinhSach = data.getDienChinhSach();

        // Khởi tạo đối tượng Student
        Student student = new Student(name, MSSV, gender, Khoa, birthYear, CuXa, Phong, HKTT, cMND, SĐT, danToc, dienChinhSach);
        // Thêm vào danh sách
        sVLuu.add(student);
    }

    public List<Student> getsVLuu() {
        return sVLuu;
    }

}
