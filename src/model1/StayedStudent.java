package model1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StayedStudent {
    private Map<String, Student> studentMap;

    public StayedStudent() {
        this.studentMap = new HashMap<>();

        Student st1 = new Student("Nguyễn Văn A", "23130001", "Nam", "Công nghệ thông tin", "24/01/2005", "A", "A04", "Bình Định", "123456", "0987654321", "Kinh", "Con liệt sĩ, thương binh, bệnh binh");
        Student st2 = new Student("Nguyễn Thị B", "23130002", "Nữ", "Công nghệ sinh học", "20/05/2004", "D", "D10", "Tiền Giang", "234567", "0345678990", "Mông", "Gia đình đặc biệt khó khăn");
        Student st3 = new Student("Nguyễn Văn C", "23130003", "Nam", "Công nghệ thực phẩm", "02/10/2005", "C", "C02", "Long An", "341678", "0168390591", "Kinh", "");
        Student st4 = new Student("Nguyễn Văn D", "23130004", "Nam", "Công nghệ thông tin", "04/08/2005", "A", "A03", "Kiên Giang", "401231", "0636036812", "Kinh", "Con liệt sĩ, thương binh, bệnh binh");
        Student st5 = new Student("Nguyễn Thị E", "23130005", "Nữ", "Lâm nghiệp", "09/11/2004", "B", "B05", "Dak Lak", "579130", "0470641237", "Kinh", "Gia đình đặc biệt khó khăn");
        Student st6 = new Student("Đinh Thị M", "23130014", "Nữ", "Thú y", "19/05/2006", "E", "E04", "Kiên Giang", "428450", "0470646432", "Thái", "");
        Student st7 = new Student("Trần Văn N", "23130015", "Nam", "Kinh tế", "17/01/2003", "F", "F06", "TP.HCM", "085342", "0470646289", "Kinh", "Gia đình đặc biệt khó khăn");
        Student st8 = new Student("Đặng Thị O", "23130021", "Nữ", "Ngôn ngữ anh", "09/11/2001", "B", "B11", "Long An", "581534", "0260641237", "Kinh", "");

        addStudent(st1);
        addStudent(st2);
        addStudent(st3);
        addStudent(st4);
        addStudent(st5);
        addStudent(st6);
        addStudent(st7);
        addStudent(st8);

    }

    // Thêm sinh viên vào danh sách
    public void addStudent(Student student) {
        if (student == null || student.getIdStudent() == null) {
            throw new IllegalArgumentException("Thông tin sinh viên không hợp lệ!");
        }
        studentMap.put(student.getIdStudent(), student);
    }

    // Tìm sinh viên theo MSSV
    public Student findStudentById(String mssv) {
        return studentMap.get(mssv.trim());
    }

    // Cap nhat thong tin sinh vien
    public void updateStudent(Student updatedStudent) {
        if (updatedStudent == null || updatedStudent.getIdStudent() == null) {
            throw new IllegalArgumentException("Thông tin sinh viên không hợp lệ!");
        }

        String mssv = updatedStudent.getIdStudent();
        if (studentMap.containsKey(mssv)) {
            studentMap.put(mssv, updatedStudent); // Thay thế thông tin sinh viên trong map
        } else {
            throw new IllegalArgumentException("Không tìm thấy sinh viên có MSSV: " + mssv);
        }
    }

    // Xóa sinh viên khỏi danh sách theo MSSV
    public boolean removeStudentById(String mssv) {
        return studentMap.remove(mssv.trim()) != null;
    }

    public Map<String, Student> getStudentMap() {
        return studentMap;
    }
}
