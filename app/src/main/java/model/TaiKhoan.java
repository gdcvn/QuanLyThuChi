package model;

public class TaiKhoan {
	private long id;
	private String tenTaiKhoan;
	private String loaiTaiKhoan;
	private double soTien;

	public TaiKhoan(long id, String tenTaiKhoan, String loaiTaiKhoan,
			double soTien) {
		super();
		this.id = id;
		this.tenTaiKhoan = tenTaiKhoan;
		this.loaiTaiKhoan = loaiTaiKhoan;
		this.soTien = soTien;
	}

	public TaiKhoan() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getLoaiTaiKhoan() {
		return loaiTaiKhoan;
	}

	public void setLoaiTaiKhoan(String loaiTaiKhoan) {
		this.loaiTaiKhoan = loaiTaiKhoan;
	}

	public double getSoTien() {
		return soTien;
	}

	public void setSoTien(double soTien) {
		this.soTien = soTien;
	}

}
