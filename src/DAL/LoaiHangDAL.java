package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BLL.LoaiHang;
import BLL.SanPham;

public class LoaiHangDAL extends connectSql {
	
	
	public LoaiHangDAL() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ArrayList<LoaiHang> docLoaiHang(){
		ArrayList<LoaiHang> arrLoaihang = new ArrayList<LoaiHang>();
		try {
			String sql = "select * from LOAIHANG";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				LoaiHang lh = new LoaiHang();
				lh.setMaLH(rs.getInt(1)); 
				lh.setTenLH(rs.getString(2));
				arrLoaihang.add(lh);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return arrLoaihang;
	}
	public ArrayList<LoaiHang> docLoaiHangMaLH(int maLh) throws SQLException{
		ArrayList<LoaiHang> arrLoaihang = new ArrayList<LoaiHang>();
		String sql = "select * from LOAIHANG where MaLH =" + maLh;
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			LoaiHang lh = new LoaiHang();
			lh.setMaLH(rs.getInt("MaLH"));
			lh.setTenLH(rs.getString(2));
			arrLoaihang.add(lh);
		}
	
	return arrLoaihang;
	}
	
	public static void main(String[] args) throws SQLException {
		LoaiHangDAL test = new LoaiHangDAL();
		ArrayList<LoaiHang> arrLoaihang = test.docLoaiHang();
		System.out.println(arrLoaihang);
	}
}
