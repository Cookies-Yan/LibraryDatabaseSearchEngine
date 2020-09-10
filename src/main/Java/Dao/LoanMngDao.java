package Dao;

import Model.LoanMng;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanMngDao {
	
	/**
	 * add new borrower
	 * @param con
	 * @param loanMng
	 * @return
	 * @throws Exception
	 */
	public int addLoan(Connection con, LoanMng loanMng )throws Exception{
		String sql = "insert into book_loans values (null,?,?,?,?,null,null,null)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, loanMng.getIsbn());
//		System.out.println(loanMng.getIsbn());
		pstmt.setInt(2, loanMng.getCard_id());
//		System.out.println(loanMng.getCard_id());
		pstmt.setString(3, loanMng.getDate_out());
//		System.out.println(loanMng.getDate_out());
		pstmt.setString(4, loanMng.getDue_date());
//		System.out.println(loanMng.getDue_date());

		String sql_updateAvail = "update book set Avail=1 where Isbn=?";
		PreparedStatement pstmt_updateAvail = con.prepareStatement(sql_updateAvail);
		pstmt_updateAvail.setString(1, loanMng.getIsbn());
//		System.out.println(loanMng.getIsbn());
		try {
			pstmt_updateAvail.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int i = 0;
		try {
			i = pstmt.executeUpdate();
//			System.out.println(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public int addFine(Connection con,int n)throws Exception{
		String sql_fine = "insert into fines values (?,null,0)";
		PreparedStatement pstmt_fine = con.prepareStatement(sql_fine);
		pstmt_fine.setInt(1, n);
		return pstmt_fine.executeUpdate();
	}
	public ResultSet getLoan_id(Connection con,LoanMng loanMng)throws Exception{
		String sql="select Loan_id from (select Loan_id,Isbn,Card_id,Date_out,Due_date,Date_in,Bname from book_loans natural join borrower)as w where w.Isbn=? and w.Card_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, loanMng.getIsbn());
		pstmt.setInt(2, Integer.valueOf(loanMng.getCard_id()));

		return pstmt.executeQuery();
    } 
	/**
	 * return all card_id exit
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public ResultSet checkId(Connection con,LoanMng loanMng)throws Exception{
		String sql="select card_id from borrower where card_id= ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, loanMng.getCard_id());
		return pstmt.executeQuery();
	}
	
	/**
	 * return count of loans of a single borrower
	 * @param con
	 * @param loanMng
	 * @return
	 * @throws Exception
	 */
	public ResultSet checkCount(Connection con, LoanMng loanMng)throws Exception{
		String sql="select count(*) from book_loans where card_id= ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, loanMng.getCard_id());
		return pstmt.executeQuery();
	}
	public ResultSet checkAvail(Connection con, LoanMng loanMng)throws Exception{
		String sql="select avail from book where isbn= ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, loanMng.getIsbn());
		return pstmt.executeQuery();
	}
	
	/**
	 * search loan information
	 * @param con
	 * @param loanMng
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con,LoanMng loanMng)throws Exception{
    	StringBuffer sb= new StringBuffer("select Loan_id,Isbn,Card_id,Date_out,Due_date,Date_in from book_loans");
		sb.append(" where Isbn like '%"+loanMng.getSearchTxt()+"%'"+" or Card_id like '%"+loanMng.getSearchTxt()+"%'"
	+" or Bname like '%"+loanMng.getSearchTxt()+"%'");
//		System.out.println(sb);
    	PreparedStatement pstmt = con.prepareStatement(sb.toString());
    	return pstmt.executeQuery();
    }
	
	public int updateLoan(Connection con, LoanMng loanMng )throws Exception{
		String sql_updateLoan = "update book_loans set Date_in=? where Isbn=? and Card_id=?";
		PreparedStatement pstmt_updateLoan = con.prepareStatement(sql_updateLoan);
		pstmt_updateLoan.setString(1, loanMng.getDate_in());
		pstmt_updateLoan.setString(2, loanMng.getIsbn());
		pstmt_updateLoan.setInt(3, loanMng.getCard_id());
		
		String sql_updateAvail_1 = "update book set Avail=0 where Isbn=?";
		PreparedStatement pstmt_updateAvail_1 = con.prepareStatement(sql_updateAvail_1);
		pstmt_updateAvail_1.setString(1, loanMng.getIsbn());
		pstmt_updateAvail_1.executeUpdate();
		return pstmt_updateLoan.executeUpdate();
	}
}
   