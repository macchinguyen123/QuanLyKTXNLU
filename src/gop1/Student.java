package gop1;

public class Student {
    private int stt;
    private String ten, mssv, gioiTinh, khoa;
    private int namSinh;
    private String cuXa, phong;

    public Student(int stt, String ten, String mssv, String gioiTinh, String khoa, int namSinh, String cuXa, String phong) {
        this.stt = stt;
        this.ten = ten;
        this.mssv = mssv;
        this.gioiTinh = gioiTinh;
        this.khoa = khoa;
        this.namSinh = namSinh;
        this.cuXa = cuXa;
        this.phong = phong;
    }

    @Override
    public String toString() {
        return stt + ". " + ten + "_" + mssv + "_" + gioiTinh + "_" + khoa + "_" + namSinh + "_" + cuXa + "_" + phong;
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

}
