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
			String sql = "select * from LOAIHANG where isDeleted = 1";
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
	public int getLastMaLH() throws SQLException {
		String sql = "SELECT TOP 1 MaLH FROM LOAIHANG ORDER BY MALH DESC";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		  if (rs.next()) {
		        int maxColumnValue = rs.getInt("MaLH");
		        return maxColumnValue;
		    }
		  return 0;
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
	public boolean ThemLoaiHang(LoaiHang Lh,String condition) throws SQLException {
		String sql ="";
		if(condition.equals("themloaihang")) {
			sql = "insert into LOAIHANG (TenLH,isDeleted) values (?,?)";
		}
		if(condition.equals("sualoaihang")) {
			sql = "update LOAIHANG set TenLH = ? where MaLH = ? ";
		}
		if(condition.equals("xoaloaihang")) {
			sql = "update LOAIHANG set isDeleted =" + 0 + " where MaLH = ?";
		}
		PreparedStatement pstm = conn.prepareStatement(sql);
		try {
			
			if(condition.equals("sualoaihang")) {
				pstm.setString(1, Lh.getTenLH());
				pstm.setInt(2, Lh.getMaLH());
			}
			if(condition.equals("xoaloaihang")) {
				pstm.setInt(1, Lh.getMaLH());
			}
			if(condition.equals("themloaihang")) {
				pstm.setString(1, Lh.getTenLH());
				pstm.setInt(2, 1);
			}
			
			pstm.executeUpdate();
		} catch (Exception e) {

			return false;
		}
		
		return true;
		
	}
	
	public static void main(String[] args) throws SQLException {
		LoaiHangDAL test = new LoaiHangDAL();
		int temp = test.getLastMaLH();
		
	}
}
