package gop1;

public class Student {
    private int stt;
    private String ten, mssv, gioiTinh, khoa;
    private int namSinh;
    private String cuXa, phong;
    private String diaChi, idCCCD, sđt;

    public Student(int stt, String ten, String mssv, String gioiTinh, String khoa, int namSinh, String cuXa, String phong, String diaChi, String idCCCD, String sđt) {
        this.stt = stt;
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
    }

    @Override
    public String toString() {
        return stt + ". " + ten + "_" + mssv + "_" + gioiTinh + "_" + khoa + "_" + namSinh + "_" + cuXa + "_" + phong + "_" + diaChi + "_" + idCCCD + "_" + sđt;
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

    public int getStt() {
        return stt;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public int getNamSinh() {
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
}
