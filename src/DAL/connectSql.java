package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class connectSql {
public static void main(String[] args) throws SQLException {
	String connectionUrl = "jdbc:sqlserver://ROG-G14:1433;databaseName=QuanLySieuThi;user=sa;password=sa;encrypt=true;trustServerCertificate=true;";
	Connection conn = DriverManager.getConnection(connectionUrl);
	PreparedStatement pstm = conn.prepareStatement("Select * from SANPHAM");
	ResultSet rs = pstm.executeQuery();
	 List<String> result = new ArrayList<>();
	while(rs.next()) {
		String row = rs.getString("MaSP")+","+rs.getString("TenSP") + ","+rs.getString("GiaMua");
		result.add(row);
	}
	
	 rs.close();
     pstm.close();
     conn.close();
}
}
