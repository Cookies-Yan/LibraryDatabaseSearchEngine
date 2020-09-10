package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.BrwerMng;


/**
 * new borrower Dao 
 * @author qwe55
 *
 */
public class BrwerMngDao {
	/**
	 * add a new borrower
	 * @param borrower
	 * @param con
	 * @return
	 * @throws Exception
	 */
   public int add(Connection con, BrwerMng brwerMng)throws Exception{
	   String sql = "INSERT into borrower values(null,?,?,?,?)";
	   PreparedStatement pstmt = con.prepareStatement(sql);

	   pstmt.setString(1, BrwerMng.getSsn());
	   pstmt.setString(2, BrwerMng.getBname());
	   pstmt.setString(3, BrwerMng.getAddress());
	   pstmt.setString(4, BrwerMng.getPhone());

       int i = 0;
       try {
           i = pstmt.executeUpdate();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return i;
   }
   public ResultSet checkSsn(Connection con)throws Exception{
		String sql="select ssn from borrower";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
   }
   public ResultSet checkLoanId(Connection con,BrwerMng brwerMng)throws Exception{
	   String sql="select Card_id from borrower where Ssn=?";
	   PreparedStatement pstmt = con.prepareStatement(sql);
	   pstmt.setString(1, BrwerMng.getSsn());
	return pstmt.executeQuery(); 
   }
}
