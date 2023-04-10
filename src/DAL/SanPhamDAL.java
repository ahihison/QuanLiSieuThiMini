package DAL;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BLL.SanPham;

public class SanPhamDAL extends connectSql {
	public SanPhamDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<SanPham> docSanPham(String condition, String value) {
		String sql = "";
		ArrayList<SanPham> arrList = new ArrayList<SanPham>();
		try {
			if (condition.equals("sapxeptheoten")) {
				sql = "select * from SANPHAM where isDeleted = 1 order by TenSP";
			}
			if (condition.equals("docsanpham")) {
				sql = "select * from SANPHAM where isDeleted = 1";
			}
			if (condition.equals("sapxeptheogia")) {
				sql = "select * from SANPHAM where isDeleted = 1 order by GiaBan";
			}
			if (condition.equals("timkiem")) {
				sql = "select * from SANPHAM where isDeleted = 1 and TenSP LIKE ?";

			}

			PreparedStatement pstm = conn.prepareStatement(sql);
			if (condition.equals("timkiem")) {
				pstm.setString(1, "%" + value + "%");
			}
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setMaSp(rs.getInt("MaSP"));
				sp.setTenSp(rs.getString("TenSP"));
				sp.setGiaMua(rs.getInt("GiaMua"));
				sp.setGiaBan(rs.getInt("GiaBan"));
				sp.setHanSuDung(rs.getString("HSD"));
				sp.setMaLh(rs.getInt("MaLH"));
				sp.setDonVi(rs.getString("DonVi"));
				sp.setImg(rs.getString("img"));
				sp.setIsDeleted(rs.getInt("isDeleted"));
				arrList.add(sp);
			}
		} catch (Exception e) {
			
		}
		return arrList;
	}

	public int layMaLoaiSP(String tenMaLoai) throws SQLException {
		String sql = "SELECT MaLH FROM LOAIHANG WHERE TenLH = ?";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, tenMaLoai);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				return rs.getInt("MaLH");
			} else {
				return -1; // or throw an exception
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1; // or throw an exception
		}
	}

	public boolean xoaSanPham(String masp) throws SQLException {
		String sql = "UPDATE SANPHAM SET isDeleted = " + 0 + " where MaSP = " + masp;
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			int rowsUpdated = pstm.executeUpdate();

			return rowsUpdated > 0;
		}
	}

	public boolean themsanpham(SanPham sp, String condition, String oldMaSP) throws SQLException {
		String sql = "";
		if (condition.equals("themsanpham")) {
			sql = "INSERT INTO SANPHAM (TenSP, GiaMua, GiaBan, HSD, MaLH, DonVi, img,isDeleted,MaSP) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
		}
		if (condition.equals("suasanpham")) {
			sql = "UPDATE SANPHAM SET TenSP = ?, GiaMua = ?, GiaBan = ?, HSD = ?, MaLH = ?, DonVi = ?, img = ?, isDeleted = ?,MaSP = ? WHERE MaSP = ?";

		}
		PreparedStatement pstm = conn.prepareStatement(sql);

		try {
			
			pstm.setString(1, sp.getTenSp());
			pstm.setFloat(2, sp.getGiaMua());
			pstm.setFloat(3, sp.getGiaBan());
			pstm.setString(4, sp.getHanSuDung());
			pstm.setInt(5, sp.getMaLh());
			pstm.setString(6, sp.getDonVi());
			pstm.setString(7, ".//Image//" + sp.getImg());
			pstm.setInt(8, 1);
			pstm.setInt(9, sp.getMaSp());

			if (condition.equals("suasanpham")) {
				pstm.setInt(10, Integer.parseInt(oldMaSP));
			}

			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static void main(String[] args) throws SQLException {
		SanPhamDAL sp = new SanPhamDAL();
		SanPham spthem = new SanPham();

	}

}
