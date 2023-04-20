package DAL;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.NhanVien;

public class NhanVienDAL extends connectSql {

	public NhanVienDAL() throws SQLException {
		super();
		
	}
	
	public ArrayList<NhanVien> kiemTraDangNhap() throws SQLException{
		ArrayList<NhanVien> arrNv = new ArrayList<NhanVien>();
		String sql ="";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		return arrNv;
		
		
	}
	

}
