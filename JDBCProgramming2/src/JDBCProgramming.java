import java.sql.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

class MySQLJDBCUtil {
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		    String url       = "jdbc:mysql://localhost/classicmodels?serverTimezone=UTC";
		    String user      = "root";
		    String password  = "twinsangel1996^^ ";
		    return conn = DriverManager.getConnection(url, user, password);
		}
}

public class JDBCProgramming{
	public static void main(String[] args) throws IOException
	{
	         
		        String sql = "SELECT firstname, lastname, email " +
		                     "FROM employees";
		        
		        try (Connection conn = MySQLJDBCUtil.getConnection();
		             Statement stmt  = conn.createStatement();
		             ResultSet rs    = stmt.executeQuery(sql)) {
		           
		            // loop through the result set
		            while (rs.next()) {
		                System.out.println(rs.getString("firstname") + "\t" + 
		                                   rs.getString("lastname")  + "\t" +
		                                   rs.getString("email"));
		                    
		            }
		        } catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		        }
		    }	
}