package model;

public class Chi {
	private Long id;
	private double soTien;
	private String mucChi;
	private String moTa;
	private String taiKhoanChi;
	private String thoiGian;
	private int daChi;
	private Long idTaiKhoan;

	public Chi() {

	}

	public Chi(Long id, double soTien, String mucChi, String moTa, String taiKhoanChi, String thoiGian, int daChi) {
		this.id = id;
		this.soTien = soTien;
		this.mucChi = mucChi;
		this.moTa = moTa;
		this.taiKhoanChi = taiKhoanChi;
		this.thoiGian = thoiGian;
		this.daChi = daChi;
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

	public String getMucChi() {
		return mucChi;
	}

	public void setMucChi(String mucChi) {
		this.mucChi = mucChi;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getTaiKhoanChi() {
		return taiKhoanChi;
	}

	public void setTaiKhoanChi(String taiKhoanChi) {
		this.taiKhoanChi = taiKhoanChi;
	}

	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	public int getDaChi() {
		return daChi;
	}

	public void setDaChi(int daChi) {
		this.daChi = daChi;
	}

	public Long getIdTaiKhoan() {
		return idTaiKhoan;
	}

	public void setIdTaiKhoan(Long idTaiKhoan) {
		this.idTaiKhoan = idTaiKhoan;
	}
}
