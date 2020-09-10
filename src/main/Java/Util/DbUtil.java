package Util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil
{
    private String dbUrl="jdbc:mysql://localhost:3306/librarydb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private String dbUserName="root";
    private String dbPassword="root";
    private String jdbcName="com.mysql.cj.jdbc.Driver";

    public Connection getCon()throws Exception
    {
        Class.forName(jdbcName);
        Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        return con;
    }
    public void closeCon(Connection con)throws Exception{
        if(con!=null)
        {
            con.close();
        }
    }
    public static void main(String[] args)
    {
        DbUtil dbUtil = new DbUtil();
        try
        {
            dbUtil.getCon();
            System.out.println("Data base connected...");
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Data base connection failed!");
        }
    }

}

