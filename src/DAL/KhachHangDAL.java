package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BLL.KhachHang;


public class KhachHangDAL extends connectSql {
	
	
	public KhachHangDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ArrayList<KhachHang> docKhachHang(String condition, String value)  {
		String sql = "";
		ArrayList<KhachHang> arrList = new ArrayList<KhachHang>();
                try {
			if (condition.equals("sapxeptheoten")) {
				sql = "select * from KHTT where isDeleted = 1 order by HoTen";
			}
			if (condition.equals("dockhachhang")) {
				sql = "select * from KHTT where isDeleted = 1";
			}
			if (condition.equals("sapxeptheomakh")) {
				sql = "select * from KHTT where isDeleted = 1 order by MaKH";
			}
			if (condition.equals("timkiem")) {
				sql = "select * from KHTT where where isDeleted = 1 and TenSP LIKE ?";

			}
			PreparedStatement pstm = conn.prepareStatement(sql);
			if (condition.equals("timkiem")) {
				pstm.setString(1, "%" + value + "%");
			}
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setMakh(rs.getInt("makh"));
				kh.setDiemThuong(rs.getFloat("diemThuong"));
				kh.setHoTen(rs.getString("hoTen"));
                                kh.setDiaChi(rs.getString("diaChi"));
                                kh.setImg(rs.getString("img"));
                                kh.setNgayCapThe(rs.getString("ngayCapThe"));
                                kh.setNgayMuaGanNhat(rs.getString("ngayMuaGanNhat"));
				arrList.add(kh);
			}
		} catch (Exception e) {
			
		}
		return arrList;
	}
        
        public boolean xoaKhachHang(String makh) throws SQLException {
		String sql = "UPDATE KHTT SET isDeleted = " + 0 + " where MaSP = " + makh;
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			int rowsUpdated = pstm.executeUpdate();

			return rowsUpdated > 0;
		}
	}
        public boolean themkhachhang(KhachHang kh, String condition, String oldMaKH) throws SQLException {
		String sql = "";
		if (condition.equals("themkhachhang")) {
			sql = "INSERT INTO KHTT (HoTen, DiaChi, NgayCapThe, NgayMuaGanNhat, DiemThuong, img, isDeleted, MaKH) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
		}
		if (condition.equals("suakhachhang")) {
			sql = "UPDATE KHTT SET HoTen = ?, DiaChi = ?, NgayCapThe = ?, NgayMuaGanNhat = ?, DiemThuong = ?, img = ?, isDeleted = ?,MaKH = ? WHERE MaKH = ?";

		}
		PreparedStatement pstm = conn.prepareStatement(sql);

		try {
			
			pstm.setString(1, kh.getHoTen());
                        pstm.setString(1, kh.getDiaChi());
			pstm.setString(1, kh.getNgayCapThe());
                        pstm.setString(1, kh.getNgayMuaGanNhat());
                        pstm.setFloat(1, kh.getDiemThuong());
			pstm.setString(7, ".//Image//" + kh.getImg());
			pstm.setInt(8, 1);
			pstm.setInt(9, kh.getMakh());

			if (condition.equals("suakhachhang")) {
				pstm.setInt(10, Integer.parseInt(oldMaKH));
			}

			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
        public static void main(String[] args) throws SQLException {
		KhachHangDAL kh = new KhachHangDAL();
		KhachHang khthem = new KhachHang();
        }
}