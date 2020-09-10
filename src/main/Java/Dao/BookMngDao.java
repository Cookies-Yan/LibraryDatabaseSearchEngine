package Dao;

import Model.BookMng;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * book search Dao
 * @author qwe55
 *
 */
public class BookMngDao {
	/**
	 * search the book information
	 * @param con
	 * @param bookMng
	 * @return
	 * @throws Exception
	 */
    public ResultSet list(Connection con,BookMng bookMng)throws Exception{
    	StringBuffer sb=null;
    	if(StringUtils.isNotEmpty(bookMng.getSearchTxt()))
    	{
    		sb= new StringBuffer("select isbn,title,group_concat(author) as a , avail from book");
    		sb.append(" where book.Isbn like '%"+bookMng.getSearchTxt()+"%'"+" or book.Title like '%"+bookMng.getSearchTxt()+"%'"
    	+" or book.Author like '%"+bookMng.getSearchTxt()+"%'"+"group by book.Isbn");
    	}
    	
    	PreparedStatement pstmt=con.prepareStatement(sb.toString());
    	return pstmt.executeQuery();
    }   
}
