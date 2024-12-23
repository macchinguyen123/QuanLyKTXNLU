package sinhVienDangKy;

public class StudentInfo {
    private String name;
    private String gender;
    private String birthDate;
    private String studentId;
    private String phoneNumber;
    private String address;
    private String faculty;
    private String dormitory;
    private String room;
    private String idNumber;
    private String ethnicity;
    private boolean isPolicy1;
    private boolean isPolicy2;

    // Constructors
    public StudentInfo(String name, String gender, String birthDate, String studentId,
                       String phoneNumber, String address, String faculty, String dormitory,
                       String room, String idNumber, String ethnicity,
                       boolean isPolicy1, boolean isPolicy2) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.studentId = studentId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.faculty = faculty;
        this.dormitory = dormitory;
        this.room = room;
        this.idNumber = idNumber;
        this.ethnicity = ethnicity;
        this.isPolicy1 = isPolicy1;
        this.isPolicy2 = isPolicy2;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }

    public String getDormitory() { return dormitory; }
    public void setDormitory(String dormitory) { this.dormitory = dormitory; }

    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }

    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }

    public String getEthnicity() { return ethnicity; }
    public void setEthnicity(String ethnicity) { this.ethnicity = ethnicity; }

    public boolean isPolicy1() { return isPolicy1; }
    public void setPolicy1(boolean policy1) { isPolicy1 = policy1; }

    public boolean isPolicy2() { return isPolicy2; }
    public void setPolicy2(boolean policy2) { isPolicy2 = policy2; }
}
