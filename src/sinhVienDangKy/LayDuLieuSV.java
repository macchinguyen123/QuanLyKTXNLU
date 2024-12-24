package sinhVienDangKy;

import sinhVienDangO.Student;

import java.util.ArrayList;
import java.util.List;

public class LayDuLieuSV {
    private static LayDuLieuSV instance;
    private List<Student> sVLuu;

    private LayDuLieuSV() {
        sVLuu = new ArrayList<>();
    }

    public static LayDuLieuSV getInstances() {
        if (instance == null) {
            instance = new LayDuLieuSV();
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
// In ra dữ liệu để kiểm tra
        System.out.println("Thêm sinh viên: " + data.getTen());
        System.out.println("Thêm sinh viên: " + data.getDiaChi());
        // Thêm vào danh sách
        sVLuu.add(student);
    }

    public List<Student> getsVLuu() {
        return sVLuu;
    }

    public void clearData() {
        sVLuu.clear();
    }

    public void removeStudent(String mssv) {
        sVLuu.removeIf(student -> student.getMssv().equals(mssv));
    }

}
