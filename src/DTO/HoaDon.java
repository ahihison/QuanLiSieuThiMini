
package DTO;

public class HoaDon {
    private int MaHd,MaNv,MaKh,isDeleted;
    private float MucGiam,DiemThuong,TienTra;
    private String ThoiDiem,Gio;
	public int getMaHd() {
		return MaHd;
	}
	public void setMaHd(int maHd) {
		MaHd = maHd;
	}
	public int getMaNv() {
		return MaNv;
	}
	public void setMaNv(int maNv) {
		MaNv = maNv;
	}
	public int getMaKh() {
		return MaKh;
	}
	public void setMaKh(int maKh) {
		MaKh = maKh;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public float getMucGiam() {
		return MucGiam;
	}
	public void setMucGiam(float mucGiam) {
		MucGiam = mucGiam;
	}
	public float getDiemThuong() {
		return DiemThuong;
	}
	public void setDiemThuong(float diemThuong) {
		DiemThuong = diemThuong;
	}
	public float getTienTra() {
		return TienTra;
	}
	public void setTienTra(float tienTra) {
		TienTra = tienTra;
	}
	public String getThoiDiem() {
		return ThoiDiem;
	}
	public void setThoiDiem(String thoiDiem) {
		ThoiDiem = thoiDiem;
	}
	public String getGio() {
		return Gio;
	}
	public void setGio(String gio) {
		Gio = gio;
	}
   
}
