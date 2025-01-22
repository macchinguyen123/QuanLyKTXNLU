package model1;

public class Student {
    private String nameStudent;
    private String idStudent;

    private String gender;

    private String faculty;

    private String dateOfBirth;

    private String dormitory;

    private String room;

    private String address;

    private String idCCCD;

    private String numberPhone;

    private String nation;

    private String politicalForum;

    public Student(String nameStudent, String idStudent, String gender, String faculty, String dateOfBirth, String dormitory, String room, String address, String idCCCD, String numberPhone, String nation, String politicalForum) {
        this.nameStudent = nameStudent;
        this.idStudent = idStudent;
        this.gender = gender;
        this.faculty = faculty;
        this.dateOfBirth = dateOfBirth;
        this.dormitory = dormitory;
        this.room = room;
        this.address = address;
        this.idCCCD = idCCCD;
        this.numberPhone = numberPhone;
        this.nation = nation;
        this.politicalForum = politicalForum;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCCCD() {
        return idCCCD;
    }

    public void setIdCCCD(String idCCCD) {
        this.idCCCD = idCCCD;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPoliticalForum() {
        return politicalForum;
    }

    public void setPoliticalForum(String politicalForum) {
        this.politicalForum = politicalForum;
    }

    public boolean isMartyrs() {
        return this.politicalForum.equalsIgnoreCase("Con liệt sĩ, thương binh, bệnh binh");
    }

    public boolean isPoorHousehold() {
        return this.politicalForum.equalsIgnoreCase("Gia đình đặc biệt khó khăn");
    }
}
