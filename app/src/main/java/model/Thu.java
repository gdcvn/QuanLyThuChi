package model;

/**
 * Created by vuong on 24/10/2016.
 */

public class Thu {
    private Long id;
    private double soTien;
    private String mucThu;
    private String moTa;
    private String taiKhoanThu;
    private String thoiGian;

    public Thu() {

    }

    public Thu(Long id, double soTien, String mucThu, String moTa, String taiKhoanThu, String thoiGian) {
        this.id = id;
        this.soTien = soTien;
        this.mucThu = mucThu;
        this.moTa = moTa;
        this.taiKhoanThu = taiKhoanThu;
        this.thoiGian = thoiGian;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public String getMucThu() {
        return mucThu;
    }

    public void setMucThu(String mucThu) {
        this.mucThu = mucThu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTaiKhoanThu() {
        return taiKhoanThu;
    }

    public void setTaiKhoanThu(String taiKhoanThu) {
        this.taiKhoanThu = taiKhoanThu;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}
