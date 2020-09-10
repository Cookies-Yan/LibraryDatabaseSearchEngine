package Dao;

import Model.FineMng;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FineMngDao {
	
	public ResultSet listFineAmt(Connection con)throws Exception{
		String sql="select card_id,bname,sum(fine_amt)as sum from book_loans group by card_id";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
    }
	
	public int updateFine(Connection con,FineMng fineMng)throws Exception{
		String sql = "update fines set fine_amt=? where Loan_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setFloat(1, fineMng.getFine_amt());
		pstmt.setInt(2, fineMng.getLoan_id());
		return pstmt.executeUpdate();
	}
	
	public int updateFine_ReturnNoLate(Connection con,FineMng fineMng)throws Exception{
		String sql = "update fines set fine_amt=?,paid=1 where Loan_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setFloat(1, fineMng.getFine_amt());
		pstmt.setInt(2, fineMng.getLoan_id());
		return pstmt.executeUpdate();
	}
	
	public ResultSet list_date(Connection con)throws Exception{	
		String sql="select Loan_id,Date_out,Due_date,Date_in from book_loans";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	public boolean checkLate(Connection con,String D1, String D2)throws Exception {
		String sql="select datediff(?,?) as daydiff ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, D1);
		pstmt.setString(2, D2);
		ResultSet rs = pstmt.executeQuery();
		int daydiff = 0;
        System.out.println(1);
		while(rs.next()) {
			daydiff = rs.getInt("daydiff");
            System.out.println(daydiff);
		}
		if (daydiff == 0){
			return true;
		}
		else {
			return false;
		}
	}
	
	public int checkDiff(Connection con,String D1, String D2)throws Exception {
		String sql="select datediff(?,?) as daydiff";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, D1);
		pstmt.setString(2, D2);
		ResultSet rs=pstmt.executeQuery();
		int daydiff=0;
		while(rs.next()) {
			daydiff=rs.getInt(1);
		}
		return daydiff;
	}
	
	public ResultSet list_Loan(Connection con,FineMng fineMng)throws Exception{	
		String sql="select loan_id,isbn,date_out,due_date,date_in,fine_amt from book_loans where card_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, fineMng.getCard_id());
		return pstmt.executeQuery();
	}
	
	public int payFine(Connection con,FineMng fineMng)throws Exception{
		String sql = "update fines set paid=1, Fine_amt = 0.00 where loan_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setFloat(1, fineMng.getLoan_id());
		return pstmt.executeUpdate();
	}
	
	public ResultSet checkPaid(Connection con,FineMng fineMng)throws Exception{
		String sql="select Loan_id,date_in,paid from fines natural join book_loans where Loan_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setFloat(1, fineMng.getLoan_id());
		return pstmt.executeQuery();
	}

	public ResultSet checkBname(Connection con,FineMng fineMng)throws Exception{
		String sql="select Bname from book_loans where card_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setFloat(1, fineMng.getCard_id());
		return pstmt.executeQuery();
	}
}
