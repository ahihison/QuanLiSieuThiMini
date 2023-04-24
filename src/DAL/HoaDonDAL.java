
package DAL;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.HoaDon;

public class HoaDonDAL extends connectSql {
	public HoaDonDAL() throws SQLException {
		super();
	}

	public ArrayList<HoaDon> docHoaDon(String condition, String value) {
		String sql = "";
		ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
		try {
			if (condition.equals("docHoaDon")) {
				sql = "select* from HOADON WHERE isDeleted=1";
			}
			if (condition.equals("sapxeptheotien")) {
				sql = "select * from HOADON WHERE isDeleted=1 order by TongTienTra";
			}
			if (condition.equals("timkiem")) {
				sql = "select * from HOADON where isDeleted = 1 and MaHD LIKE %" + value + "% ";
			}
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				
				HoaDon hd = new HoaDon();
				hd.setMaHd(rs.getInt("MaHD"));
				hd.setThoiDiem(rs.getString("ThoiDiemLap"));
				hd.setTienTra(rs.getFloat("TongTienTra"));
				hd.setMucGiam(rs.getFloat("MucGiam"));
				hd.setDiemThuong(rs.getInt("DiemThuong"));
				hd.setMaNv(rs.getInt("MaNV"));
				hd.setMaKh(rs.getInt("MaKH"));
				
				hd.setGio(rs.getString("GioMua"));
			
				
				hd.setIsDeleted(rs.getInt("isDeleted"));
				arr.add(hd);
			}
		} catch (Exception e) {
		}
		return arr;
	}

	public int LayMaHd(String mahd) throws SQLException {
		String sql = "SELECT MaLH FROM HOADONCHITIET WHERE MaSP = ?,SoLuong=?;";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, mahd);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				return rs.getInt("MaHD");
			} else {
				return -1; // or throw an exception
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1; // or throw an exception
		}
	}

	public boolean xoaHoaDon(String mahd) throws SQLException {
		String sql = "UPDATE SANPHAM SET isDeleted = " + 0 + " where MaHD = " + mahd;
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			int rowsUpdated = pstm.executeUpdate();

			return rowsUpdated > 0;
		}
	}

	public boolean themhoadon(HoaDon hd, String condition, String MaHDcu) throws SQLException {
		String sql = "";
		if (condition.equals("themhoadon")) {
			sql = "INSERT INTO HOADON (MaHD, ThoiDiemLap, TongTienTra, MucGiam, DiemThuong, MaNV, MaKH,isDeleted,GioMua) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
		}
		if (condition.equals("suahoadon")) {
			sql = "UPDATE HOADON SET MaHd = ?, ThoiDiemLap = ?, TongTienTra = ?, MucGiam = ?, DiemThuong = ?, MaNV = ?, MaKH = ?, isDeleted = ?,GioMua = ? WHERE MaHD = ?";

		}
		PreparedStatement pstm = conn.prepareStatement(sql);

		try {

			pstm.setInt(1, hd.getMaHd());
			pstm.setString(2, hd.getThoiDiem());
			pstm.setFloat(3, hd.getTienTra());
			pstm.setFloat(4, hd.getMucGiam());
			pstm.setFloat(5, hd.getDiemThuong());
			pstm.setInt(6, hd.getMaNv());
			pstm.setInt(7, hd.getMaKh());
			pstm.setString(8, hd.getGio());
			pstm.setInt(9, 1);

			if (condition.equals("suahoadon")) {
				System.out.println(hd.getMaHd());
				System.out.println(MaHDcu);
				pstm.setInt(10, Integer.parseInt(MaHDcu));
			}

			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public static void main(String[] args) throws SQLException {
		HoaDonDAL hdd = new HoaDonDAL();
		ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
		System.out.println(hdd.docHoaDon("docHoaDon", null)); 

	}
}
