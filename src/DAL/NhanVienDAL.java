package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.NhanVien;

public class NhanVienDAL extends connectSql {

	public NhanVienDAL() throws SQLException {
		super();	
	}
	public ArrayList<NhanVien> kiemTraDangNhap() throws SQLException{
		ArrayList<NhanVien> arrNv = new ArrayList<NhanVien>();
		String sql ="select * from NHANVIEN where isDeleted = 1";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			NhanVien nv = new NhanVien();
			nv.setTaiKhoan(rs.getString("TaiKhoan"));
			nv.setMatKhau(rs.getString("MatKhau"));
			nv.setChucVu(rs.getInt("ChucVu"));
			nv.setMaNv(rs.getInt("MaNV"));
			nv.setHoTen(rs.getString("HoTen"));
			
			arrNv.add(nv);
		}
		return arrNv;
	}
	public static void main(String[] args) throws SQLException {
		NhanVienDAL nvd = new NhanVienDAL();
		System.out.println(nvd.kiemTraDangNhap());
	}
}
