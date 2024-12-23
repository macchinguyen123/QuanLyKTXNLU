package gop1;

import java.time.LocalDate;

public class Student {
    private String ten, mssv, gioiTinh, khoa;
    private String namSinh;
    private String cuXa, phong;
    private String diaChi, idCCCD, sđt;
    private String danToc, dienChinhSach;

    public Student(String ten, String mssv, String gioiTinh, String khoa, String namSinh, String cuXa, String phong, String diaChi, String idCCCD, String sđt, String danToc, String dienChinhSach) {
        this.ten = ten;
        this.mssv = mssv;
        this.gioiTinh = gioiTinh;
        this.khoa = khoa;
        this.namSinh = namSinh;
        this.cuXa = cuXa;
        this.phong = phong;
        this.diaChi = diaChi;
        this.idCCCD = idCCCD;
        this.sđt = sđt;
        this.danToc = danToc;
        this.dienChinhSach = dienChinhSach;
    }

    @Override
    public String toString() {
        return ten + "_" + mssv + "_" + gioiTinh + "_" + khoa + "_" + namSinh + "_" + cuXa + "_" + phong + "_" + diaChi + "_" + idCCCD + "_" + sđt;
    }

    public String getTen() {
        return ten;
    }

    public String getMssv() {
        return mssv;
    }

    public String getKhoa() {
        return khoa;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public String getCuXa() {
        return cuXa;
    }

    public String getPhong() {
        return phong;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getIdCCCD() {
        return idCCCD;
    }

    public String getSđt() {
        return sđt;
    }

    public String getDanToc() {
        return danToc;
    }

    public String getDienChinhSach() {
        return dienChinhSach;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public void setCuXa(String cuXa) {
        this.cuXa = cuXa;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setIdCCCD(String idCCCD) {
        this.idCCCD = idCCCD;
    }

    public void setSđt(String sđt) {
        this.sđt = sđt;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public void setDienChinhSach(String dienChinhSach) {
        this.dienChinhSach = dienChinhSach;
    }

    public boolean isMartyrs() {
        return this.dienChinhSach.equalsIgnoreCase("Con liệt sĩ, thương binh, bệnh binh");
    }

    public boolean isPoorHousehold() {
        return this.dienChinhSach.equalsIgnoreCase("Hộ nghèo, gia đình khó khăn");
    }

    public boolean isDisability() {
        return this.dienChinhSach.equalsIgnoreCase("Khuyết tật, bệnh hiểm nghèo");
    }

    public boolean isEthnic() {
        return this.dienChinhSach.equalsIgnoreCase("Dân tộc thiểu số");
    }

    public boolean isNotSubject() {
        return this.dienChinhSach.equalsIgnoreCase("Không thuộc các đối tượng trên");
    }
}
